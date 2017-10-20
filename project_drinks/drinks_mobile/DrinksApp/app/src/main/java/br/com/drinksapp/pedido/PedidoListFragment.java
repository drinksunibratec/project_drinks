package br.com.drinksapp.pedido;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;


public class PedidoListFragment extends ListFragment
        implements ActionMode.Callback,
        AdapterView.OnItemLongClickListener,
        LoaderManager.LoaderCallbacks<Cursor>,
        SwipeRefreshLayout.OnRefreshListener {

    ListView mListView;
    ActionMode mActionMode;

    PedidoRepositorio mRepositorio;

    CursorAdapter mAdapter;
    String mTextoBusca;

    SwipeRefreshLayout mSwipeLayout;
    BroadcastReceiver mServiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mSwipeLayout.setRefreshing(false);
            if (!intent.getBooleanExtra(PedidoIntentService.EXTRA_SUCESSO, false)) {
                Toast.makeText(getActivity(), R.string.erro_sincronizacao,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        IntentFilter filter = new IntentFilter(PedidoIntentService.ACAO_SINCRONIZAR);
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mServiceReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                mServiceReceiver);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list_pedido, null);
        mSwipeLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(
                R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRepositorio = new PedidoRepositorio(getActivity());
        mAdapter = new PedidoCursorAdapter(getActivity(), null);
        mListView = getListView();
        setListAdapter(mAdapter);
        mListView.setOnItemLongClickListener(this);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mActionMode != null) {
            iniciarModoExclusao();
            atualizarTitulo();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (mActionMode == null) {
            Activity activity = getActivity();
            if (activity instanceof PedidoListFragment.AoClicarNoPedido) {
                Cursor cursor = (Cursor) l.getItemAtPosition(position);
                Pedido pedido = PedidoRepositorio.pedidoFromCursor(cursor);

                PedidoListFragment.AoClicarNoPedido listener = (PedidoListFragment.AoClicarNoPedido) activity;
                listener.clicouNoPedido(pedido);
            }
        } else {
            atualizarItensMarcados(mListView, position);
            if (qtdeItensMarcados() == 0) {
                mActionMode.finish();
            }
        }

    }

    public void buscar(String s) {
        mTextoBusca = TextUtils.isEmpty(s) ? null : s;
        getLoaderManager().restartLoader(0, null, this);
    }

    public void limparBusca() {
        buscar(null);
    }

    public interface AoClicarNoPedido {
        void clicouNoPedido(Pedido pedido);
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_delete_list, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.acao_delete) {
            remover();
            actionMode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        mActionMode = null;
        for (int i = 0; i < mListView.getCount(); i++) {
            mListView.setItemChecked(i, false);
        }
        mListView.clearChoices();
        mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        limparBusca();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        boolean consumed = (mActionMode == null);
        if (consumed) {
            iniciarModoExclusao();
            mListView.setItemChecked(i, true);
            atualizarItensMarcados(mListView, i);
        }
        return consumed;
    }

    private void iniciarModoExclusao() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        mActionMode = activity.startSupportActionMode(this);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void atualizarItensMarcados(ListView l, int position) {
        l.setItemChecked(position, l.isItemChecked(position));
        atualizarTitulo();
    }

    private int qtdeItensMarcados() {
        SparseBooleanArray checked = mListView.getCheckedItemPositions();
        int checkedCount = 0;
        for (int i = 0; i < checked.size(); i++) {
            if (checked.valueAt(i)) {
                checkedCount++;
            }
        }
        return checkedCount;
    }

    private void atualizarTitulo() {
        int checkedCount = qtdeItensMarcados();
        String selecionados = getResources().getQuantityString(
                R.plurals.numero_selecionados,
                checkedCount, checkedCount);
        mActionMode.setTitle(selecionados);
    }

    private void remover() {
        final List<Pedido> pedidosExcluidos = new ArrayList<Pedido>();
        SparseBooleanArray checked = mListView.getCheckedItemPositions();
        for (int i = checked.size() - 1; i >= 0; i--) {
            if (checked.valueAt(i)) {
                int position = checked.keyAt(i);
                Cursor cursor = (Cursor) mListView.getItemAtPosition(position);
                Pedido pedido = mRepositorio.pedidoFromCursor(cursor);
                pedidosExcluidos.add(pedido);
                mRepositorio.excluir(pedido);
            }
        }
        Activity activity = getActivity();
        if (activity instanceof PedidoListFragment.AoExcluirPedidos) {
            PedidoListFragment.AoExcluirPedidos aoExcluirPedidos =
                    (PedidoListFragment.AoExcluirPedidos)activity;
            aoExcluirPedidos.exclusaoCompleta(pedidosExcluidos);
        }

        Snackbar.make(mListView,
                getString(R.string.mensagem_excluir, pedidosExcluidos.size()),
                Snackbar.LENGTH_LONG)
                .setAction(R.string.desfazer, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (Pedido pedido : pedidosExcluidos) {
                            pedido.status = Pedido.Status.ATUALIZAR;
                            mRepositorio.inserirLocal(pedido, getActivity().getContentResolver());
                        }
                        limparBusca();
                    }
                }).show();
    }

    public interface  AoExcluirPedidos {
        void exclusaoCompleta(List<Pedido> pedidos);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mRepositorio.buscar(getActivity(), mTextoBusca);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onRefresh() {
        Intent it = new Intent(getActivity(), PedidoIntentService.class);
        getActivity().startService(it);
    }
}
