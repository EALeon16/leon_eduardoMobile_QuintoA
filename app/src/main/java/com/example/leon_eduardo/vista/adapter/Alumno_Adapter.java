package com.example.leon_eduardo.vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Alumno;

import java.util.List;

public class Alumno_Adapter extends RecyclerView.Adapter<Alumno_Adapter.ViewHolderAlumno> implements  View.OnClickListener{

    List<Alumno> lista;
    public View.OnClickListener cliclk;

    public Alumno_Adapter(List<Alumno> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolderAlumno onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_alumno, null);
        view.setOnClickListener(this);
        return new ViewHolderAlumno(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlumno holder, int pos) {
        holder.datoId.setText(lista.get(pos).getIdAlumno()+"");
        holder.datoNombre.setText(lista.get(pos).getNombre());
        holder.datoDireccion.setText(lista.get(pos).getDireccion());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if (cliclk != null) {
            cliclk.onClick(v);
        }
    }

    public void setOnClick(View.OnClickListener listener) {
        this.cliclk = listener;


    }

    public class ViewHolderAlumno extends RecyclerView.ViewHolder {
        TextView datoId;
        TextView datoNombre;
        TextView datoDireccion;
        public ViewHolderAlumno(@NonNull View itemView) {
            super(itemView);
            datoId = itemView.findViewById(R.id.txtItemSWId);
            datoNombre = itemView.findViewById(R.id.txtItemSWAlumno);
            datoDireccion = itemView.findViewById(R.id.txtItemSWDireccion);
        }
    }
}
