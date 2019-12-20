package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leon_eduardo.R;

import java.util.ArrayList;
import java.util.List;

import com.example.leon_eduardo.modelo.Artista;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

public class ActividadRecycleArtistas extends AppCompatActivity {

    RecyclerView recyclerViewArtistas;
    Artista_adapter adapter;
    List<Artista> listaArtistas;
    Button boton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_recycle_artistas);
        tomarControl();
        cargarRecycler();
    }

    private void tomarControl(){
        recyclerViewArtistas = findViewById(R.id.recyclerArtista);


    }

    private void cargarRecycler(){


        Artista artista1 = new Artista();
        artista1.setNombres("Luis ");
        artista1.setApellidos(" Miguel ");
        artista1.setNombreArtistico(" Luismi");
        artista1.setFoto(R.drawable.luis);

        Artista artista2 = new Artista();
        artista2.setNombres(" Don");
        artista2.setApellidos(" Merardo ");
        artista2.setNombreArtistico(" Donme");
        artista2.setFoto(R.drawable.merardo);






        listaArtistas = new ArrayList<Artista>();

        listaArtistas.add(artista1);
        listaArtistas.add(artista2);

        adapter = new Artista_adapter(listaArtistas);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArtistas.setAdapter(adapter);

    }


    public void onClick(View v) {




        Toast.makeText(getApplicationContext(), "Hola soy un Toast", Toast.LENGTH_SHORT).show();





    }
}
