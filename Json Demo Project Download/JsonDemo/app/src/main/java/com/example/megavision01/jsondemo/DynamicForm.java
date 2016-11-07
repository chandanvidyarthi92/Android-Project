package com.example.megavision01.jsondemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
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

public class DynamicForm extends AppCompatActivity {

    private List<EditText> editTextLongList = new ArrayList<EditText>();
    private List<TextView> textViewList = new ArrayList<TextView>();
    private List<Spinner> textSpinnerList = new ArrayList<Spinner>();
    private List<CheckBox> textCheckBoxList = new ArrayList<CheckBox>();
    private List<RadioButton> textRadioButtonList = new ArrayList<RadioButton>();
    private List<RadioGroup> textRadioGroupList = new ArrayList<RadioGroup>();
    private List<RadioButton> textSecRadioButtonList = new ArrayList<RadioButton>();//14june
    private List<RadioGroup> textSecRadioGroupList = new ArrayList<RadioGroup>();

    LinearLayout.LayoutParams fittype, textLayout;
    private int year;
    private int month;
    private int day;
    public static final int DATE_DIALOG_ID = 999;
    //EditText tvDisplayDate;
    DatePicker dpResult;
    DatabaseHelper myDb;
    EditText etUsername, etdate;
    Spinner sp1;
    RadioGroup gender;
    String genderStr;
    RadioButton rbMale1, rbFemale1;
    CheckBox hobby;
    String datevalue;
    Button btnInsert;
    DatePickerDialog.OnDateSetListener datePickerListener;
    String str_name = "", str_type = "", str_value = "", project_id = "";
    String TAG = DynamicForm.class.getSimpleName();
    int str_id = 0;
    DatePicker dp;
    private ProgressDialog pDialog;
    private static String url = "http://androidworkingapp.site88.net//formdesign.php";
    ArrayList<HashMap<String, String>> contactList;
    List<String> spinnerArray = new ArrayList<String>();
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
            String id = getIntent().getStringExtra("id");

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
                    Log.d("val", str_id + str_name + str_type + str_value);

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
                        formLayout.addView(qualifiaction(str_value));
                    }
                    if (str_type.equals("radio")) {

                        formLayout.addView(textView(str_name));
                        formLayout.addView(radiogroup(str_value, str_id));
                    }

                    if (str_type.equals("checkbox")) {

                        formLayout.addView(textView(str_name));
                        String[] str_chk1 = str_value.split(",");
                        for (int i = 0; i < str_chk1.length; i++) {
                            formLayout.addView(checkBox(1, str_chk1[i]));
                        }

                    }

                }
                while (cursor.moveToNext());
            }


        } catch (Exception e) {
            Log.d("Exp", e.toString());
        }




/*
        if(type[0].equals("text")){

            formLayoutChild.addView(textView(label[0]));
            formLayoutChild.addView(editText(label[0],id[0]));
        }*/

       /* if(type[1].equals("date")){

            // setCurrentDate(editText(label[1],id[1]));
            //addListerOnButton(editText(label[1],id[1]));

            formLayoutChild3.addView(textView(label[1]));
            formLayoutChild3.addView(editText(label[1],id[1]));
        }*/
       /* if(type[2].equals("dropdown")){

            formLayoutChild1.addView(textView(label[2]));
            formLayoutChild1.addView(qualifiaction(option));

            // editText(label[0],id[0]);
        }*/
      /*  if(type[3].equals("radio")){

            formLayoutChild4.addView(textView(label[3]));
            formLayoutChild4.addView(radiogroup(optionRadio, id[3]));
        }*/
       /* if(type[4].equals("checkbox")){

            formLayoutChild5.addView(textView(label[4]));
            String [] str_chk1=str_name_value.split(",");
            for(int i=0;i<str_chk1.length;i++)
            {
                formLayoutChild5.addView(checkBox(1,str_chk1[i]));
            }
        }
*/

        Button submit = new Button(this);
        submit.setText("Submit");
        submit.setId(101);
       //btnInsert= (Button) findViewById(submit.getId());
        formLayout.addView(submit);
        setContentView(scrollView);
        submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String idd= getIntent().getStringExtra("id");
        Toast.makeText(DynamicForm.this,"id is "+idd,Toast.LENGTH_SHORT).show();
        DatabaseHelper dbhs = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbhs.getWritableDatabase();
        ContentValues values = new ContentValues();

        //String[] strings = new String[editTextLongList.size()];
        //String abc = etUsername.getText().toString();
        /* for (int i = 0; i<editTextLongList.size(); i++){
            strings[i] = editTextLongList.get(i).getText().toString();
            Toast.makeText(getBaseContext(),strings[i]+"edit",Toast.LENGTH_LONG).show();
            Log.d("kjkj", "" + strings[i]);
            //values.put();
        }*/

        for (EditText editLongText : editTextLongList) {
            int id_l=editLongText.getId();

            String slnew=editLongText.getText().toString();
            //Toast.makeText(getBaseContext(),slnew+"",Toast.LENGTH_LONG).show();
            Log.d("fa",slnew);
            //values.put("f_values",slnew);
            //db.insert("myInfo1", null, values);
            //db.update("myInfo1", values, "ID = ?", new String[]{"2"});
        }
    }
});

    }

    private TextView textView(String label) {
        TextView textView = new TextView(this);
        //textView.setId(hint);
        textView.setTextSize(20);
        //textView.setHint(uname);
        textView.setText(label);
        textView.setLayoutParams(textLayout);
        textViewList.add(textView);
        return textView;
    }

    private EditText editText(String name, int id) {
        final EditText editText = new EditText(this);
        editText.setId(id);
        etUsername = (EditText) findViewById(editText.getId());
     /*       setCurrentDate(editText);
            addListerOnButton(editText);
        //dateDialog();*/
        editText.setLayoutParams(fittype);
        editText.setHint(name);
        editTextLongList.add(editText);
        return editText;
    }

    private EditText editText1(String name, int id) {
        final EditText editText1 = new EditText(this);
        editText1.setId(id);
        etdate = (EditText) findViewById(editText1.getId());
        Log.d("dateofbirth","dfsf"+name);
        editText1.setClickable(true);
        editText1.setFocusable(false);
        setCurrentDate(editText1);
        dateDialog(editText1);
        datechanged(editText1);
        editText1.setLayoutParams(fittype);
        editText1.setHint(name);
        return editText1;
    }


    private Spinner qualifiaction(String options) {
        Spinner qualifiactionSpinner = new Spinner(this);
        qualifiactionSpinner.setId(1002);
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

    private RadioGroup radiogroup(String optionRadio, int id) {

        RadioGroup radioGroup = new RadioGroup(getApplicationContext());
        radioGroup.setId(id);
        gender = (RadioGroup) findViewById(radioGroup.getId());
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setLayoutParams(fittype);
        String[] optionRadioList = optionRadio.split(",");
        RadioButton[] radioButton = new RadioButton[optionRadioList.length];
        for (int i = 0; i < optionRadioList.length; i++) {
            radioButton[i] = new RadioButton(this);
            radioButton[i].setText(optionRadioList[i]);
            radioButton[i].setTextSize(22);
            radioGroup.addView(radioButton[i]);
        }
        textSecRadioGroupList.add(radioGroup);
        return radioGroup;
    }

    private CheckBox checkBox(int a_id, String strvalue) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        hobby = (CheckBox) findViewById(checkBox.getId());
        //textCheckBoxList.add(checkBox);
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

  /*  public void saveData(){
        try{



            }catch (Exception e)
            {
                Log.d("Error",e.toString());
            }

    }*/

}




