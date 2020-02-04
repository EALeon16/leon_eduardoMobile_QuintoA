package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.leon_eduardo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ActividadSWEjercicio extends AppCompatActivity implements View.OnClickListener {

    TextView ver, wea;
    Button btnver;
    String host = "https://samples.openweathermap.org/";
    String get = "/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22";
    Servicioweb sw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_swejercicio);
        cargarComponentes();

    }

    public void cargarComponentes(){
        ver = findViewById(R.id.verSWHILO);
        btnver = findViewById(R.id.verSWEjercicio);
        wea = findViewById(R.id.verSWweather);
        btnver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        sw = new Servicioweb();

        sw.execute(host.concat(get));




    }


    class Servicioweb extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... parametros) {
            String consulta = "";
            String ruta = parametros[0];
                try {

                    URL url = new URL(ruta);
                    Log.e("aa", "try" + "entro al try2");
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();
                    if (codigoRespuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                        consulta += lector.readLine();
                        lector.close();

                    }else {
                        consulta += "no hubo conexión";
                    }
                }catch (Exception ex){
                    Log.e("mensaje","no hubo coneccion");
                }
                //consulta = parametros[1] + "-" + consulta;
                Log.e("aaaa", " esta es la consulta" + consulta);




            return consulta;
        }
        @Override
        protected void onPostExecute(String s){

            //ver.setText(s);
            cargarDatos(s);
           // cargarW(s);
        }
    }

    public void cargarDatos(String cadena){
        String line = "";
        String leerb= "";
        try{

            JSONObject json = new JSONObject(cadena);
            String lon = json.getJSONObject("coord").getString("lon");
            String lat = json.getJSONObject("coord").getString("lat");





            line += "Longitud = " + lon + "\n";
            line += "Latitud = " + lat + "\n";

            //////weather






            ////////base

            JSONObject base = new JSONObject(cadena);

            String bases = base.getString("base");
            String temp = base.getJSONObject("main").getString("temp");
            String pressure = base.getJSONObject("main").getString("pressure");
            String humidity = base.getJSONObject("main").getString("humidity");
            String temp_min = base.getJSONObject("main").getString("temp_min");
            String temp_max = base.getJSONObject("main").getString("temp_max");

            leerb += "base = "+ bases + "\n";
            leerb += "temp = "+ temp + "\n";
            leerb += "pressure = "+ pressure + "\n";
            leerb += "humidity = "+ humidity + "\n";
            leerb += "temp_min = "+ temp_min + "\n";
            leerb += "temp_max = "+ temp_max + "\n";



            ver.setText(line);
            wea.setText(leerb);



        }catch (Exception e){

        }

    }


    public void cargarW(String cadena){
        String leerw = "";
        try{
            JSONObject weather = new JSONObject(cadena);
            String wid = weather.getJSONObject("weather").getString("id");
            String main = weather.getJSONObject("weather").getString("main");
            String desc = weather.getJSONObject("weather").getString("description");
            String icon = weather.getJSONObject("weather").getString("icon");



            leerw+= "id = " + wid + "\n";
            leerw += "main = " + main + "\n";
            leerw += "description = " + desc + "\n";
            leerw += "icon = " + icon + "\n";
            //wea.setText(leerw);

        }catch (Exception e){

        }
    }



}
