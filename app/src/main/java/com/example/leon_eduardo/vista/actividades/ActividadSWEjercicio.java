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
import java.util.WeakHashMap;

public class ActividadSWEjercicio extends AppCompatActivity implements View.OnClickListener {

    TextView ver, wea, leervisibility, lclouds, ldt, lcod, leerBase;
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
        leervisibility = findViewById(R.id.verSWwVisibility);
        lclouds = findViewById(R.id.verSWClouds);
        ldt = findViewById(R.id.verSWdt);
        lcod = findViewById(R.id.verSWwcod);
        leerBase = findViewById(R.id.verSWbase);
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




            line += "COORD" +  "\n";
            line += "Longitud = " + lon + "\n";
            line += "Latitud = " + lat + "\n";

            //////weather

            String idW = "";
            String main = "";
            String description = "";
            String icon = "";

            String datosW = "";


            JSONObject weather = new JSONObject(cadena);
            JSONArray clima = weather.getJSONArray("weather");
            for(int i =0; i<clima.length(); i++){
                JSONObject c = clima.getJSONObject(i);
                idW = c.getString("id");
                main = c.getString("main");
                description = c.getString("description");
                icon = c.getString("icon");

            }
            datosW += "WEATHER" +  "\n";
            datosW += "id = " + idW + "\n";
            datosW += "main = " + main + "\n";
            datosW += "description = " + description+ "\n";
            datosW += "icon = " + icon + "\n";






            ////////base

            JSONObject base = new JSONObject(cadena);

            String bases = base.getString("base");
            String temp = base.getJSONObject("main").getString("temp");
            String pressure = base.getJSONObject("main").getString("pressure");
            String humidity = base.getJSONObject("main").getString("humidity");
            String temp_min = base.getJSONObject("main").getString("temp_min");
            String temp_max = base.getJSONObject("main").getString("temp_max");

            leerb += "BASE"+ "\n";
            leerb += "base = "+ bases + "\n";
            leerb += "temp = "+ temp + "\n";
            leerb += "pressure = "+ pressure + "\n";
            leerb += "humidity = "+ humidity + "\n";
            leerb += "temp_min = "+ temp_min + "\n";
            leerb += "temp_max = "+ temp_max + "\n";
            ///////////visibility


            JSONObject visibility = new JSONObject(cadena);
            String datosV = "";

            String visi = visibility.getString("visibility");
            String speed = visibility.getJSONObject("wind").getString("speed");
            String deg = visibility.getJSONObject("wind").getString("deg");
            datosV += "VISIBILITY"+ "\n";
            datosV += "visibility = "+ visi + "\n";
            datosV += "speed = "+ speed + "\n";
            datosV += "deg = "+ deg + "\n";
///////clouds

            JSONObject clouds = new JSONObject(cadena);
            String datosC = "";

            String all = clouds.getJSONObject("clouds").getString("all");
            datosC += "CLOUDS"+"\n";
            datosC += "all = "+ all + "\n";

            JSONObject dt = new JSONObject(cadena);
            String datosDT = "";

            String dtn = dt.getString("dt");
            String type = dt.getJSONObject("sys").getString("type");
            String idDT = dt.getJSONObject("sys").getString("id");
            String message = dt.getJSONObject("sys").getString("message");
            String country = dt.getJSONObject("sys").getString("country");
            String sunrise = dt.getJSONObject("sys").getString("sunrise");
            String sunset = dt.getJSONObject("sys").getString("sunset");

            datosDT += "DT"+ "\n";
            datosDT += "dt = "+ dtn + "\n";
            datosDT += "type = "+ type + "\n";
            datosDT += "id = "+ idDT + "\n";
            datosDT += "message = "+ message + "\n";
            datosDT += "country = "+ country + "\n";
            datosDT += "sunrise = "+ sunrise + "\n";
            datosDT += "sunset = "+ sunset + "\n";

            JSONObject datos = new JSONObject(cadena);
            String leerD = "";

            String id = datos.getString("id");
            String name = datos.getString("name");
            String cod = datos.getString("cod");

            leerD +=  "id = "+ id + "\n";
            leerD +=  "name = "+ name + "\n";
            leerD +=  "cod = "+ cod + "\n";






            ver.setText(line);
            wea.setText(leerb);
            leervisibility.setText(datosV);
            lclouds.setText(datosC);
            ldt.setText(datosDT);
            lcod.setText(leerD);
            leerBase.setText(datosW);



        }catch (Exception e){

        }

    }




}
