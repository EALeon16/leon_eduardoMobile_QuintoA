package com.example.leon_eduardo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.leon_eduardo.modelo.Artista;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActividadMP extends AppCompatActivity implements View.OnClickListener {

    Button botonLeer;
    TextView datos;
    RecyclerView recyclerViewMP;
    Artista_adapter adapter;
    List<Artista> listaArtistas;
    String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mp);
        tomarControl();

    }


    public void tomarControl(){

        botonLeer = findViewById(R.id.btnLeerMP);
        datos = findViewById(R.id.txtVerMP);
        botonLeer.setOnClickListener(this);
        recyclerViewMP = findViewById(R.id.recyclerMP);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLeerMP:
                try{
                    InputStream input = getResources().openRawResource(R.raw.archivoraw);
                    BufferedReader lector = new BufferedReader(new InputStreamReader(input));
                    cadena = lector.readLine();
                    cargar_recicler();
                }catch (Exception ex){
                    Log.e("ArchivoMI", "Error de escritura" + ex.getMessage());

                }
                break;

    }
    }

    public void cargar_recicler(){
        listaArtistas = new ArrayList<Artista>();
        String[] listaAI;
        String[] listaA =  cadena.split(";");

        for(int i=0; i < listaA.length; i++){
            Artista artista = new Artista();
            listaAI = listaA[i].split(",");
            artista.setNombres(listaAI[0]);
            artista.setApellidos(listaAI[1]);
            artista.setNombreArtistico(listaAI[2]);
            listaArtistas.add(artista);
        }
        adapter = new Artista_adapter(listaArtistas);
        recyclerViewMP.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMP.setAdapter(adapter);



    }
}
