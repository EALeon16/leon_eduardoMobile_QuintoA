package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Alumno;
import com.example.leon_eduardo.vista.adapter.Alumno_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ActividadSWAlumnos extends AppCompatActivity implements View.OnClickListener {

    EditText cajaID, cajaNombres, cajaDireccion;
    Button btnGuardar, btnModificar, btnEliminar, btnBuscarTodos, btnBuscarID;
    TextView datos;
    RecyclerView recyclerAlumnosSW;
    Alumno_Adapter adapter;
    List<Alumno> listaAlumno;


    // definir URLS delo servicio web
    String host = "http://reneguaman.000webhostapp.com";
    String insert = "/insertar_alumno.php";
    String get = "/obtener_alumnos.php";
    String getById = "/obtener_alumno_por_id.php";
    String update ="/actualizar_alumno.php";
    String delete = "/borrar_alumno.php";

    Servicioweb sw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_swalumnos);
        cargarcComponentes();


    }
    /////////acceder al servicio web mediante un hilo

    class Servicioweb extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... parametros) {

            String consulta = "";
            String [] cadena;

            URL url = null;
            String ruta = parametros[0];//la ruta htpp//..../obteneralumons/php

            if(parametros[1].equals("1") || parametros[1].equals("3") ){
                try {
                    url = new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();
                    if (codigoRespuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                        consulta += lector.readLine();
                    }else{
                        consulta += "no hubo conexión";
                    }
                }catch (Exception ex){
                    Log.e("mensaje","no hubo coneccion");
                }
                //consulta = parametros[1] + "-" + consulta;
                Log.e("aaaa", " esta es la consulta" + consulta);


            }
            else if(parametros[1].equals("2")){
                try{
                    url = new URL(ruta);

                    URLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoOutput(true);
                    conexion.setDoInput(true);
                    //conexion.setUseCaches(false);
                    conexion.connect();
                    JSONObject json = new JSONObject();
                    json.put("nombre", parametros[2]);
                    json.put("direccion", parametros[3]);


                    OutputStream os =  conexion.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(json.toString());

                    writer.flush();
                    writer.close();
                    int respuesta = ((HttpURLConnection) conexion).getResponseCode();
                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        consulta += lector.readLine();
                    }

                }catch (Exception e){
                    Log.e("aaaaa", "ERROR: NO INGRESO AL TRY");

                }
                consulta = parametros[1] + "-consula ingresado" + consulta;
                Log.e("aaaaa", "AAAAAAAAAAAAAAAAAAAA" + consulta);


            }else if(parametros[1].equals("5")){
                try {
                    url = new URL(ruta);
                    URLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoInput(true);
                    conexion.setDoOutput(true);
                    conexion.setUseCaches(false);
                    conexion.setRequestProperty("Content-Type", "application/json");
                    conexion.setRequestProperty("Accept", "application/json");
                    conexion.connect();

                    //se crea el json
                    JSONObject json = new JSONObject();
                    json.put("idalumno", parametros[2]);
                    json.put("nombre", parametros[3]);
                    json.put("direccion", parametros[4]);

                    // Envio los parámetros post.
                    OutputStream os = conexion.getOutputStream();
                    BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    escritor.write(json.toString());
                    escritor.flush();
                    escritor.close();

                    int respuesta = ((HttpURLConnection) conexion).getResponseCode();
                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        consulta += lector.readLine();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                consulta = parametros[1] + "-" + consulta;

            }
            else if(parametros[1].equals("4")){
                try {
                    url = new URL(ruta);
                    URLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoInput(true);
                    conexion.setDoOutput(true);
                    conexion.setUseCaches(false);
                    conexion.setRequestProperty("Content-Type", "application/json");
                    conexion.setRequestProperty("Accept", "application/json");
                    conexion.connect();

                    //se crea el json
                    JSONObject json = new JSONObject();
                    json.put("idalumno", parametros[2]);

                    // Envio los parámetros post.
                    OutputStream os = conexion.getOutputStream();
                    BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    escritor.write(json.toString());
                    escritor.flush();
                    escritor.close();

                    int respuesta = ((HttpURLConnection) conexion).getResponseCode();
                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        consulta += lector.readLine();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                consulta = parametros[1] + "-" + consulta;
            }

                return consulta;
        }
        @Override
        protected void onPostExecute(String s){

            datos.setText(s);
            cargarRecycler(s);
            cargarByYD(s);
        }


    }




    public void cargarcComponentes(){
        cajaID = findViewById(R.id.txtIdAlumnoSW);
        cajaNombres = findViewById(R.id.idAgregarNombre);
        cajaDireccion = findViewById(R.id.idAgregarDireccion);
        datos = findViewById(R.id.textVerAlumnosSW);
        btnGuardar = findViewById(R.id.btnAgregarAlumnoSW);
        btnGuardar.setOnClickListener(this);
        btnModificar = findViewById(R.id.btnEditarAulumnoSW);
        btnModificar.setOnClickListener(this);
        btnEliminar = findViewById(R.id.btnEliminarAlumnoSW);
        btnEliminar.setOnClickListener(this);
        btnBuscarTodos = findViewById(R.id.btnListarAlumnosSW);
        btnBuscarTodos.setOnClickListener(this);
        btnBuscarID = findViewById(R.id.btnBuscarAlumnoSW);
        btnBuscarID.setOnClickListener(this);
        recyclerAlumnosSW = findViewById(R.id.idrecyclerAlumnoSW);


    }

    private void cargarByYD(String cadena){
        JSONObject json = null;
        try {
            json = new JSONObject(cadena);
            String estado = json.getString("estado");
            List<Alumno> lista = new ArrayList<Alumno>();
            if(estado.equals("1")){
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(json.getJSONObject("alumno").getInt("idAlumno"));
                alumno.setNombre(json.getJSONObject("alumno").getString("nombre"));
                alumno.setDireccion(json.getJSONObject("alumno").getString("direccion"));
                lista.add(alumno);
                cargarLista(lista);
            }else{
                Toast.makeText(getApplicationContext(),"No existe ese estudiante", Toast
                        .LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void cargarRecycler(String json){

        try{

            JSONObject jsonObject = new JSONObject(json);

            Log.e("aaa", "pasa");
            JSONArray alumnos = jsonObject.getJSONArray("alumnos");

            listaAlumno = new ArrayList<>();
            for (int i = 0; i< alumnos.length() ; i++){
                JSONObject a = alumnos.getJSONObject(i);
                Alumno alumnoR = new Alumno();
                alumnoR.setIdAlumno(a.getInt("idalumno"));

                alumnoR.setNombre(a.getString("nombre"));
                alumnoR.setDireccion(a.getString("direccion"));
                listaAlumno.add(alumnoR);
            }
            adapter = new Alumno_Adapter(listaAlumno);
            adapter.setOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarDatos(v);
                }
            });
            recyclerAlumnosSW.setLayoutManager(new LinearLayoutManager(this));
            recyclerAlumnosSW.setAdapter(adapter);

        }catch (Exception ex){

        }
    }

    private void cargarDatos(View v){
        int id = listaAlumno.get(recyclerAlumnosSW.getChildAdapterPosition(v)).getIdAlumno();
        String nombre= listaAlumno.get(recyclerAlumnosSW.getChildAdapterPosition(v)).getNombre();
        String direccion = listaAlumno.get(recyclerAlumnosSW.getChildAdapterPosition(v)).getDireccion();
        cajaID.setText(id+"");
        cajaNombres.setText(nombre+"");
        cajaDireccion.setText(direccion+"");
    }


    public void cargarLista(List<Alumno> lista){
        listaAlumno = new ArrayList<Alumno>();
        listaAlumno = lista;
        recyclerAlumnosSW.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Alumno_Adapter(listaAlumno);
        adapter.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno alumno = listaAlumno.get(recyclerAlumnosSW.getChildAdapterPosition(v));
                cajaID.setText(alumno.getIdAlumno() + "");
                cajaNombres.setText(alumno.getNombre());
                cajaDireccion.setText(alumno.getDireccion());
            }
        });
        recyclerAlumnosSW.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

       sw = new Servicioweb();

        switch (v.getId()){
            case R.id.btnListarAlumnosSW:
                sw.execute(host.concat(get),"1");

                break;

            case R.id.btnAgregarAlumnoSW:
                sw.execute(host.concat(insert), "2" , cajaNombres.getText().toString(), cajaDireccion.getText().toString());

                break;

            case  R.id.btnBuscarAlumnoSW:
                sw.execute(host.concat(getById) + "?idalumno=" + cajaID.getText().toString(),"3");
                break;
            case R.id.btnEliminarAlumnoSW:
                sw.execute(host.concat(delete),"4", cajaID.getText().toString());
                break;
            case R.id.btnEditarAulumnoSW:
                sw.execute(host.concat(update), "5", cajaID.getText().toString(), cajaNombres.getText().toString(), cajaDireccion.getText().toString());
                break;


        }

    }


}
