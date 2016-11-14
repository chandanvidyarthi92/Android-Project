package com.example.megavision01.jsondemo;

/**
 * Created by Intel on 10-11-2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MegaVision01 on 10/6/2016.
 */
public class ListDataAdapter extends ArrayAdapter<DataProvider> {
    List<DataProvider> list = new ArrayList<DataProvider>();
    private LayoutInflater mInflater;
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    public class LayoutHandler
    {
        TextView ID,NAME;
    }
    @Override
    public void add(DataProvider object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DataProvider getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView " + position + " " + convertView);
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_listview,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME=(TextView)row.findViewById(R.id.textName);
            layoutHandler.ID=(TextView)row.findViewById(R.id.textId);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler =(LayoutHandler)row.getTag();
        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.ID.setText(dataProvider.getId());
        layoutHandler.NAME.setText(dataProvider.getName());
        return row;

    }
}


