package br.com.drinksapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.http.EstabelecimentoTask;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {



    GoogleApiClient mGoogleApiClient;

    LoaderManager mLoader;

    GoogleMap mMap;
    private boolean mPermissaoLocalizacaoConcedida;
    private Location mUltimaLocalizacao;
    private LatLng mDefaultLocation;
    private boolean mDeveExibirDialogGPS;
    private Handler mHandler;
    private CameraPosition mCameraPosition;
    private int mTenativas;

    private ProgressDialog pDialog;
    private List<Estabelecimento> mListaEstabelecimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mListaEstabelecimentos = new ArrayList<Estabelecimento>();

        mLoader = getSupportLoaderManager();
        mLoader.initLoader(0, null, this);

        mDefaultLocation = new LatLng(-8.0475622, -34.8769643);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

        mHandler = new Handler();
        mDeveExibirDialogGPS = savedInstanceState == null;

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


    }

    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        return new EstabelecimentoTask(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, List<Estabelecimento> data) {
        if (data != null) {
            mListaEstabelecimentos = data;
            for (Estabelecimento estabelecimento : mListaEstabelecimentos) {
                Double lat = Double.parseDouble(estabelecimento.getLatitude());
                Double lng = Double.parseDouble(estabelecimento.getLongitude());
                LatLng ponto = new LatLng(lat, lng);

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(ponto)
                        .title(estabelecimento.getNomeFantasia())
                        .snippet("Bairro: " + estabelecimento.getBairro()));
                marker.setTag(estabelecimento);
            }

            mMap.setOnInfoWindowClickListener(new MarkerListener());
        }
    }


    class MarkerListener implements GoogleMap.OnInfoWindowClickListener{


        @Override
        public void onInfoWindowClick(Marker marker) {
            Estabelecimento estabelecimento = (Estabelecimento)marker.getTag();
            new TaskListarProdutos().execute(estabelecimento);
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private class TaskListarProdutos extends AsyncTask<Estabelecimento, Void, Intent> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Carregando... Aguarde!");
            showDialog();
        }

        @Override
        protected Intent doInBackground(Estabelecimento... estabelecimento) {
            ArrayList<Produto> retorno = null;
            List<Produto>  produtos = null;
            Intent it = null;
            try {
                produtos =  DBConnectParser.listProdutosPorEstabelecimento(estabelecimento[0]);
                retorno = new ArrayList<Produto>(produtos);
                if(retorno != null){
                    it = new Intent(MainActivity.this, ListaProdutosActivity.class);
                    it.putParcelableArrayListExtra(Constantes.EXTRA_LISTA_PRODUTOS, retorno);
                    it.putExtra(Constantes.EXTRA_ESTABELECIMENTO, estabelecimento[0]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return it;
        }

        @Override
        protected void onPostExecute(Intent it) {
            startActivity(it);
            hideDialog();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(Constantes.EXTRA_DIALOG, mDeveExibirDialogGPS);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mDeveExibirDialogGPS = savedInstanceState.getBoolean(Constantes.EXTRA_DIALOG, true);
    }

    private void verificarStatusGPS(){
        final LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        final LocationSettingsRequest.Builder locationSetting = new LocationSettingsRequest.Builder();
        locationSetting.setAlwaysShow(true);
        locationSetting.addLocationRequest(locationRequest);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(
          mGoogleApiClient, locationSetting.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()){
                    case LocationSettingsStatusCodes.SUCCESS:
                        getDeviceLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        if(mDeveExibirDialogGPS){
                            try {
                                status.startResolutionForResult(MainActivity.this, Constantes.REQUEST_CHECAR_GPS);
                                mDeveExibirDialogGPS = false;
                            } catch (IntentSender.SendIntentException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        updateLocationUI();
        getDeviceLocation();

    }


    private void getDeviceLocation() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mPermissaoLocalizacaoConcedida = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    Constantes.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mPermissaoLocalizacaoConcedida) {
            mUltimaLocalizacao = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);

        }
        // Set the map's camera position to the current location of the device.
        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mUltimaLocalizacao != null) {
            mTenativas = 0;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mUltimaLocalizacao.getLatitude(),
                            mUltimaLocalizacao.getLongitude()), Constantes.DEFAULT_ZOOM));
        } else {
            if(mTenativas < 10){
                mTenativas++;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDeviceLocation();
                    }
                }, 2000);
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, Constantes.DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
        // A step later in the tutorial adds the code to get the device location.
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mPermissaoLocalizacaoConcedida = false;
        switch (requestCode) {
            case Constantes.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPermissaoLocalizacaoConcedida = true;
                }
            }
        }
        updateLocationUI();
    }
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mPermissaoLocalizacaoConcedida) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mUltimaLocalizacao = null;
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        Constantes.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        verificarStatusGPS();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()){
            try {
                connectionResult.startResolutionForResult(this, Constantes.REQUEST_ERRO_PLAY_SERVICES);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }else{
            exibirMensagemErro(this, connectionResult.getErrorCode());
        }
    }

    private void exibirMensagemErro(FragmentActivity activity, final int codigoErro){
        final String TAG = "DIALOG_ERRO_PLAY_SERVICES";

        if(getSupportFragmentManager().findFragmentByTag(TAG) == null){


            DialogFragment erroFragment = new DialogFragment(){
                @NonNull
                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
                    int result = apiAvailability.isGooglePlayServicesAvailable(getActivity());

                    return apiAvailability.getErrorDialog(getActivity(), result, Constantes.REQUEST_ERRO_PLAY_SERVICES);
                }
            };
            erroFragment.show(activity.getSupportFragmentManager(), TAG);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.REQUEST_ERRO_PLAY_SERVICES && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }else if(requestCode == Constantes.REQUEST_CHECAR_GPS){
            if(resultCode == RESULT_OK){
                mTenativas = 0;
                mHandler.removeCallbacksAndMessages(null);
                getDeviceLocation();
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        mHandler.removeCallbacksAndMessages(null);
        super.onStop();
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent PerfilActivity = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(PerfilActivity);
            // Handle the camera action
        } else if (id == R.id.nav_estebalecimentos) {
            Intent EstabelecimentoActivity = new Intent(MainActivity.this, br.com.drinksapp.estabelecimento.EstabelecimentoActivity.class);
            startActivity(EstabelecimentoActivity);

        } else if (id == R.id.nav_produtos) {
            Intent ProdutoActivity = new Intent(MainActivity.this, br.com.drinksapp.produto.ProdutoActivity.class);
            startActivity(ProdutoActivity);

        } else if (id == R.id.nav_pedidos) {
            Intent PedidoActivity = new Intent(MainActivity.this, br.com.drinksapp.pedido.PedidoActivity.class);
            startActivity(PedidoActivity);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}