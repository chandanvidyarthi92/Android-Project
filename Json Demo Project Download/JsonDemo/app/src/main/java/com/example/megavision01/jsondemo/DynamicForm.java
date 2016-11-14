package com.example.megavision01.jsondemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DynamicForm extends Activity {

    private List<EditText> editTextLongList = new ArrayList<EditText>();
    private List<TextView> textViewList = new ArrayList<TextView>();
    private List<Spinner> textSpinnerList = new ArrayList<Spinner>();
    private List<CheckBox> textCheckBoxList = new ArrayList<CheckBox>();
    private List<RadioButton> textRadioButtonList = new ArrayList<RadioButton>();
    private List<RadioGroup> textRadioGroupList = new ArrayList<RadioGroup>();

    LinearLayout.LayoutParams fittype, textLayout;
    private int year;
    private int month;
    private int day;
    public static final int DATE_DIALOG_ID = 999;
    DatePicker dpResult;
    DatabaseHelper myDb;
    EditText etUsername, etdate;
    Spinner sp1;
    CheckBox hobby;
    DatePickerDialog.OnDateSetListener datePickerListener;
    String str_name = "", str_type = "", str_value = "", project_id = "";
    String TAG = DynamicForm.class.getSimpleName();
    int str_id = 0;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(getBaseContext());
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout formLayout = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(formLayout);

        fittype = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        fittype.setMargins(10, 0, 10, 0);
        textLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textLayout.setMargins(10, 0, 10, 0);
        try {
            /*Getting Project Id from DataListActivity*/
            id = getIntent().getStringExtra("id");
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            SQLiteDatabase db1 = databaseHelper.getWritableDatabase();
            Cursor cursor = db1.rawQuery("select * from myInfo1 where P_ID='"+id+"'", null);
            if (cursor.moveToFirst()) {
                do {
                    str_id = cursor.getInt(0);
                    str_name = cursor.getString(2);
                    str_type = cursor.getString(3);
                    str_value = cursor.getString(4);
                    project_id = cursor.getString(1);

                    if (str_type.equals("text")) {
                        formLayout.addView(textView(str_name));
                        formLayout.addView(editText(str_name, str_id));
                    }
                    if (str_type.equals("date")) {
                        formLayout.addView(textView(str_name));
                        formLayout.addView(editText1(str_name, str_id));
                        formLayout.addView(datePicker(str_id));
                    }
                    if (str_type.equals("dropdown")) {

                        formLayout.addView(textView(str_name));
                        formLayout.addView(qualifiaction(str_value,str_id));
                    }
                    if (str_type.equals("radio")) {

                        formLayout.addView(textView(str_name));
                        formLayout.addView(radiogroup(str_value, str_id));
                    }
                    if (str_type.equals("checkbox")) {

                        formLayout.addView(textView(str_name));
                        String[] str_chk1 = str_value.split(",");
                        for (int i = 0; i < str_chk1.length; i++) {
                            formLayout.addView(checkBox(str_id, str_chk1[i]));
                        }
                    }
                }
                while (cursor.moveToNext());
            }


        } catch (Exception e) {
            Log.d("Exp", e.toString());
        }

        Button submit = new Button(this);
        submit.setText("Submit");
        submit.setId(101);
        formLayout.addView(submit);
        setContentView(scrollView);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                saveData();
                Intent intent = new Intent(DynamicForm.this,DataListActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
             }
        });
    }


    private TextView textView(String label) {
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText(label);
        textView.setLayoutParams(textLayout);
        textViewList.add(textView);
        return textView;
    }

    private EditText editText(String name, int id) {
        final EditText editText = new EditText(this);
        editText.setId(id);
        etUsername = (EditText) findViewById(editText.getId());
        editText.setLayoutParams(fittype);
        editText.setHint(name);
        editTextLongList.add(editText);
        return editText;
    }

    private EditText editText1(String name, int id) {
        final EditText editText1 = new EditText(this);
        editText1.setId(id);
        etdate = (EditText) findViewById(editText1.getId());
        editText1.setClickable(true);
        editText1.setFocusable(false);
        setCurrentDate(editText1);
        dateDialog(editText1);
        datechanged(editText1);
        editText1.setLayoutParams(fittype);
        editText1.setHint(name);
        return editText1;
    }

    private Spinner qualifiaction(String options,int sp_id) {
        Spinner qualifiactionSpinner = new Spinner(this);
        qualifiactionSpinner.setId(sp_id);
        sp1 = (Spinner) findViewById(qualifiactionSpinner.getId());
        String[] optionList = options.split(",");
        List<String> spinnerArray = new ArrayList<String>();
        for (String value : optionList) {
            spinnerArray.add(value);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            qualifiactionSpinner.setAdapter(adapter);
        }
        qualifiactionSpinner.setLayoutParams(fittype);
        textSpinnerList.add(qualifiactionSpinner.getSelectedItemPosition(), qualifiactionSpinner);
        return qualifiactionSpinner;
    }

    private RadioGroup radiogroup(String optionRadio,int id){

        final RadioGroup radioGroup = new RadioGroup(getApplicationContext());
        radioGroup.setId(id);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        radioGroup.setLayoutParams(fittype);
        String[] optionRadioList = optionRadio.split(",");
        //  final RadioButton[] radioButton = new RadioButton[optionRadioList.length];
        for(int i = 0 ; i<optionRadioList.length;i++){
            radioGroup.addView(radioButton(id+1,optionRadioList[i]));
        }
        textRadioGroupList.add(radioGroup);
        return radioGroup;
    }

    private RadioButton radioButton(int a_id,String strvalue) {   //965

        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(strvalue);
       // radioButton.setId(a_id);
        textRadioButtonList.add(radioButton);
        return radioButton;
    }
    private CheckBox checkBox(int a_id, String strvalue) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        hobby = (CheckBox) findViewById(checkBox.getId());
        textCheckBoxList.add(checkBox);
        return checkBox;
    }

    private DatePicker datePicker(int a_id) {
        DatePicker datePicker = new DatePicker(getApplicationContext());
        datePicker.setId(a_id);
        datePicker.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        dpResult = (DatePicker) findViewById(datePicker.getId());
        return datePicker;
    }

    public void setCurrentDate(EditText editText1) {
        dpResult = new DatePicker(this);
        //dpResult = (DatePicker)findViewById(R.id.datePicker);
        editText1.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year).append(" "));
        dpResult.init(year, month, day, null);
    }


    public void dateDialog(EditText editTextDate) {
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);
        }
        return null;
    }

    public void datechanged(final EditText editTextDate){
     datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            editTextDate.setText(new StringBuilder().append(day).append("-").append(month+1).append("-").append(year).append(" "));
            //dpResult.init(year, month, day, null);
        }
    };
    }

    public void saveData()
    {
        int rd=1;
        String idd= getIntent().getStringExtra("id");
        DatabaseHelper dbhs = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbhs.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("project_id", idd);
        values.put("rec_id", rd);

        for (EditText editLongText : editTextLongList) {
            int id_l=editLongText.getId();

            String slnew=editLongText.getText().toString();
            Log.d("fa", slnew);
            values.put("f_id", id_l);
            values.put("value", slnew);
            db.insert("dataValue", null, values);
        }

        for (RadioGroup rdgrp : textRadioGroupList) {
            String selectRB="";
            int cbid=rdgrp.getId();
            Log.d("cbid",""+cbid);

            try{
                int selectedId=rdgrp.getCheckedRadioButtonId();
                View rb1 = rdgrp.findViewById(selectedId);
                int idx= rdgrp.indexOfChild(rb1);
                RadioButton radioButton = (RadioButton) rdgrp.getChildAt(idx);
                if(radioButton.isChecked())
                {
                    selectRB=radioButton.getText().toString();
                    values.put("f_id", cbid);
                    values.put("value", selectRB);
                    db.insert("dataValue", null, values);
                }
                else{
                    Toast.makeText(getBaseContext(),selectRB+" Radio value1",Toast.LENGTH_LONG).show();
                }
            }
            catch(NullPointerException e){
                System.out.println("fbi540 ERROR=="+ e);
            }
            catch(Exception e){
                System.out.println("fd1303 ERROR==" + e);
                Toast.makeText(getApplicationContext(), "Error code: fd1303", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                System.out.println("-------ee FD radio  "+"Id_"+cbid+" value "+selectRB) ;

            }
        }
        String scheckbox="";
        String checkupload="";
        for (CheckBox textCheckBox : textCheckBoxList) {
            int id_ck=textCheckBox.getId();
                if(textCheckBox.isChecked())
                {
                    scheckbox=textCheckBox.getText().toString();
                    values.put("value", scheckbox);
                    db.insert("dataValue", null, values);
                }
                else
                {
                    scheckbox="null";

                }

                if(checkupload.equals(""))
                {
                    checkupload=scheckbox;
                }
                else{
                    checkupload=checkupload+","+scheckbox;
                }

        }

        for (Spinner textSpinner : textSpinnerList) {
            int id_sp = textSpinner.getId();
            String sspinner = String.valueOf(textSpinner.getSelectedItem());
            values.put("f_id", id_sp);
            values.put("value",sspinner);
            db.insert("dataValue", null, values);
            //values.put("Id_"+id_sp, sspinner);
        }


    }

}




