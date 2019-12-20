package com.example.leon_eduardo.vista.fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leon_eduardo.R;

public class fragmento extends AppCompatActivity implements View.OnClickListener, frg_uno.OnFragmentInteractionListener, frg_dos.OnFragmentInteractionListener {

    Button botonFrg1, botonFrg2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmento);
        cargarComponentes();
    }


    private void cargarComponentes(){
        botonFrg1 = findViewById(R.id.frg2);
        botonFrg2 = findViewById(R.id.frg3);
        botonFrg1.setOnClickListener(this);
        botonFrg2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.frg2:
                frg_uno fragmento1 = new frg_uno();
                FragmentTransaction transacccion1 = getSupportFragmentManager().beginTransaction();
                transacccion1.replace(R.id.contenedorFragmento, fragmento1);
                transacccion1.commit();
                break;
            case R.id.frg3:
                frg_dos fragmento2 = new frg_dos();
                FragmentTransaction transaccion2 = getSupportFragmentManager().beginTransaction();
                transaccion2.replace(R.id.contenedorFragmento, fragmento2);
                transaccion2.commit();
                break;
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
