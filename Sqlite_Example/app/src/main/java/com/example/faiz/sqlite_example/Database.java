package com.example.faiz.sqlite_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product.db";
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "product_name";


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE" + TABLE_NAME + "(" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREAMENT" +
//                COLUMN_PRODUCTNAME + "TEXT" +
//                ");";
        String sql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null)",TABLE_NAME,COLUMN_ID,COLUMN_PRODUCTNAME);


        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Addvalue(MyProducts myProducts) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCTNAME, myProducts.getProductname());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void DeleteProduct(String productname) {
        SQLiteDatabase db = getWritableDatabase();
        String sql =String.format("delete from %s where %s = 0",TABLE_NAME,COLUMN_ID);
       // db.execSQL("DELETE FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "=\"" + productname + "\";");
        db.execSQL(sql);
        db.close();
    }

    public String databaseTostring() {
        String db_string = "";
        SQLiteDatabase db = getReadableDatabase();
        String sql =String.format("select %s,%s from %s order by %s",COLUMN_ID,COLUMN_PRODUCTNAME,TABLE_NAME,COLUMN_ID);
        Cursor c = db.rawQuery(sql, null);

       // c.moveToFirst();

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String title = c.getString(1);

            Log.d("Haha", id + " " + title);
            db_string += title;
          //  db_string += "\n";
         //   db_string +="\n";

        }

        db.close();
        return db_string;
    }
}
