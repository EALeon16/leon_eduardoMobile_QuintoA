package com.example.leon_eduardo.modelo.config;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.example.leon_eduardo.vista.actividades.ActividadCarroORM;

public class AplicacionORM extends Application {

    public void onCreate(){
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
