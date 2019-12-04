package com.example.leon_eduardo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActividadRecibirParametros extends AppCompatActivity {

    TextView  verNombre, verApellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_recibir_parametros);
        cargardDatos();
    }

    public void cargardDatos(){

        Bundle datos = this.getIntent().getExtras();

        verApellido = findViewById(R.id.lblRecibirParametroapellido);
        verNombre = findViewById(R.id.lblRecibirParametronombre);
        String verN = datos.getString("nombre");
        String verA = datos.getString("apellido");
        verNombre.setText(verN);
        verApellido.setText(verA);

    }
}
