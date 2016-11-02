package com.example.megavision01.sqliteapp;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
    DatabaseHelper myDb;
    EditText etUsername, etPassword;
    Button btnSignup;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        myDb = new DatabaseHelper(this);
        etUsername = (EditText) findViewById(R.id.etLoginUserName);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        Button btnLogin =(Button)findViewById(R.id.btnLogin);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getSingleData(etUsername.getText().toString() );
                if (res.getCount() == 0) {
                    Toast.makeText(LoginActivity.this, "Not Found ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    while (res.moveToNext()) {
                            if(res.getString(2).equals(etUsername.getText().toString()) && etPassword.getText().toString().equals(res.getString(4))) {
                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                intent.putExtra("username", res.getString(2).toString());
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(Name,etUsername.getText().toString() );
                                editor.putString(Phone,etPassword.getText().toString());
                                editor.commit();
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

     /*   DevicePolicyManager myDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdminSample = new ComponentName(this, DeviceAdminSample.class);

        if (myDevicePolicyManager.isDeviceOwnerApp(this.getPackageName())) {
            // Device owner
            String[] packages = {this.getPackageName()};
            myDevicePolicyManager.setLockTaskPackages(mDeviceAdminSample, packages);
        } else {
            // Not a device owner - prompt user or show error
        }

        if (myDevicePolicyManager.isLockTaskPermitted(this.getPackageName())) {
            // Lock allowed
            startLockTask();
        } else {
            // Lock not allowed - show error or something useful here
        }*/
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }

}
