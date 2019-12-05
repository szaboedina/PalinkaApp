package com.example.palinkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdatbazisSegito extends SQLiteOpenHelper {


    public  static final String DATABASE_NAME= "PalinkaAdatbazis.db";
    public static final String TABLE_NAME="palinka";

    public static final String COL_1="id";
    public static final String COL_2="fozo";
    public static final String COL_3="gyumolcs";
    public static final String COL_4="alkohol";


    public AdatbazisSegito(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,fozo TEXT NOT NULL,gyumolcs TEXT NOT NULL,alkohol INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean adatRogzites(String fozo, String gyumolcs, String alkohol){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fozo);
        contentValues.put(COL_3, gyumolcs);
        contentValues.put(COL_4, alkohol);

        long eredmeny = database.insert(TABLE_NAME,null,contentValues);
        if (eredmeny==-1)
            return false;
        else
            return true;
    }
    public Cursor adatLekerdezes(){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor eredmeny = database.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return eredmeny;
    }
    public long adatTorles(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME,COL_1+" = ?",new String[] {String.valueOf(id)});
    }

    public long adatModosit(String id, String fozo, String gyumolcs, String alkohol){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, fozo);
        values.put(COL_3, gyumolcs);
        values.put(COL_4, alkohol);
        return  database.update(TABLE_NAME, values, COL_1+" = ?", new String[]{id});
    }
}




