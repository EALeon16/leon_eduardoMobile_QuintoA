package com.example.leon_eduardo.vista;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.leon_eduardo.MainActivity;
import com.example.leon_eduardo.R;
import com.example.leon_eduardo.vista.actividades.ActividadCarroORM;
import com.example.leon_eduardo.vista.actividades.ActividadEnviarParametors;
import com.example.leon_eduardo.vista.actividades.ActividadProducto;
import com.example.leon_eduardo.vista.actividades.ActividadSD;
import com.example.leon_eduardo.vista.actividades.ActividadSWAlumnos;
import com.example.leon_eduardo.vista.actividades.actividad_escuchar;
import com.example.leon_eduardo.vista.actividades.activity_login;
import com.example.leon_eduardo.vista.actividades.activity_sumar;
import com.example.leon_eduardo.vista.actividades.main_archivo;
import com.example.leon_eduardo.vista.fragmentos.fragmento;
import com.example.leon_eduardo.vista.fragmentos.frg_escuchar;
import com.example.leon_eduardo.vista.fragmentos.frg_uno;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class navigation_drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, frg_uno.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_dlgSumar, R.id.nav_share, R.id.nav_send, R.id.nav_Login,R.id.nav_Parametroe, R.id.nav_Suma, R.id.opcionSD, R.id.opcionHelper, R.id.opcionORM,
                R.id.opcionColores, R.id.opcionEscuchar, R.id.opcionAgregarartista, R.id.opcionMI, R.id.opcionMP, R.id.nav_verarchivo)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Fragment fragment = null;
                if(destination.getId() == R.id.nav_Login){
                    intent = new Intent(navigation_drawer.this, activity_login.class);
                    startActivity(intent);
                }else if(destination.getId() == R.id.nav_Suma){
                    intent = new Intent(navigation_drawer.this, activity_sumar.class);
                    startActivity(intent);


                }else if(destination.getId() == R.id.nav_Parametroe){
                    intent = new Intent(navigation_drawer.this, ActividadEnviarParametors.class);
                    startActivity(intent);

                }else if(destination.getId() == R.id.nav_dlgSumar){
                    final Dialog dlgSumar = new Dialog(navigation_drawer.this);
                    dlgSumar.setContentView(R.layout.dlg_sumar);

                    final EditText cajaN1 = dlgSumar.findViewById(R.id.txtN1dlg);
                    final EditText cajaN2 = dlgSumar.findViewById(R.id.txtN2dlg);
                    Button botonsumarlDlg= dlgSumar.findViewById(R.id.btnSumardlg);
                    botonsumarlDlg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int valor1 = Integer.parseInt(cajaN1.getText().toString());
                            int valor2 = Integer.parseInt(cajaN2.getText().toString());
                            int suma = valor1 + valor2;
                            Toast.makeText(navigation_drawer.this, "La suma es: " + suma, Toast.LENGTH_SHORT).show();
                            dlgSumar.hide();
                        }
                    });

                    dlgSumar.show();

                }else if(destination.getId() == R.id.opcionHelper){
                    intent = new Intent(navigation_drawer.this, ActividadProducto.class);
                    startActivity(intent);

                }else if(destination.getId() == R.id.opcionColores){
                    intent = new Intent(navigation_drawer.this, fragmento.class);
                    startActivity(intent);
                }else if(destination.getId() == R.id.opcionEscuchar){
                    intent = new Intent(navigation_drawer.this, actividad_escuchar.class);
                    startActivity(intent);
                }else if(destination.getId() == R.id.nav_verarchivo){
                        intent = new Intent(navigation_drawer.this, main_archivo.class);
                        startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu. main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){


        Intent intent;
        switch(item.getItemId()) {
            case R.id.opcionLogin:
                intent = new Intent(navigation_drawer.this, activity_login.class);
                startActivity(intent);
                break;
            case R.id.opcionSuma:
                intent = new Intent(navigation_drawer.this, activity_sumar.class);
                startActivity(intent);
                break;
            case R.id.opcionParametroe:
                intent = new Intent(navigation_drawer.this, ActividadEnviarParametors.class);
                startActivity(intent);
                break;
            case R.id.dlgSumar:
                final Dialog dlgSumar = new Dialog(navigation_drawer.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);

                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtN1dlg);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txtN2dlg);
                Button botonsumarlDlg= dlgSumar.findViewById(R.id.btnSumardlg);
                botonsumarlDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int valor1 = Integer.parseInt(cajaN1.getText().toString());
                        int valor2 = Integer.parseInt(cajaN2.getText().toString());
                        int suma = valor1 + valor2;
                        Toast.makeText(navigation_drawer.this, "La suma es: " + suma, Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });

                dlgSumar.show();
                break;

            case R.id.opcionColores:
                intent = new Intent(navigation_drawer.this, fragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionEscuchar:
                intent = new Intent(navigation_drawer.this, actividad_escuchar.class);
                startActivity(intent);
                break;

            case R.id.opcionHelper:
                intent = new Intent(navigation_drawer.this, ActividadProducto.class);
                startActivity(intent);
                break;
            case R.id.opcionArchivos2:
                intent = new Intent(navigation_drawer.this, main_archivo.class);
                startActivity(intent);

                break;
            case R.id.opcionSD:
                intent = new Intent(navigation_drawer.this, ActividadSD.class);
                startActivity(intent);

                break;
            case R.id.opcionORM:
                intent = new Intent(navigation_drawer.this, ActividadCarroORM.class);
                startActivity(intent);

                break;
            case R.id.opcionHilo:
                intent = new Intent(navigation_drawer.this, ActividadSWAlumnos.class);
                startActivity(intent);

                break;



        }




        return  super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
