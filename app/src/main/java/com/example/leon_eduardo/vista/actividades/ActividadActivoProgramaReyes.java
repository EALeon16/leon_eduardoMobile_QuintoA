package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActividadActivoProgramaReyes extends AppCompatActivity {

    TextView datos;
    RecyclerView recycler;

    Artista_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_activo_programa_reyes);
        cargarComponentes();
        obtenerReyes();
    }


    private void cargarComponentes(){
        datos = findViewById(R.id.txtReyes);
        recycler = findViewById(R.id.actividadReyes);
    }

    private void obetnerRecyclerArchico(){
        try{
            InputStream input = getResources().openRawResource(R.raw.reyes);
            BufferedReader lector = new BufferedReader(new InputStreamReader(input));
            String informacion = lector.readLine();
             datos.setText(informacion);

        }catch (Exception ex){

        }
    }

    private  void obtenerReyes(){
        try{
            DocumentBuilder bilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = bilder.parse(getResources().openRawResource(R.raw.reyes), null);
            NodeList listagodos = doc.getElementsByTagName("godo");

            Toast.makeText(this, "numero de reyes  " + listagodos.getLength(), Toast.LENGTH_SHORT).show();
            datos.setText("numero de reyes" + listagodos.getLength());
            String result = "";
            for(int i = 0; i < listagodos.getLength(); i ++){

                Node Reyes = listagodos.item(i);
                Element element2 =  (Element) Reyes;
                datos.setText(datos.getText()+"\nNombre: "+ getValue("nombre", element2)+"\n");
                datos.setText(datos.getText()+"\nPeriodo: "+ getValue("periodo", element2)+"\n");



            }


        }catch(Exception ex){

        }
    }

    private static String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();

    }
}
