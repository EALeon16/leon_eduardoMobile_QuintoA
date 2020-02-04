package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Artista;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ActividadSD extends AppCompatActivity implements  View.OnClickListener {

    EditText cajaNombres, cajaApellidos, cajaNA;
    TextView datos;
    Button botonEscribir, botonLeer;
    String lineas;
    List<Artista> listaArtistas;
    Artista_adapter adapter;
    RecyclerView recyclerViewSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_sd);
        cargarcomponentes();
    }

    private void cargarcomponentes(){
        datos = findViewById(R.id.lblSD);
        cajaNombres = findViewById(R.id.txtNombreSD);
        cajaApellidos = findViewById(R.id.txtApellidoSD);
        cajaNA = findViewById(R.id.txtNombreArtistiocoSD);
        botonEscribir = findViewById(R.id.btnEscribirSD);
        botonLeer = findViewById(R.id.btnLeerSD);
        recyclerViewSD = findViewById(R.id.recyclerSD);
        botonLeer.setOnClickListener(this);
        botonEscribir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEscribirSD:
                try{
                    File ruta = Environment.getExternalStorageDirectory();//RUTA DEL sd
                    File file = new File(ruta.getAbsoluteFile(), "archivo.txt");
                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(cajaNombres.getText().toString() + "," + cajaApellidos.getText().toString() + ","+cajaNA.getText().toString()+ ":");
                    bw.close();
                    cajaNombres.setText("");
                    cajaApellidos.setText("");
                    cajaNA.setText("");


                }catch (Exception ex){
                    Log.e("Error SD", ex.getMessage());
                }
                break;

            case R.id.btnLeerSD:

                try{
                    File rut = Environment.getExternalStorageDirectory();//RUTA DEL sd
                    File file = new File(rut.getAbsoluteFile(), "archivo.txt");
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    lineas = lector.readLine();
                    datos.setText(lector.readLine());
                    lector.close();
                    cargar_recicler();

                }catch (Exception ex){
                    Log.e("Error SD", ex.getMessage());
                }
                break;
        }

    }
    public void cargar_recicler(){
        listaArtistas = new ArrayList<Artista>();
        String[] listaAI;
        String[] listaA =  lineas.split(";");

        for(int i=0; i < listaA.length; i++){
            Artista artista = new Artista();
            listaAI = listaA[i].split(",");
            artista.setNombres(listaAI[0]);
            artista.setApellidos(listaAI[1]);
            artista.setNombreArtistico(listaAI[2]);
            listaArtistas.add(artista);
        }
        adapter = new Artista_adapter(listaArtistas);
        recyclerViewSD.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSD.setAdapter(adapter);



    }
}
