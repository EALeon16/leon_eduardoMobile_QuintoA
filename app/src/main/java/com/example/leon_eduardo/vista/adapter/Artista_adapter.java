package com.example.leon_eduardo.vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.leon_eduardo.R;

import java.util.List;

import com.example.leon_eduardo.modelo.Artista;

public class Artista_adapter extends RecyclerView.Adapter<Artista_adapter.ViewHolderArtista> {

    List<Artista> lista;
    public Artista_adapter(List<Artista> lista){
        this.lista = lista;

    }


    @Override
    public ViewHolderArtista onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artista, null);
        return new ViewHolderArtista(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderArtista viewHolderArtista, int i) {

        viewHolderArtista.datoNombres.setText(lista.get(i).getNombres());
        viewHolderArtista.datoApellidos.setText(lista.get(i).getApellidos());
        viewHolderArtista.datoNombreArtistico.setText(lista.get(i).getNombreArtistico());
        viewHolderArtista.datoFoto.setImageResource(lista.get(i).getFoto());


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolderArtista extends  RecyclerView.ViewHolder{
        TextView datoNombres;
        TextView datoApellidos;
        TextView datoNombreArtistico;
        ImageView datoFoto;
        Button boton;
        public ViewHolderArtista(View itemView) {
            super(itemView);
            datoNombres = itemView.findViewById(R.id.lblNombreArtista);
            datoApellidos = itemView.findViewById(R.id.lblApellidoArtista);
            datoNombreArtistico = itemView.findViewById(R.id.lblNombreArtistico);
            datoFoto = itemView.findViewById(R.id.imgFoto);




        }


    }
}
