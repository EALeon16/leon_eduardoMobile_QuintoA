package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Automovil;
import com.example.leon_eduardo.vista.adapter.Automovil_adapter;

import java.util.ArrayList;
import java.util.List;

public class ActividadCarroORM extends AppCompatActivity implements View.OnClickListener{

    EditText cajaPlaca, cajaModelo, cajaMarca, cajaanio;
    Button btnAgregar, btnEditar;
    Button btnEliminar, btnEliminarPlaca, btnListar, btnListarPlaca;
    List<Automovil> listaAuto;
    TextView ver;
    RecyclerView recyclerAuto;
    Automovil_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_carro_orm);
        cargarComponentes();
        cargarRecycler();
    }

    public void cargarComponentes(){
        cajaPlaca = findViewById(R.id.idPlaca);
        cajaModelo = findViewById(R.id.idModelo);
        cajaMarca = findViewById(R.id.idMarca);
        cajaanio = findViewById(R.id.idAnio);

        btnAgregar = findViewById(R.id.btnAgregarAuto);
        btnEditar = findViewById(R.id.btnEditarAuto);
        btnEliminar = findViewById(R.id.btnEliminarAllAuto);
        btnEliminarPlaca = findViewById(R.id.btnEliminarSeleccionAuto);
        btnListar = findViewById(R.id.btnListarA);
        btnListarPlaca = findViewById(R.id.btnBuscarAuto);
        ver = findViewById(R.id.textVerAutos);
        btnAgregar.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnEliminarPlaca.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnListarPlaca.setOnClickListener(this);
        recyclerAuto = findViewById(R.id.idrecyclerAuto);


    }

    public void cargarRecycler(){
        listaAuto  = Automovil.getAll();
        adapter = new Automovil_adapter(listaAuto);
        recyclerAuto.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClick((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Automovil autos = listaAuto.get(recyclerAuto.getChildAdapterPosition(v));
                String placa = listaAuto.get(recyclerAuto.getChildAdapterPosition(v)).getPlaca();
                String modelo = listaAuto.get(recyclerAuto.getChildAdapterPosition(v)).getModelo();
                String marca = listaAuto.get(recyclerAuto.getChildAdapterPosition(v)).getMarca();
                int anio = listaAuto.get(recyclerAuto.getChildAdapterPosition(v)).getAnio();

                cajaPlaca.setText(placa);
                cajaModelo.setText(modelo);
                cajaMarca.setText(marca);
                cajaanio.setText(anio+"");


           btnEditar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String cP = cajaPlaca.getText().toString();
                   String CMa = cajaModelo.getText().toString();
                   String cMo = cajaMarca.getText().toString();
                   String cA = cajaanio.getText().toString();

                   if(cP.length() != 0 && CMa.length() != 0 && cMo.length() != 0 && cA.length() != 0){
                       autos.setModelo(cajaModelo.getText().toString());
                       autos.setMarca(cajaMarca.getText().toString());
                       autos.setAnio(Integer.parseInt(cajaanio.getText().toString()));
                       autos.save();

                       Toast.makeText(ActividadCarroORM.this, "Datos modificados correctamente" , Toast.LENGTH_SHORT).show();
                       cajaPlaca.setText("");
                       cajaModelo.setText("");
                       cajaMarca.setText("");
                       cajaanio.setText("");
                       cargarRecycler();
                   }else{
                       Toast.makeText(ActividadCarroORM.this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
                   }




               }
           });
            }
        }));
        recyclerAuto.setAdapter(adapter);



    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnAgregarAuto:
                String n1 = cajaPlaca.getText().toString();
                String n2 = cajaModelo.getText().toString();
                String n3 = cajaMarca.getText().toString();
                String n4 = cajaanio.getText().toString();

                if(n1.length() != 0 && n2.length() != 0 && n3.length() != 0 && n4.length() != 0){
                    Automovil carrro = new Automovil();
                    carrro.setPlaca(cajaPlaca.getText().toString());
                    carrro.setModelo(cajaModelo.getText().toString());
                    carrro.setMarca(cajaMarca.getText().toString());
                    carrro.setAnio(Integer.parseInt(cajaanio.getText().toString()));
                    carrro.save();
                    Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
                    cajaPlaca.setText("");
                    cajaModelo.setText("");
                    cajaMarca.setText("");
                    cajaanio.setText("");
                    cargarRecycler();

                }else{
                    Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }
                /*if(n1.length() == 0 && n2.length() == 0 && n3.length() == 0 && n4.length() == 0 ){
                    Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();

                }else{
                    Automovil carrro = new Automovil();
                    carrro.setPlaca(cajaPlaca.getText().toString());
                    carrro.setModelo(cajaModelo.getText().toString());
                    carrro.setMarca(cajaMarca.getText().toString());
                    carrro.setAnio(Integer.parseInt(cajaanio.getText().toString()));
                    carrro.save();
                    Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
                    cajaPlaca.setText("");
                    cajaModelo.setText("");
                    cajaMarca.setText("");
                    cajaanio.setText("");
                }*/


                break;

            case R.id.btnListarA:
                ver.setText("Numero de carros" + Automovil.getAll().size());
                cargarRecycler();
                break;

            case R.id.btnEliminarAllAuto:
                Automovil cElimiarAll = new Automovil();
                cElimiarAll.EliminarAll();
                Toast.makeText(this, "Se ha eliminado toda la informacion", Toast.LENGTH_SHORT).show();
                cajaPlaca.setText("");
                cajaModelo.setText("");
                cajaMarca.setText("");
                cajaanio.setText("");
                cargarRecycler();
                break;
            case R.id.btnEliminarSeleccionAuto:
                String cap = cajaPlaca.getText().toString();
                if(cap.length() == 0){
                    Toast.makeText(this, "Selecciona Automovil", Toast.LENGTH_SHORT).show();
                }else{
                    String placa = cajaPlaca.getText().toString();
                    Automovil cElimiarSeleccion = new Automovil();
                    cElimiarSeleccion.EliminarByPlaca(placa);
                    Toast.makeText(this, "Vehiculo eliminado", Toast.LENGTH_SHORT).show();
                    cajaPlaca.setText("");
                    cajaModelo.setText("");
                    cajaMarca.setText("");
                    cajaanio.setText("");
                    cargarRecycler();
                }

                break;


            case R.id.btnBuscarAuto:
                String placas = cajaPlaca.getText().toString();



                if(placas.length() == 0){
                    Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
                }else{
                    Automovil c = new Automovil();
                    c = Automovil.getCarbyPlaca(placas);
                    Log.e("es" , ""+c.getPlaca() + "" + c.getAnio());

                    Automovil auto = new Automovil();
                    auto.setPlaca(c.getPlaca());
                    auto.setModelo(c.getModelo());
                    auto.setMarca(c.getMarca());
                    auto.setAnio(c.getAnio());
                    listaAuto = new ArrayList<Automovil>();

                    listaAuto.add(auto);

                    adapter = new Automovil_adapter(listaAuto);
                    recyclerAuto.setLayoutManager(new LinearLayoutManager(this));
                    recyclerAuto.setAdapter(adapter);


                }
                break;

        }
    }
}
