package com.example.megavision01.jsondemo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {
    ListView listView;
    public CheckBox checkListView;
    DatabaseHelper myDb;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper db;
    String project_id,from_id;
    String p_id,p_name;
    ListDataAdapter listDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        /*Getting project id from main Activity*/
        project_id = getIntent().getStringExtra("id");
        from_id = getIntent().getStringExtra("f_id");
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                for (int j = 0; j < parent.getChildCount(); j++)
                    parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                //idd=ListDataAdapter.getItem(pos).getId();
                // change the background color of the selected element
                view.setBackgroundColor(Color.parseColor("#193e49"));
            }
        });

        db = new DatabaseHelper(getApplicationContext());
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.display_listview);
        listView.setAdapter(listDataAdapter);
        Cursor cursor = db.getInformation(project_id,from_id);
        try{
            if (cursor.moveToFirst()) {
                do {
                    p_id =cursor.getString(1);
                    p_name = cursor.getString(4);
                    DataProvider dataProvider = new DataProvider(p_id, p_name);
                    listDataAdapter.add(dataProvider);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e)
        {
            Log.d("Data", e.toString());
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
            Intent intent = new Intent(DataListActivity.this,DynamicForm.class);
            intent.putExtra("id",project_id);
            startActivity(intent);
        }
        if(R.id.editId ==item.getItemId())
        {

        }
        if(R.id.deleteId ==item.getItemId())
        {


        }
        return super.onOptionsItemSelected(item);
    }

}
