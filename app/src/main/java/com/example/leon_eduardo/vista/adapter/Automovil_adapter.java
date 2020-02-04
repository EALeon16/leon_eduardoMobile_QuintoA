package com.example.leon_eduardo.vista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Automovil;

import java.util.List;

public class Automovil_adapter extends RecyclerView.Adapter<Automovil_adapter.ViewHolderAutomovil> implements View.OnClickListener {
    List<Automovil> lista;
    public View.OnClickListener cliclk;

    public Automovil_adapter(List<Automovil> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolderAutomovil onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_automovil, null);
        view.setOnClickListener(this);
        return new ViewHolderAutomovil(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAutomovil viewHolderAutomovil, int pos) {
        viewHolderAutomovil.datoPlaca.setText(lista.get(pos).getPlaca());
        viewHolderAutomovil.datoModelo.setText(lista.get(pos).getModelo());
        viewHolderAutomovil.datoMarca.setText(lista.get(pos).getMarca());
        viewHolderAutomovil.datoAnio.setText(lista.get(pos).getAnio() + "");

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



    public class ViewHolderAutomovil extends RecyclerView.ViewHolder {

        TextView datoPlaca;
        TextView datoModelo;
        TextView datoMarca;
        TextView datoAnio;

        public ViewHolderAutomovil(View itemView){
            super(itemView);
            datoPlaca = itemView.findViewById(R.id.txtPlacaR);
            datoModelo = itemView.findViewById(R.id.txtModeloR);
            datoMarca = itemView.findViewById(R.id.txtMarcaR);
            datoAnio = itemView.findViewById(R.id.txtAnioR);

        }
    }

}
