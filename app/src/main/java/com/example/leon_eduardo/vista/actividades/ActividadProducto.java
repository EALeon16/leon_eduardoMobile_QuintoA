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

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.controlador.HelperProducto;
import com.example.leon_eduardo.modelo.Producto;
import com.example.leon_eduardo.vista.adapter.Producto_adapter;

import java.util.List;

public class ActividadProducto extends AppCompatActivity implements View.OnClickListener {


    EditText cajaCodigo, cajaDescripcion, cajaPrecio, cajaCantidad;
    Button agregarProducto, listarproducto, listar, eliminarT, eliminarS, editar;
    List<Producto> listaProductos;
    HelperProducto helperProducto;
    RecyclerView recyclerProducto;
    Producto_adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_producto);
        cargarComponenentes();
    }


    public void cargarComponenentes(){
        cajaCodigo = findViewById(R.id.idPcodigo);
        cajaDescripcion = findViewById(R.id.idPdescripcion);
        cajaPrecio = findViewById(R.id.idPprecio);
        cajaCantidad = findViewById(R.id.idPcantidad);
        agregarProducto = findViewById(R.id.btnAgregarproducto);
        listar = findViewById(R.id.btnListar);
        listarproducto = findViewById(R.id.btnListarProducto);
        eliminarT = findViewById(R.id.btnEliminarAll);
        eliminarT.setOnClickListener(this);
        eliminarS = findViewById(R.id.btnEliminarSeleccion);
        eliminarS.setOnClickListener(this);
        agregarProducto.setOnClickListener(this);
        listarproducto.setOnClickListener(this);
        listar.setOnClickListener(this);
        helperProducto = new HelperProducto(this, "PruductoBD", null, 1);
        recyclerProducto = findViewById(R.id.idrecyclerProducto);
        editar = findViewById(R.id.btnEditar);
        editar.setOnClickListener(this);

    }

    public void cargarRecycler(){
        listaProductos  = helperProducto.getAll();
        adapter = new Producto_adapter(listaProductos);
        recyclerProducto.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClick((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = listaProductos.get(recyclerProducto.getChildAdapterPosition(v)).getCodigo();
                String descripcion = listaProductos.get(recyclerProducto.getChildAdapterPosition(v)).getDescripcion();
                double precio = listaProductos.get(recyclerProducto.getChildAdapterPosition(v)).getPrecio();
                int cantidad = listaProductos.get(recyclerProducto.getChildAdapterPosition(v)).getCantidad();
                Log.e("codigo",""+codigo);
                cajaCodigo.setText(codigo+"");
                cajaDescripcion.setText(descripcion);
                cajaPrecio.setText(precio+"");
                cajaCantidad.setText(cantidad+"");
            }
        }));
        recyclerProducto.setAdapter(adapter);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregarproducto:
                Producto p = new Producto();
                p.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                p.setDescripcion(cajaDescripcion.getText().toString());
                p.setPrecio(Double.parseDouble(cajaPrecio.getText().toString()));
                p.setCantidad(Integer.parseInt(cajaCantidad.getText().toString()));
                helperProducto.insertar(p);
                break;
            case R.id.btnListarProducto:
                String code = cajaCodigo.getText().toString();
                listaProductos = helperProducto.getProductoByCode(code);
                adapter = new Producto_adapter(listaProductos);
                recyclerProducto.setLayoutManager(new LinearLayoutManager(this));
                recyclerProducto.setAdapter(adapter);
                break;
            case R.id.btnListar:
                cargarRecycler();

                break;
            case R.id.btnEliminarAll:
                helperProducto.eliminarAll();


                break;

            case R.id.btnEliminarSeleccion:
                int codigo = Integer.parseInt(cajaCodigo.getText().toString());

                Producto pe = new Producto();
                pe.setCodigo(codigo);
                helperProducto.eliminar(pe);

                break;
            case R.id.btnEditar:
                Producto p2 = new Producto();
                p2.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                p2.setDescripcion(cajaDescripcion.getText().toString());
                p2.setPrecio(Double.parseDouble(cajaPrecio.getText().toString()));
                p2.setCantidad(Integer.parseInt(cajaCantidad.getText().toString()));
                helperProducto.modificar(p2);
                break;
        }


    }



}
