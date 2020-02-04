package com.example.leon_eduardo.vista.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.vista.fragmentos.FragmentoArtistaRecycler;
import com.example.leon_eduardo.vista.fragmentos.FragmentoMI;
import com.example.leon_eduardo.vista.fragmentos.FragmentoMP;
import com.example.leon_eduardo.vista.fragmentos.FragmentoReyes;

public class main_archivo extends AppCompatActivity implements FragmentoMP.OnFragmentInteractionListener, FragmentoMI.OnFragmentInteractionListener, FragmentoReyes.OnFragmentInteractionListener,
                                                                FragmentoArtistaRecycler.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_archivo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        //caragra menu

        //metodo inflate permite agregar un menu implrmrnyasdo de archivo xml a la actividad

        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu. menu_archivos, menu);



        return true;

    }
    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent intent;
        Fragment frg = null;
        FragmentTransaction fragmentTransaction = null;


        switch(item.getItemId()) {
            case R.id.idMemoriaMI:
                frg = new FragmentoMI();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frg_archivos, frg);
                fragmentTransaction.commit();


                break;
            case R.id.idMemoriaMP:

                frg = new FragmentoMP();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frg_archivos, frg);
                fragmentTransaction.commit();



                break;
            case R.id.idMemoriaReyes:

                frg = new FragmentoReyes();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frg_archivos, frg);
                fragmentTransaction.commit();



                break;

            case R.id.idArtiostasRecycler:

                frg = new FragmentoArtistaRecycler();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frg_archivos, frg);
                fragmentTransaction.commit();



                break;
        }

        return true;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
