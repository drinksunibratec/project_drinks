package br.com.drinksapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.helper.SQLiteHandler;
import br.com.drinksapp.helper.SessionManager;
import br.com.drinksapp.http.EstabelecimentoTask;


public class MainActivity_old extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>, OnMapReadyCallback{

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private SQLiteHandler db;
    private SessionManager session;

    private GoogleMap mMap;

    LatLng mOrigem;

    LoaderManager mLoader;


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

        mLoader = getSupportLoaderManager();
        mLoader.initLoader(0, null, this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



//        txtName = (TextView) findViewById(R.codProduto.name);
//        txtEmail = (TextView) findViewById(R.codProduto.email);
//        btnLogout = (Button) findViewById(R.codProduto.btnLogout);
//
//        // SqLite database handler
//        db = new SQLiteHandler(getApplicationContext());
//        // session manager
//        session = new SessionManager(getApplicationContext());
//
//        if (!session.isLoggedIn()) {
//            logoutUser();
//        }
//
//        // Fetching user details from SQLite
//        HashMap<String, String> usuario = db.getUserDetails();
//        String nome = usuario.get("nome");
//        String email = usuario.get("email");
//
//        // Displaying the user details on the screen
//        txtName.setText(nome);
//        txtEmail.setText(email);
//
//        // Logout button click event
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                logoutUser();
//            }
//        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mOrigem = new LatLng(-23.561706, -46.6555981);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mOrigem, 17.0f));
        googleMap.addMarker(new MarkerOptions().position(mOrigem).title("Av. Boa Viagem").snippet("Recife"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(mOrigem).zoom(17).bearing(90).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        // Launching the login activity
        Intent intent = new Intent(MainActivity_old.this, LoginActivity.class);
        startActivity(intent);
        finish();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            Intent PerfilActivity = new Intent(MainActivity_old.this, PerfilActivity.class);
            startActivity(PerfilActivity);
            // Handle the camera action
        } else if (id == R.id.nav_estebalecimentos) {
            Intent EstabelecimentoActivity = new Intent(MainActivity_old.this, br.com.drinksapp.estabelecimento.EstabelecimentoActivity.class);
            startActivity(EstabelecimentoActivity);

        } else if (id == R.id.nav_produtos) {
            Intent ProdutoActivity = new Intent(MainActivity_old.this, br.com.drinksapp.produto.ProdutoActivity.class);
            startActivity(ProdutoActivity);

        } else if (id == R.id.nav_pedidos) {
            Intent PedidoActivity = new Intent(MainActivity_old.this, br.com.drinksapp.pedido.PedidoActivity.class);
            startActivity(PedidoActivity);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        return new EstabelecimentoTask(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, List<Estabelecimento> data) {
        if(data != null){
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }


//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMinZoomPreference(13.0f);
//        mMap.setMaxZoomPreference(21.0f);
//
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(
//                this, android.Manifest.permission.
//                        ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        googleMap.setMyLocationEnabled(true);
//        googleMap.setTrafficEnabled(true);
//        googleMap.setIndoorEnabled(true);
//        googleMap.setBuildingsEnabled(true);
//        googleMap.getUiSettings().setZoomControlsEnabled(true);
//    }
}
