package com.example.megavision01.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by MegaVision01 on 9/22/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="demo.db";
    public static final String TABLE_NAME = "myInfo";
    public static final String TABLE_NAME1 = "myInfo1";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "CONTACT";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_6 = "DATE";
    public static final String COL_7 = "QUALIFICATION";
    public static final String COL_8 = "GENDER";
    public static final String COL_9 = "HOBBY";
    public static final String COL_10 = "PROFILEPIC";

    public static final String Form_Id="F_ID";
    public static final String Project_Id="P_ID";
    public static final String Form_name="f_name";
    public static final String form_type="f_type";
    public static final String form_value="f_values";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("Database","Database is created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY,NAME TEXT,USERNAME TEXT,CONTACT INTEGER,PASSWORD INTEGER,DATE INTEGER,QUALIFICATION TEXT,GENDER TEXT,HOBBY TEXT,PROFILEPIC BLOB)");
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME1 + "(F_ID INTEGER PRIMARY KEY,P_ID INTEGER,f_name TEXT,f_type TEXT,f_values TEXT)");
            Log.d("Table", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String username,String contact,String password,String date,String qualification,String gender,String hobby,byte[] image)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,username);
        contentValues.put(COL_4,contact);
        contentValues.put(COL_5,password );
        contentValues.put(COL_6,date);
        contentValues.put(COL_7,qualification);
        contentValues.put(COL_8,gender);
        contentValues.put(COL_9, hobby);
        contentValues.put(COL_10, image);
        long resultset =db.insert(TABLE_NAME,null,contentValues);
        if(resultset == -1)
            return false;
        else
            return true;
    }

  /*  public boolean insertData1(String username,String qualification,String date,String gender,String hobby)
    {
        SQLiteDatabase db1 =this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL_3,username);
        contentValues1.put(COL_6,date);
        contentValues1.put(COL_7,qualification);
        contentValues1.put(COL_8,gender);
        contentValues1.put(COL_9, hobby);
        long resultset =db1.insert(TABLE_NAME,null,contentValues1);
        if(resultset == -1)
            return false;
        else
            return true;
    }*/
    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from " + TABLE_NAME1, null);
        return res;
    }
    public Cursor getSingleData( String User)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE USERNAME = '"+User+"'",null );
        return res;

    }

    public Cursor getData( String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE ID = '"+id+"'",null );
        return res;

    }
    /*  public String retriveData(String UName) {
        String a, b;
        b = "not";
        SQLiteDatabase db = getReadableDatabase();
        //String Cols[]={COL_3,COL_5};
        //String whereClause="USERNAME=?";
        Cursor cursor = db.rawQuery("SELECT USERNAME, PASSWORD FROM " + TABLE_NAME+" WHERE USERNAME = '"+UName+"'",null );
        //Cursor cursor = db.query(TABLE_NAME,Cols,whereClause,Username,null,null,null);
        do {
            cursor.moveToFirst();

            a = cursor.getString(0);

            if (a.equals(UName)) {
                b = cursor.getString(1);
                break;
            }
        }
        while (cursor.moveToNext());

        return b;
    }*/

    public Cursor getInformation(SQLiteDatabase db)
    {
        Cursor cursor;
        cursor =db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    public void deleteRecord(String id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NAME, "Id="+id, null);
    }
    public boolean updateData(String id,String name,String username,String contact,String password,String date,String qualification,String gender,String hobby,byte[] image)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,username);
        contentValues.put(COL_4,contact);
        contentValues.put(COL_5,password );
        contentValues.put(COL_6,date);
        contentValues.put(COL_7,qualification);
        contentValues.put(COL_8,gender);
        contentValues.put(COL_9, hobby);
        contentValues.put(COL_10, image);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }

}
