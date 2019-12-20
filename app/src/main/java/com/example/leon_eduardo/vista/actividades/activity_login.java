package com.example.leon_eduardo.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leon_eduardo.R;

public class activity_login extends AppCompatActivity implements  View.OnClickListener {
    EditText cajaUsuario, cajaPassword;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarComponentes();


    }

    private void cargarComponentes(){

        cajaUsuario = findViewById(R.id.txtLogin);
        cajaPassword = findViewById(R.id.txtPassword);
        boton = findViewById(R.id.btnLogin);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Toast.makeText( activity_login.this, "Usuario:" + " " + cajaUsuario.getText() +
                        " " + "Clave:"+ " " + cajaPassword.getText(), Toast.LENGTH_SHORT).show();

    }
}
