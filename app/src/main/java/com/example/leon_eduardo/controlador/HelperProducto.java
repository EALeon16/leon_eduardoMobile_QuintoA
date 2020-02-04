package com.example.leon_eduardo.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.leon_eduardo.modelo.Producto;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class HelperProducto extends SQLiteOpenHelper {
    public HelperProducto(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE producto( id INTEGER PRIMARY KEY AUTOINCREMENT, codigo INTEGER, descripcion TEXT, precio DOUBLE, cantidad INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertar(Producto producto){
        ContentValues valores = new ContentValues();
        valores.put("codigo", producto.getCodigo());
        valores.put("descripcion", producto.getDescripcion());
        valores.put("precio", producto.getPrecio());
        valores.put("cantidad", producto.getCantidad());

        this.getWritableDatabase().insert("producto", null, valores);
    }

    public List <Producto> getAll(){
        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select *from producto", null);

        if (cursor.moveToFirst()){
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                lista.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
    public void modificar(Producto producto){
        ContentValues valores = new ContentValues();
        valores.put("codigo", producto.getCodigo());
        valores.put("codigo", producto.getCodigo());
        valores.put("descripcion", producto.getDescripcion());
        valores.put("precio", producto.getPrecio());
        valores.put("cantidad", producto.getCantidad());
        this.getWritableDatabase().update("producto", valores, "codigo="+ producto.getCodigo(), null);


    }

    public void eliminar(Producto producto){
        this.getWritableDatabase().delete("producto", "codigo="+ producto.getCodigo() , null);
    }

    public void eliminarAll(){
        this.getWritableDatabase().delete("producto", null, null);
    }

    public List<Producto> getProductoByCode(String code){
        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select *from producto where codigo='"+code+"'", null);

        if (cursor.moveToFirst()){
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                lista.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}
