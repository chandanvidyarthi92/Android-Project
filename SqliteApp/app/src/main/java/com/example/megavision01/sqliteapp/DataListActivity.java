package com.example.megavision01.sqliteapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import com.example.megavision01.sqliteapp.DataProvider;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class DataListActivity extends AppCompatActivity {
    ListView listView;
    public CheckBox checkListView;
    DatabaseHelper myDb;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper db;
    public String id;
    ListDataAdapter listDataAdapter;
    Context context=this;
    private int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);
        listView = (ListView) findViewById(R.id.listView);
        //final CheckBox checkBox = (CheckBox)findViewById(R.id.checkListView);

        //listView.setTag(checkListView);
        //registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {

              /* try {
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        View v = parent.getChildAt(i);
                        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkListView);
                        checkBox.setChecked(false);

                    }
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkListView);
                    checkBox.setChecked(true);

                    if (checkBox.isChecked()) {
                        id = listDataAdapter.getItem(pos).getId();
                    }

                }
                catch (Exception e) {
                    Log.d("Exp", e.toString());
                }*//*checkListView = (CheckBox) view.getTag();
                checkListView.setChecked(true);
                // Data is displayed on another Activity by using Intent
                listDataAdapter.getItem(pos);
                //checkListView.setFocusable(true);*/

              /*  for (int i = 0; i < adapterView.getCount(); i++) {
                    if(pos == i ){
                        adapterView.getChildAt(i).setBackgroundColor(Color.parseColor("#193e49"));
                       id=listDataAdapter.getItem(pos).getId();
                        //Toast.makeText(DataListActivity.this,abc.toString(),Toast.LENGTH_LONG).show();
                    }else{
                        listView.getChildAt(i).setBackgroundColor(Color.parseColor("#2787A4"));
                    }
                }*/
                for (int j = 0; j < parent.getChildCount(); j++)
                    parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                id=listDataAdapter.getItem(pos).getId();
                // change the background color of the selected element
                view.setBackgroundColor(Color.parseColor("#193e49"));







            }

        });

        db = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = db.getWritableDatabase();
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.display_listview);
        listView.setAdapter(listDataAdapter);
        Cursor cursor = db.getInformation(sqLiteDatabase);
        try{
        if (cursor.moveToFirst()) {
            do {
                //Toast.makeText(getBaseContext(), cursor.getString(1), Toast.LENGTH_LONG).show();
                String id,name, username, password,contact,dob,qualification,gender,hobby;
                id =cursor.getString(0);
                name = cursor.getString(1);
                username = cursor.getString(2);
                password = cursor.getString(4);
                contact = cursor.getString(3);
                dob = cursor.getString(5);
                qualification = cursor.getString(6);
                gender = cursor.getString(7);
                hobby = cursor.getString(8);
                DataProvider dataProvider = new DataProvider(id,name, username, password,contact,dob,qualification,gender,hobby);
                listDataAdapter.add(dataProvider);
            }
            while (cursor.moveToNext());
        }
    }
    catch (Exception e)
    {
        Log.d("Data",e.toString());
    }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_action,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.openId == item.getItemId())
        {
            Intent intent = new Intent(DataListActivity.this,Display.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        if(R.id.editId ==item.getItemId())
        {
            Intent intent= new Intent(DataListActivity.this,RegisterActivity.class);
            intent.putExtra("idd",id);
            startActivity(intent);
        }
        if(R.id.deleteId ==item.getItemId())
        {

            db.deleteRecord(id);
            Toast.makeText(DataListActivity.this,"Data Deleted Successfully.",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(DataListActivity.this,DataListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(DataListActivity.this,RegisterActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        super.onCreateContextMenu(menu, v, menuInfo);
        menuInflater.inflate(R.menu.activity_main_action, menu);

    }*/

/*  @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.openId:
                Intent intent = new Intent(DataListActivity.this,Display.class);
                intent.putExtra("name",listDataAdapter.getItem(info.position).getId());
                //intent.putExtra("username", listDataAdapter.getItem(info.position).getUsername());
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }*/
}
