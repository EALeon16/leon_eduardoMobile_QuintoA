package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leon_eduardo.R;

public class ActividadEnviarParametors extends AppCompatActivity implements View.OnClickListener {

    EditText cajaNombre, cajaApellido;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_enviar_parametors);
        enviarParametros();
    }

    public void enviarParametros(){

        cajaNombre = findViewById(R.id.txtNombreEnviarParametro);
        cajaApellido = findViewById(R.id.txtApellidoEnviarParametro);
        boton = findViewById(R.id.btnEnviarParametro);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(ActividadEnviarParametors.this, ActividadRecibirParametros.class);
        Bundle enviar = new Bundle();
        enviar.putString("nombre", cajaNombre.getText().toString());
        enviar.putString("apellido", cajaApellido.getText().toString());
        intent.putExtras(enviar);
        startActivity(intent);







    }
}
