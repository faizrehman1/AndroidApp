package com.example.faiz.sql_todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
        String sql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null, %s TEXT , %s TEXT )", TABLE_Name, PERSON_ID, PERSON_TITLE, PERSON_DISCRIPTION,PERSON_STATUS);

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean saveData(ArrayList<ToDoObjects> arrayList){
        SQLiteDatabase db = getWritableDatabase();


        db.delete(TABLE_Name, null, null);

        int i=0;
        for(ToDoObjects data: arrayList){
            ContentValues values = new ContentValues();
            values.put(PERSON_ID,i);
            values.put(PERSON_TITLE,data.getTitle());
            values.put(PERSON_DISCRIPTION, data.getDiscription());
            values.put(PERSON_STATUS,data.getCheck());

            db.insert(TABLE_Name, null, values);
            i++;

            Log.d("abid",i+" "+data.getTitle()+" "+data.getDiscription()+" "+data.getCheck());
        }

        db.close();
    return true;
    }

    public ArrayList<ToDoObjects> getData(){
        ArrayList<ToDoObjects> arrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select %s,%s,%s,%s from %s order by %s",PERSON_ID,PERSON_TITLE,PERSON_DISCRIPTION ,PERSON_STATUS,TABLE_Name,PERSON_ID);
        Cursor cursor = db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            int id =cursor.getInt(0);
            String title = cursor.getString(1);
            String discription = cursor.getString(2);
            boolean bol = Boolean.parseBoolean(cursor.getString(3));

            arrayList.add(new ToDoObjects(title,discription,bol,id));
            Log.d("lol",title+" "+discription+" "+bol+" ");
        }


        db.close();
        return arrayList;
    }
}
