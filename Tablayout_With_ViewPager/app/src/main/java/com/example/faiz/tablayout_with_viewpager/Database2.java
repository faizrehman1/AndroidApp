package com.example.faiz.tablayout_with_viewpager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "informationn.db";

    // Contacts table name
    private static final String TABLE_Name= "Namee";

    // Contacts Table Columns names
    private static final String PERSON_ID = "id";
    private static final String PERSON_TITLE = "title";
    private static final String PERSON_DISCRIPTION = "discription";



    public Database2(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null, %s TEXT )",TABLE_Name,PERSON_ID,PERSON_TITLE,PERSON_DISCRIPTION);

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveData(ToDoObject arrayList){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PERSON_TITLE,arrayList.getTitle());
        values.put(PERSON_DISCRIPTION, arrayList.getDiscription());
        // values.put(PERSON_STATUS, arrayList.getCheck());

        db.insert(TABLE_Name, null, values);


        Log.d("abid", arrayList.getTitle() + " " + arrayList.getDiscription() + " ");


        db.close();

    }

    public List<ToDoObject> getData(){
        List<ToDoObject> arrayList = new ArrayList<ToDoObject>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select %s,%s,%s from %s order by %s", PERSON_ID, PERSON_TITLE, PERSON_DISCRIPTION, TABLE_Name, PERSON_ID);
        Cursor cursor = db.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String msg = cursor.getString(2);
            // String checkBoxx = cursor.getString(3);
            Log.d("ID is ", "Msg:" + id);
            arrayList.add(new ToDoObject(title, msg,id));


        }


        db.close();
        return arrayList;
    }

    public void deleteItem(int pos){
        this.getWritableDatabase().delete(TABLE_Name,PERSON_ID+"="+pos,null);
    }

}
