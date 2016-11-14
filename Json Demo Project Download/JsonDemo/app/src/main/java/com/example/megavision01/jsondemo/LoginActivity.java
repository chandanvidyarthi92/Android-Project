package com.example.megavision01.jsondemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    DatabaseHelper myDb;
    SQLiteDatabase db;
    EditText etUsername, etPassword;
    Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        myDb = new DatabaseHelper(this);
        db=myDb.getWritableDatabase();
        etUsername = (EditText) findViewById(R.id.etLoginUserName);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        Button btnLogin =(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.rawQuery("SELECT * FROM login WHERE USERNAME = '"+etUsername.getText().toString()+"'",null );
                if(etUsername.getText().toString().trim().equals(""))
                {
                    etUsername.setError("Enter Name");
                }
                else if(etPassword.getText().toString().trim().equals(""))
                {
                    etPassword.setError("Enter Password");
                }
                else if (res.getCount() == 0) {
                    Toast.makeText(LoginActivity.this, "Not Found ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    while (res.moveToNext()) {
                        if(res.getString(1).equals(etUsername.getText().toString()) && etPassword.getText().toString().equals(res.getString(2))) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Username and Password not matching.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
