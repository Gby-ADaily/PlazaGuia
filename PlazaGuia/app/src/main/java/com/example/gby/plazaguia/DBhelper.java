package com.example.gby.plazaguia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

    // Informacion de la tabla
    public static final String TABLE_STORE = "TBtienda";
    public static final String STORE_ID = "id";
    public static final String STORE_NOMBRE = "nombre";

    public static final String TABLE_FLOOR = "TBpiso";
    public static final String FLOOR_IDTIENDA = "_id";
    public static final String FLOOR_PISO = "deberTitulo";
    public static final String FLOOR_LOC_CENTRO= "locCentro";

    // informacion del a base de datos
    static final String DB_NAME = "DBPlazaGuia";
    static  String direccion="";
    static final int DB_VERSION = 1;

    // Informacion de la base de datos
    private static final String CREATE_TABLESTORE = "create table "
            + TABLE_STORE + "("
            + STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STORE_NOMBRE + " TEXT NOT NULL"+");";

    private static final String CREATE_TBFLOOR = "create table "
            + TABLE_FLOOR + "("
            + FLOOR_IDTIENDA + " INTEGER NOT NULL, "
            + FLOOR_PISO + " INTEGER NOT NULL,"
            + FLOOR_LOC_CENTRO + " TEXT NOT NULL"+");";
    //, FOREIGN KEY ("+ DEBERES_IDCURSO +") REFERENCES "+ TABLE_MEMBER +"(" + MIEMBRO_ID + "));";


    public DBhelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        direccion=db.getPath();
        db.execSQL(CREATE_TABLESTORE);
        db.execSQL(CREATE_TBFLOOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLOOR);
        onCreate(db);
    }
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(direccion,
                    null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            Log.e("Error", "No existe la base de datos " + e.getMessage());
        }
        return checkDB != null;
    }
}