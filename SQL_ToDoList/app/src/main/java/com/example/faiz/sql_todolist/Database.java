package com.example.faiz.sql_todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "information.db";

    // Contacts table name
    private static final String TABLE_Name= "Name";

    // Contacts Table Columns names
    private static final String PERSON_ID = "id";
    private static final String PERSON_TITLE = "title";
    private static final String PERSON_DISCRIPTION = "discription";
    private static final String PERSON_STATUS="status";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String createTable= "CREATE TABLE " + TABLE_Name + "("
//                + PERSON_ID + " INTEGER PRIMARY KEY," + PERSON_TITLE + " TEXT,"
//                + PERSON_DISCRIPTION + " TEXT," + PERSON_STATUS + " TEXT" + ")";
        String sql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null, %s TEXT , %s TEXT )",TABLE_Name,PERSON_ID,PERSON_TITLE,PERSON_DISCRIPTION,PERSON_STATUS);

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveData(ToDoObjects arrayList){
        SQLiteDatabase db = getWritableDatabase();


      //  db.delete(TABLE_Name, null, null);

          ContentValues values = new ContentValues();
           // values.put(PERSON_ID,arrayList.getId());
            values.put(PERSON_TITLE,arrayList.getTitle());
            values.put(PERSON_DISCRIPTION, arrayList.getDiscription());
            values.put(PERSON_STATUS, arrayList.getCheck());

            db.insert(TABLE_Name, null, values);


            Log.d("abid", arrayList.getTitle() + " " + arrayList.getDiscription() + " " + arrayList.getCheck());


        db.close();

    }

    public List<ToDoObjects> getData(){
        List<ToDoObjects> arrayList = new ArrayList<ToDoObjects>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select %s,%s,%s,%s from %s order by %s", PERSON_ID, PERSON_TITLE, PERSON_DISCRIPTION, PERSON_STATUS, TABLE_Name, PERSON_ID);
        Cursor cursor = db.rawQuery(sql, null);

      //  int i=0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String msg = cursor.getString(2);
            String checkBoxx = cursor.getString(3);
            Log.d("ID is ", "Msg:" + id);
            arrayList.add(new ToDoObjects(title, msg,Boolean.valueOf(checkBoxx),id));

       // i++;
        }


        db.close();
        return arrayList;
    }

    public void deleteItem(int pos){
        this.getWritableDatabase().delete(TABLE_Name,PERSON_ID+"="+pos,null);
   }
}
