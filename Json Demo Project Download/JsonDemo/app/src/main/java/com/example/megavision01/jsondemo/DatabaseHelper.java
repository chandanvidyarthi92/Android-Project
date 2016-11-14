package com.example.megavision01.jsondemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MegaVision01 on 11/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="project.db";
    public static final String LOGIN = "login";
    public static final String TABLE_NAME = "myProject";
    public static final String TABLE_NAME1 = "myInfo1";
    public static final String TABLE_NAME2 = "record";
    public static final String TABLE_NAME3 = "dataValue";

    public static final String id="ID";
    public static final String usernmae="USERNAME";
    public static final String password="PASSWORD";
    public static final String p_id="P_ID";
    public static final String Project_name="P_NAME";


    public static final String Form_Id="F_ID";
    public static final String Project_Id="P_ID";
    public static final String Form_name="f_name";
    public static final String Form_type="f_type";
    public static final String Form_value="f_values";

    public static final String RECORD_ID="r_id";

    public static final String DATA_ID="data_id";
    public static final String PROJECTID="project_id";
    public static final String REC_ID="rec_id";
    public static final String FIELD_ID="f_id";
    public static final String VALUE="value";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("Database", "Database is created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY,P_ID TEXT,P_NAME TEXT)");
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME1 + "(F_ID INTEGER PRIMARY KEY,P_ID INTEGER,f_name TEXT,f_type TEXT,f_values TEXT)");
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME2 + "(r_id INTEGER PRIMARY KEY,project_id INTEGER)");
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME3 + "(data_id INTEGER PRIMARY KEY,project_id INTEGER,rec_id INTEGER,f_id INTEGER,value TEXT)");
        sqLiteDatabase.execSQL(" CREATE TABLE " + LOGIN + "(ID INTEGER PRIMARY KEY,USERNAME TEXT,PASSWORD TEXT)");

        Log.d("Table", TABLE_NAME + "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + LOGIN);

    }
    public boolean insertProject(String Project_id,String project_name)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(p_id, Project_id);
        contentValues.put(Project_name, project_name);
        long resultset =db.insert(TABLE_NAME,null,contentValues);
        if(resultset == -1)
            return false;
        else
            return true;
    }

    public boolean insertForm(String Project_id,String label,String type,String option)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // contentValues.put(Form_Id,form_Id);
        contentValues.put(Project_Id,Project_id);
        contentValues.put(Form_name,label);
        contentValues.put(Form_type,type);
        contentValues.put(Form_value,option);
        long resultset1 =db.insert(TABLE_NAME1,null,contentValues);
        if(resultset1 == -1)
            return false;
        else
            return true;
    }
    public boolean RegForm(String uname,String pass)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(usernmae,uname);
        contentValues.put(password,pass);
        long resultset =db.insert(LOGIN,null,contentValues);
        if(resultset == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
