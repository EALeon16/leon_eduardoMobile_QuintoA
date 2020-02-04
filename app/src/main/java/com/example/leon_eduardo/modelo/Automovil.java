package com.example.leon_eduardo.modelo;


import android.database.Cursor;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.ArrayList;
import java.util.List;

@Table(name="carro")


public class Automovil extends Model {
    @Column(name="placa", unique =true)
    private String placa;
    @Column(name="modelo", notNull = true)
    private String modelo;
    @Column(name="marca", notNull = true)
    private String marca;
    @Column(name="anio", notNull = true)
    private int anio;

    public Automovil(String placa, String modelo,String marca, int anio) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anio = anio;
    }
    public Automovil(){
        super();

    }

    public static List<Automovil> getAll(){
        return new Select().from(Automovil.class).execute();
    }

    public static Automovil getCarbyPlaca(String placa){
        return new Select().from(Automovil.class).where("placa=?", placa).executeSingle();
    }


    public void EliminarByPlaca(String placa){
        new Delete().from(Automovil.class).where("placa = ?", placa).execute();
    }

    public void EliminarAll(){
        new Delete().from(Automovil.class).execute();
    }



    public void Update(Automovil automovil) {
        new Update(Automovil.class).set("modelo=?"+ automovil.getModelo())
                .where("placa=?"+ automovil
                        .getPlaca()).execute();

    }







    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
