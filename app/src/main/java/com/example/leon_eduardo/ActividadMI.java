package com.example.leon_eduardo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.leon_eduardo.modelo.Artista;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ActividadMI extends AppCompatActivity implements View.OnClickListener {

    Button botonGuardar, botonBT;
    EditText cajaNombres, cajaApellidos, cajaNombreA;
    TextView datos;
    RecyclerView recyclerViewMI;
    List<Artista> listaArtistas;
    Artista_adapter adapter;
    String lineas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mi);
        tomarControl();
    }


    public void tomarControl(){
        botonGuardar = findViewById(R.id.btnGuardarMI);
        botonBT = findViewById(R.id.btnBuscarTodosMI);
        cajaNombres = findViewById(R.id.txtNombresMI);
        cajaApellidos = findViewById(R.id.txtApellidosMI);
        cajaNombreA = findViewById(R.id.txtNombreAMI);
        recyclerViewMI = findViewById(R.id.recyclerMI);
        datos = findViewById(R.id.txtVer);
        botonGuardar.setOnClickListener(this);
        botonBT.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardarMI:
                try{
                    OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput("artistastex.txt", Context.MODE_APPEND));
                    escritor.write(cajaNombres.getText().toString()+ "," + cajaApellidos.getText().toString() +"," + cajaNombreA.getText().toString() + ";");
                    escritor.close();

                }catch (Exception ex){
                    Log.e("ArchivoMI", "Error de escritura" + ex.getMessage());

                }
                break;
            case R.id.btnBuscarTodosMI:
                try{
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("artistastex.txt")));
                    lineas = lector.readLine();
                    datos.setText(lineas);cargar_recicler();
                    lector.close();
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
        recyclerViewMI.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMI.setAdapter(adapter);



    }
}
