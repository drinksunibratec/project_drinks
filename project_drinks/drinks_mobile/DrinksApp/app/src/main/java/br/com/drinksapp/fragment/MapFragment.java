package br.com.drinksapp.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import br.com.drinksapp.activity.ListaProdutosActivity;
import br.com.drinksapp.activity.MainActivity;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.http.EstabelecimentoTask;
import br.com.drinksapp.interfaces.OnBackPressedListener;
import br.com.drinksapp.util.Constantes;

public class MapFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Estabelecimento>>,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnBackPressedListener {

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

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_map, container, false);
        mLoader = getLoaderManager();
        mLoader.initLoader(0, null, this);

        mListaEstabelecimentos = new ArrayList<Estabelecimento>();
        mDefaultLocation = new LatLng(-8.0475622, -34.8769643);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

        mHandler = new Handler();
        mDeveExibirDialogGPS = savedInstanceState == null;

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
    }

    @Override
    public void doBack() {
        getActivity().finish();
    }

    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        return new EstabelecimentoTask(getActivity());
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


    class MarkerListener implements GoogleMap.OnInfoWindowClickListener {


        @Override
        public void onInfoWindowClick(Marker marker) {
            Estabelecimento estabelecimento = (Estabelecimento) marker.getTag();
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
            List<Produto> produtos = null;
            Intent it = null;
            try {
                produtos = DBConnectParser.listProdutosPorEstabelecimento(estabelecimento[0]);
                retorno = new ArrayList<Produto>(produtos);
                if (retorno != null) {
                    it = new Intent(getActivity(), ListaProdutosActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.EXTRA_PRODUTO, retorno);
                    it.putExtra(Constantes.EXTRA_BUNDLE, bundle);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constantes.EXTRA_DIALOG, mDeveExibirDialogGPS);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mDeveExibirDialogGPS = savedInstanceState.getBoolean(Constantes.EXTRA_DIALOG, true);
        }

    }

    private void verificarStatusGPS() {
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
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        getDeviceLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        if (mDeveExibirDialogGPS) {
                            try {
                                status.startResolutionForResult(getActivity(), Constantes.REQUEST_CHECAR_GPS);
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

        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mPermissaoLocalizacaoConcedida = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
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
            if (mTenativas < 10) {
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
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        Constantes.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
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
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(getActivity(), Constantes.REQUEST_ERRO_PLAY_SERVICES);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            exibirMensagemErro(getActivity(), connectionResult.getErrorCode());
        }
    }

    private void exibirMensagemErro(FragmentActivity activity, final int codigoErro) {
        final String TAG = "DIALOG_ERRO_PLAY_SERVICES";

        if (getFragmentManager().findFragmentByTag(TAG) == null) {


            DialogFragment erroFragment = new DialogFragment() {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.REQUEST_ERRO_PLAY_SERVICES && resultCode == Constantes.RESULT_OK) {
            mGoogleApiClient.connect();
        } else if (requestCode == Constantes.REQUEST_CHECAR_GPS) {
            if (resultCode == Constantes.RESULT_OK) {
                mTenativas = 0;
                mHandler.removeCallbacksAndMessages(null);
                getDeviceLocation();
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        mHandler.removeCallbacksAndMessages(null);
        super.onStop();
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }


}
