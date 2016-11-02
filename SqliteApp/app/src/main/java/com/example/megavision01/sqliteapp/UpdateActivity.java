package com.example.megavision01.sqliteapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActivity extends Activity {
EditText editTextName,editTextUsername,editTextContact,editTextPassword,editTextDob;
    Spinner spinnerQualification;
    TextView tvEditQualification,tvEditGender,tvEditHobby;
    DatabaseHelper myDb;
    RadioGroup rgEditGender;
    public String gender1;
    private int year;
    private int month;
    private int day;
    byte[] img;
    public static final int DATE_DIALOG_ID = 999;
    EditText tvEditDisplayDate;
    DatePicker dpResult1;
    Button btnUpdate;
    public StringBuilder hobby1;
    CheckBox cbEditFootball,cbEditBasketball,cbEditCricket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editTextName= (EditText) findViewById(R.id.etEditName);
        editTextUsername= (EditText) findViewById(R.id.etEditUserName);
        editTextContact= (EditText) findViewById(R.id.etEditContact);
        editTextPassword= (EditText) findViewById(R.id.etEditPass);
        editTextDob= (EditText) findViewById(R.id.etEditDate);
        spinnerQualification= (Spinner) findViewById(R.id.editQualification);
        tvEditQualification= (TextView) findViewById(R.id.tvEditqualification);
        tvEditGender= (TextView) findViewById(R.id.tvEditGender);
        tvEditHobby= (TextView) findViewById(R.id.tvEditHobby);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        cbEditBasketball= (CheckBox) findViewById(R.id.cbEditBasketball);
        cbEditFootball= (CheckBox) findViewById(R.id.cbEditFootball);
        cbEditCricket= (CheckBox) findViewById(R.id.cbEditCricket);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hobby1 = new StringBuilder();
                if (cbEditFootball.isChecked()) {
                    hobby1.append("Football ");
                }

                if (cbEditBasketball.isChecked()) {
                    hobby1.append("Basketball ");
                }

                if (cbEditCricket.isChecked()) {
                    hobby1.append("Cricket ");
                }
                String idd = getIntent().getStringExtra("idd");
                boolean isUpdate =myDb.updateData(idd, editTextName.getText().toString(), editTextUsername.getText().toString(), editTextContact.getText().toString(), editTextPassword.getText().toString(), editTextDob.getText().toString(), spinnerQualification.getSelectedItem().toString(), gender1, hobby1.toString(),img);
            if(isUpdate == true)
            {
                Toast.makeText(UpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();

            }
                else
            {
                Toast.makeText(UpdateActivity.this, "Data Not Updated Successfully", Toast.LENGTH_LONG).show();

            }
            }
        });

        setCurrentDate();
        dateDialog();
        genderSelection();
        String id = getIntent().getStringExtra("idd");
        try {
            myDb = new DatabaseHelper(getBaseContext());
            Cursor res = myDb.getData(id);


            if(res.moveToFirst())
            {
                do {
                    editTextName.setText(res.getString(1));
                    editTextUsername.setText(res.getString(2));
                    editTextContact.setText(res.getString(4));
                    editTextPassword.setText(res.getString(3));
                    editTextDob.setText(res.getString(5));
                    tvEditQualification.setText("("+res.getString(6)+")");
                    tvEditGender.setText("("+res.getString(7)+")");
                    tvEditHobby.setText("("+res.getString(8)+")");
                    String abc=res.getString(8);
                    Log.d("Hobby",abc);


                }
                while(res.moveToNext());
            }

        }
        catch (Exception e)
        {
            Log.d("TAG", "username" + e.toString());
        }
    }


   public void genderSelection() {

        rgEditGender = (RadioGroup) findViewById(R.id.rgEditGender);

        rgEditGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rbEditMale) {
                    gender1 = "Male";
                } else {
                    gender1 = "Female";
                }

            }
        });
    }

    public void setCurrentDate()
    {   tvEditDisplayDate = (EditText)findViewById(R.id.etEditDate);
        dpResult1 = (DatePicker)findViewById(R.id.datePicker);
        final Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvEditDisplayDate.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year).append(" "));
        dpResult1.init(year, month, day, null);


    }
    public void dateDialog()
    {
        tvEditDisplayDate.setOnClickListener(new View.OnClickListener() {
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
   private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            year=selectedYear;
            month=selectedMonth;
            day=selectedDay;
            tvEditDisplayDate.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year).append(" "));
            dpResult1.init(year,month,day,null);
        }
    };



}
