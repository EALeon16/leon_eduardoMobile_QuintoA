package com.example.leon_eduardo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_sumar extends AppCompatActivity implements View.OnClickListener{

    EditText n1, n2, resultado;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumar);
        Suma();
    }
    private void Suma(){

        n1 = findViewById(R.id.txtNumero1);
        n2 = findViewById(R.id.txtNumero2);
        resultado = findViewById(R.id.txtResultado);
        boton = findViewById(R.id.btnSuma);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int valor1 = Integer.parseInt(n1.getText().toString());
        int valor2 = Integer.parseInt(n2.getText().toString());
        int suma = valor1 + valor2;
        resultado.setText(suma + "");

    }
}
