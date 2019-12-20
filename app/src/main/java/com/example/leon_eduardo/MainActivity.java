package com.example.leon_eduardo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leon_eduardo.vista.actividades.ActividadEnviarParametors;
import com.example.leon_eduardo.vista.actividades.ActividadRecycleArtistas;
import com.example.leon_eduardo.vista.actividades.actividad_escuchar;
import com.example.leon_eduardo.vista.actividades.activity_login;
import com.example.leon_eduardo.vista.actividades.activity_sumar;
import com.example.leon_eduardo.vista.fragmentos.fragmento;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        //caragra menu

        //metodo inflate permite agregar un menu implrmrnyasdo de archivo xml a la actividad

        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu. main, menu);

        return true;

    }

    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent intent;
        switch(item.getItemId()) {
            case R.id.opcionLogin:
                intent = new Intent(MainActivity.this, activity_login.class);
                startActivity(intent);
                break;
            case R.id.opcionSuma:
                intent = new Intent(MainActivity.this, activity_sumar.class);
                startActivity(intent);
                break;
            case R.id.opcionParametroe:
                intent = new Intent(MainActivity.this, ActividadEnviarParametors.class);
                startActivity(intent);
                break;
            case R.id.dlgSumar:
                final Dialog dlgSumar = new Dialog(MainActivity.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);

                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtN1dlg);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txtN2dlg);
                Button botonsumarlDlg= dlgSumar.findViewById(R.id.btnSumardlg);
                botonsumarlDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int valor1 = Integer.parseInt(cajaN1.getText().toString());
                        int valor2 = Integer.parseInt(cajaN2.getText().toString());
                        int suma = valor1 + valor2;
                        Toast.makeText(MainActivity.this, "La suma es: " + suma, Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });

                dlgSumar.show();
                break;

            case R.id.opcionColores:
                intent = new Intent(MainActivity.this, fragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionEscuchar:
                intent = new Intent(MainActivity.this, actividad_escuchar.class);
                startActivity(intent);
                break;
            case R.id.opcionAgregarartista:
                intent = new Intent(MainActivity.this, ActividadRecycleArtistas.class);
                startActivity(intent);
                break;
            case R.id.opcionMI:
                intent = new Intent(MainActivity.this, ActividadMI.class);
                startActivity(intent);
                break;
            case R.id.opcionMP:
                intent = new Intent(MainActivity.this, ActividadMP.class);
                startActivity(intent);
                break;

        }

        return true;

    }




}
