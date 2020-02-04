package com.example.leon_eduardo.vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Producto;

import java.util.List;

public class Producto_adapter extends RecyclerView.Adapter<Producto_adapter.ViewHolderProducto> implements View.OnClickListener {
    List<Producto> lista;
    public View.OnClickListener cliclk;

    public Producto_adapter(List<Producto> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_producto, null);
        view.setOnClickListener(this);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderProducto viewHolderProducto, int pos) {
        viewHolderProducto.datoCodigo.setText(lista.get(pos).getCodigo()+"");
        viewHolderProducto.datoDescripcion.setText(lista.get(pos).getDescripcion());
        viewHolderProducto.datoPrecio.setText(lista.get(pos).getPrecio()+"");
        viewHolderProducto.datoCantidad.setText(lista.get(pos).getCantidad()+"");

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {

        if(cliclk != null){
            cliclk.onClick(v);
        }

    }

    public void setOnClick(View.OnClickListener listener){
        this.cliclk = listener;


    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder {

        TextView datoCodigo;
        TextView datoDescripcion;
        TextView datoPrecio;
        TextView datoCantidad;

        public ViewHolderProducto(View itemView){
            super(itemView);
            datoCodigo = itemView.findViewById(R.id.lblPcodigo);
            datoDescripcion = itemView.findViewById(R.id.lblPdescripcion);
            datoPrecio = itemView.findViewById(R.id.lblPprecio);
            datoCantidad = itemView.findViewById(R.id.lblPcantidad);

        }
    }
}
