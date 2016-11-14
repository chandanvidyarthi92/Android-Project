package com.example.megavision01.jsondemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    EditText etRegUsername,etRegPassword;
    Button btnRegister,btnBack;
    DatabaseHelper myDb;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        myDb=new DatabaseHelper(getBaseContext());
        db= myDb.getWritableDatabase();
        setContentView(R.layout.activity_register);
        etRegUsername = (EditText) findViewById(R.id.etRegUserName);
        etRegPassword = (EditText) findViewById(R.id.etRegPassword);
        btnRegister = (Button) findViewById(R.id.btnReg);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void insertData() {
        Cursor res = db.rawQuery("select * from login where USERNAME='"+etRegUsername.getText().toString()+"'",null);

        if (etRegUsername.getText().toString().trim().equals(""))
            etRegUsername.setError("Enter Name");
        else if (etRegPassword.getText().toString().trim().equals(""))
            etRegPassword.setError("Enter Username");
        else if (res.getCount() > 0) {
            etRegUsername.setError("User name already exist");
        } else {
            boolean isInserted = myDb.RegForm(etRegUsername.getText().toString(), etRegPassword.getText().toString());
            if (isInserted == true) {
                Toast.makeText(RegisterActivity.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Data Not Inserted Successfully.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
