package com.example.leon_eduardo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonLogin, botonSumar, botonParametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarComponentes();
    }
    private void cargarComponentes(){
        botonLogin = findViewById(R.id.btnIrlogin);
        botonSumar = findViewById(R.id.btnIrsumar);
        botonParametros = findViewById(R.id.btnIrparametros);
        botonLogin.setOnClickListener(this);
        botonSumar.setOnClickListener(this);
        botonParametros.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnIrlogin:
                intent = new Intent(MainActivity.this, activity_login.class);
                startActivity(intent);
                break;
            case R.id.btnIrsumar:
                intent = new Intent(MainActivity.this, activity_sumar.class);
                startActivity(intent);
                break;
            case R.id.btnIrparametros:
                intent = new Intent(MainActivity.this, ActividadEnviarParametors.class);
                startActivity(intent);
                break;
        }

    }
}
