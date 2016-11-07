package com.example.megavision01.recyclerdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] fname,dname;
    int[] img_res = {R.drawable.bc,R.drawable.bc2,R.drawable.ic_cam,R.drawable.ic_delete,R.drawable.ic_edit
        ,R.drawable.ic_open};
    ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        fname = getResources().getStringArray(R.array.film_name);
        dname = getResources().getStringArray(R.array.diretor_name);
        int i = 0;

        for (String name : fname)
        {
            DataProvider dataProvider = new DataProvider(img_res[i],name,dname[i]);
            arrayList.add(dataProvider);
            i++;
        }
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child =recyclerView.findChildViewUnder(e.getX(),e.getY());
        int position=recyclerView.getChildLayoutPosition(child);
        Toast.makeText(MainActivity.this,"pos"+position,Toast.LENGTH_SHORT).show();

        for (int j = 0; j < rv.getChildCount(); j++)
            rv.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
        // change the background color of the selected element
        child.setBackgroundColor(Color.parseColor("#193e49"));
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
});



    }

}
