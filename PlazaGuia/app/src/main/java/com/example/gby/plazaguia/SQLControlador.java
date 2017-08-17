package com.example.gby.plazaguia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    // ------------------------------------------------------------------------------------------
    public void insertarTienda(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.STORE_NOMBRE, name);
        database.insert(DBhelper.TABLE_STORE, null, cv);
    }
    public void insertarPiso(int IDtienda,int piso,String localizacion) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.FLOOR_IDTIENDA, IDtienda);
        cv.put(DBhelper.FLOOR_PISO, piso);
        cv.put(DBhelper.FLOOR_LOC_CENTRO, localizacion);
        database.insert(DBhelper.TABLE_FLOOR, null, cv);
    }
    // Insertar deberes
   /* public void insertarDatosDeberes(String Dtitulo,String Ddescripcion,String Ddia,
                                     String Dhora , String IDcurso) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.DEBERES_TITULO, Dtitulo);
        cv.put(DBhelper.DEBERES_DESCRIPCION, Ddescripcion);
        cv.put(DBhelper.DEBERES_DIA, Ddia);
        cv.put(DBhelper.DEBERES_HORA, Dhora);
        cv.put(DBhelper.DEBERES_IDCURSO, IDcurso);
        database.insert(DBhelper.TABLE_DEBERES, null, cv);
    }
    // ------------------------------------------------------------------------------------------
    */
    public Cursor leerTiendas() {
        String[] todasLasColumnas = new String[] {
                DBhelper.STORE_ID,
                DBhelper.STORE_NOMBRE
        };
        Cursor c = database.query(DBhelper.TABLE_STORE, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public Cursor leerPisos() {
        String[] todasLasColumnas = new String[] {
                DBhelper.FLOOR_IDTIENDA,
                DBhelper.FLOOR_PISO,
                DBhelper.FLOOR_LOC_CENTRO
        };
        Cursor c = database.query(DBhelper.TABLE_FLOOR, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    /*public Cursor leerDeberes() {
        String[] todasLasColumnas = new String[] {
                DBhelper.DEBERES_ID,
                DBhelper.DEBERES_TITULO,
                DBhelper.DEBERES_DESCRIPCION,
                DBhelper.DEBERES_DIA,
                DBhelper.DEBERES_HORA,
                DBhelper.DEBERES_IDCURSO
        };
        Cursor c = database.query(DBhelper.TABLE_DEBERES, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    // -----------------------------------------------------------------
    */
    public Cursor seleccionarPiso(int IDtienda) {
        String[] columnas = new String[] {
                DBhelper.FLOOR_IDTIENDA,
                DBhelper.FLOOR_PISO,
                DBhelper.FLOOR_LOC_CENTRO
        };
        String[] argumento = new String[] {
                ""+IDtienda
        };
        Cursor c = database.query(DBhelper.TABLE_FLOOR, columnas, DBhelper.FLOOR_IDTIENDA+"=?",
                argumento, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    /*
    // ------------------------------------------------------------------------------------------
    public int actualizarDatos(long memberID, String memberName,String cursoAbrev,
                               String cursoCodigo, String cursoDocente) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.MIEMBRO_NOMBRE, memberName);
        cvActualizar.put(DBhelper.MIEMBRO_ABREVIATURA, cursoAbrev);
        cvActualizar.put(DBhelper.MIEMBRO_CODIGO, cursoCodigo);
        cvActualizar.put(DBhelper.MIEMBRO_DOCENTE, cursoDocente);

        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }
    public int actualizarDeberes(long deberID, String deberTitulo,String deberDescrip,
                                 String deberDia, String deberHora, String deberIDcurso) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.DEBERES_TITULO, deberTitulo);
        cvActualizar.put(DBhelper.DEBERES_DESCRIPCION, deberDescrip);
        cvActualizar.put(DBhelper.DEBERES_DIA, deberDia);
        cvActualizar.put(DBhelper.DEBERES_HORA, deberHora);
        cvActualizar.put(DBhelper.DEBERES_IDCURSO, deberIDcurso);

        int i = database.update(DBhelper.TABLE_DEBERES, cvActualizar,
                DBhelper.DEBERES_ID + " = " + deberID, null);
        return i;
    }
    // ------------------------------------------------------------------------------------------
    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_MEMBER, DBhelper.MIEMBRO_ID + "="
                + memberID, null);
    }
    public void deleteDeber(long deberID) {
        database.delete(DBhelper.TABLE_DEBERES, DBhelper.DEBERES_ID + "="
                + deberID, null);
    }*/
}