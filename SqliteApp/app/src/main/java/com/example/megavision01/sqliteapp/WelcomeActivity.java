package com.example.megavision01.sqliteapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeActivity extends AppCompatActivity {
    public TextView tvpassword;
    TextView tvUsername,tvName,tvContact,tvQualification,tvDob,tvGender,tvHobby;
    DatabaseHelper myDb;
    byte[] img;
    ImageView imageView;
    public String name="";
    //private Session sess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /*sess = new Session(this);
        Log.d("sedd",sess.toString());*/
        /*if(!sess.loggedin())
        {
            Toast.makeText(getApplicationContext(),"fasdf",Toast.LENGTH_SHORT).show();
            //logout();
        }*/


            tvName = (TextView) findViewById(R.id.tvName);
            tvUsername = (TextView) findViewById(R.id.tvUserName);
            tvpassword = (TextView) findViewById(R.id.tvpassword);
            tvContact = (TextView) findViewById(R.id.tvContact);
            tvDob = (TextView) findViewById(R.id.tvDob);
            tvQualification = (TextView) findViewById(R.id.tvQualification);
            tvGender = (TextView) findViewById(R.id.tvGender);
            tvHobby = (TextView) findViewById(R.id.tvHobby);
            imageView = (ImageView) findViewById(R.id.viewImageView);
            Button btnLogout = (Button) findViewById(R.id.btnLogout);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //logout();
                }
            });
        /*
        String date_of_birth = getIntent().getStringExtra("date_of_birth");
        String password = getIntent().getStringExtra("password");*/
            String username = getIntent().getStringExtra("username");
        /*
        String name = getIntent().getStringExtra("name");
        String contact = getIntent().getStringExtra("contact");
        String qualification = getIntent().getStringExtra("qualification");
        String gender = getIntent().getStringExtra("gender");
        String hobby = getIntent().getStringExtra("hobby");*/

        /*tvName.setText(name);*/
            tvUsername.setText(username);
        /*tvpassword.setText(password);
        tvContact.setText(contact);
        tvDob.setText(date_of_birth);
        tvQualification.setText(qualification);
        tvGender.setText(gender);
        tvHobby.setText(hobby);*/


            try {
                myDb = new DatabaseHelper(getBaseContext());
                Cursor res = myDb.getSingleData(username);

                if (res.moveToFirst()) {
                    do {
                        tvName.setText(res.getString(1));
                        tvUsername.setText(res.getString(2));
                        tvpassword.setText(res.getString(4));
                        tvContact.setText(res.getString(3));
                        tvDob.setText(res.getString(5));
                        tvQualification.setText(res.getString(6));
                        tvGender.setText(res.getString(7));
                        tvHobby.setText(res.getString(8));
                        img = res.getBlob(res.getColumnIndex("PROFILEPIC"));
                    }
                    while (res.moveToNext());
                }
                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.d("TAG", "username" + e.toString());
            }
        }


    private void logout()
    {
       /* SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();*/
    }
        }










