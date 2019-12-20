package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.leon_eduardo.R;

import com.example.leon_eduardo.modelo.Comunicador;
import com.example.leon_eduardo.vista.fragmentos.frg_escuchar;
import com.example.leon_eduardo.vista.fragmentos.frg_escuhar2;

public class actividad_escuchar extends AppCompatActivity implements Comunicador, View.OnClickListener, frg_escuchar.OnFragmentInteractionListener, frg_escuhar2.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_escuchar);

    }


    @Override
    public void onClick(View v) {

    }



    public void responder(String datos){
        FragmentManager fragmentManager = getSupportFragmentManager();

        frg_escuhar2 frgr = (frg_escuhar2) fragmentManager.findFragmentById(R.id.frgRecibir);
        frgr.cambiarTexto(datos);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
