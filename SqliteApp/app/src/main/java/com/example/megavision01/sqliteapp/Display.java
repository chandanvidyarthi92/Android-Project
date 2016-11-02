package com.example.megavision01.sqliteapp;

import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    TextView tvUsername1,tvPassword1,tvContact1,tvQualification1,tvDob1,tvGender1,tvHobby1;
    DatabaseHelper myDb;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout myLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams textviewParms = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        myLayout.setBackgroundResource(R.drawable.bc2);

        TextView username= new TextView(this);
        TextView password = new TextView(this);
        TextView contact = new TextView(this);
        TextView dateofbirth = new TextView(this);
        TextView qualification = new TextView(this);
        TextView gender = new TextView(this);
        TextView hobby = new TextView(this);

        TextView username1= new TextView(this);
        TextView password1 = new TextView(this);
        TextView contact1 = new TextView(this);
        TextView dateofbirth1 = new TextView(this);
        TextView qualification1 = new TextView(this);
        TextView gender1 = new TextView(this);
        TextView hobby1 = new TextView(this);

        username.setText("username  :  ");
        password.setText("password  :  ");
        contact.setText("contact  :  ");
        dateofbirth.setText("dateofbirth  :  ");
        qualification.setText("qualification  :  ");
        gender.setText("gender  :  ");
        hobby.setText("hobby  :  ");


        RelativeLayout.LayoutParams usernameParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams passwordParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams contactParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams dobParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams qualificationParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams genderParms =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams hobbyParms =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams username1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams password1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams contact1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams dob1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams qualification1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams gender1Parms =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams hobby1Parms =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        username.setId(1);
        username1.setId(2);
        password.setId(3);
        password1.setId(4);
        contact.setId(5);
        contact1.setId(6);
        dateofbirth.setId(7);
        dateofbirth1.setId(8);
        qualification.setId(9);
        qualification1.setId(10);
        gender.setId(11);
        gender1.setId(12);
        hobby.setId(13);
        hobby1.setId(14);

        username1Params.addRule(RelativeLayout.RIGHT_OF, username.getId());
        passwordParams.addRule(RelativeLayout.BELOW, username.getId());
        password1Params.addRule(RelativeLayout.RIGHT_OF, password.getId());
        password1Params.addRule(RelativeLayout.BELOW, username1.getId());
        contactParams.addRule(RelativeLayout.BELOW, password.getId());
        contact1Params.addRule(RelativeLayout.RIGHT_OF, contact.getId());
        contact1Params.addRule(RelativeLayout.BELOW, password1.getId());
        dobParams.addRule(RelativeLayout.BELOW, contact.getId());
        dob1Params.addRule(RelativeLayout.RIGHT_OF, dateofbirth.getId());
        dob1Params.addRule(RelativeLayout.BELOW, contact1.getId());
        qualificationParams.addRule(RelativeLayout.BELOW, dateofbirth.getId());
        qualification1Params.addRule(RelativeLayout.RIGHT_OF,qualification.getId());
        qualification1Params.addRule(RelativeLayout.BELOW,dateofbirth1.getId());
        genderParms.addRule(RelativeLayout.BELOW, qualification.getId());
        gender1Parms.addRule(RelativeLayout.RIGHT_OF, gender.getId());
        gender1Parms.addRule(RelativeLayout.BELOW, qualification1.getId());
        hobbyParms.addRule(RelativeLayout.BELOW, gender.getId());
        hobby1Parms.addRule(RelativeLayout.RIGHT_OF, gender.getId());
        hobby1Parms.addRule(RelativeLayout.BELOW, gender1.getId());

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());


        myLayout.addView(username);
        myLayout.addView(username1,username1Params);
        myLayout.addView(password,passwordParams);
        myLayout.addView(password1,password1Params);
        myLayout.addView(contact,contactParams);
        myLayout.addView(contact1,contact1Params);
        myLayout.addView(dateofbirth,dobParams);
        myLayout.addView(dateofbirth1,dob1Params);
        myLayout.addView(qualification,qualificationParams);
        myLayout.addView(qualification1,qualification1Params);
        myLayout.addView(gender,genderParms);
        myLayout.addView(gender1,gender1Parms);
        myLayout.addView(hobby,hobbyParms);
        myLayout.addView(hobby1,hobby1Parms);

        setContentView(myLayout);


        /*tvUsername1=(TextView)findViewById(R.id.tvUserName1);
        tvPassword1=(TextView)findViewById(R.id.tvpassword1);
        tvContact1=(TextView)findViewById(R.id.tvContact1);
        tvQualification1=(TextView)findViewById(R.id.tvQualification1);
        tvDob1=(TextView)findViewById(R.id.tvDob1);
        tvGender1=(TextView)findViewById(R.id.tvGender1);
        tvHobby1=(TextView)findViewById(R.id.tvHobby1);*/


        String id = getIntent().getStringExtra("id");
        /*String password = getIntent().getStringExtra("password");
        String contact = getIntent().getStringExtra("contact");
        String dob = getIntent().getStringExtra("dob");
        String qualification = getIntent().getStringExtra("qualification");
        String gender = getIntent().getStringExtra("gender");
        String hobby = getIntent().getStringExtra("hobby");*/
        //tvUsername1.setText(id);
        /*tvPassword1.setText(password);
        tvContact1.setText(contact);
        tvDob1.setText(dob);
        tvQualification1.setText(qualification);
        tvGender1.setText(gender);
        tvHobby1.setText(hobby);*/

        try {
            myDb = new DatabaseHelper(getBaseContext());
            Cursor res = myDb.getData(id);

            if(res.moveToFirst())
            {
                do {
                    //tvName.setText(res.getString(1));
                    username1.setText(res.getString(2));
                    password1.setText(res.getString(4));
                    contact1.setText(res.getString(3));
                    dateofbirth1.setText(res.getString(5));
                    qualification1.setText(res.getString(6));
                    gender1.setText(res.getString(7));
                    hobby1.setText(res.getString(8));
                }
                while(res.moveToNext());
            }

        }
        catch (Exception e)
        {
            Log.d("TAG", "username" + e.toString());
        }

    }
}
