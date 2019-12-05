package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TanulokDBhelper extends SQLiteOpenHelper {

    private static final int DBversion = 1;
    private static final String DBname = "Tanulok.db";

    private static final String TABLE_NAME = "tanulok";

    private static final String COL_ID = "id";
    private static final String COL_VEZETEKNEV = "vezeteknev";
    private static final String COL_KERESZTNEV = "keresztnev";
    private static final String COL_JEGY = "jegy";

	
    public TanulokDBhelper(Context context){
        super(context,DBname,null,DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTables = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_VEZETEKNEV+" VARCHAR(30)," +
                COL_KERESZTNEV+ " VARCHAR(30)," +
                COL_JEGY+ " INTEGER)";
        db.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
	
	@Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean adatFelvetel(String vezeteknev, String keresztnev, String jegy){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_VEZETEKNEV, vezeteknev);
        values.put(COL_KERESZTNEV, keresztnev);
        values.put(COL_JEGY, jegy);

        //return db.insert(TABLE_NAME, null, values) != -1;
        long erintettSorok = db.insert(TABLE_NAME, null, values);
        if (erintettSorok == -1){
            return false;
        }else{
            return true;
        }
    }
    public long adatModositas(String id, String vezeteknev, String keresztnev, String jegy){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_VEZETEKNEV, vezeteknev);
        values.put(COL_KERESZTNEV, keresztnev);
        values.put(COL_JEGY, jegy);

        return db.update(TABLE_NAME,values,COL_ID+" = ?",new String[]{id});
    }

    public long adatTorles(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,COL_ID+" = ?",new String[]{id});
        //return db.delete(TABLE_NAME,COL_ID+" = "+id,null); nem jó mert sql injection lehet.
    }

    public Cursor adatLekerdezes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
	
	public Cursor tanuloJegyKereses(String vezeteknev, String keresztnev){
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM "+ TABLE_NAME +" WHERE " + COL_VEZETEKNEV+ " = ? AND "+COL_KERESZTNEV+"= ?";
        return db.rawQuery(sql, new String[] {vezeteknev,keresztnev});
	}
	
	public Cursor tanuloJegyKeresesQueryvel(String vezeteknev, String keresztnev){
		SQLiteDatabase db = this.getReadableDatabase();
		return db.query(TABLE_NAME, 
                new String[] {id,vezeteknev,keresztnev,jegy}, 
                COL_VEZETEKNEV+" = ? AND "+COL_KERESZTNEV+" = ?", 
                new String[] {vezeteknev,keresztnev}, 
                null,
                null, 
                null)
	}
}
