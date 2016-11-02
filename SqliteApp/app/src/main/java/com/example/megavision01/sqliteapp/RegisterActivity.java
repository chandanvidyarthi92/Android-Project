package com.example.megavision01.sqliteapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends Activity {

    DatabaseHelper myDb;
    EditText etName, etUserName, etContact, etPassword, etdate;
    Button btnInsert, btnView,btnSignIn,btnListview;
    private int year,month,day;
    DatePicker dpResult;
    Spinner sp1;
    public String genderStr,gender;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    public CheckBox cbFootball, cbBasketball, cbCricket;
    public StringBuilder hobby;
    ImageView imageView;
    LinearLayout[] layout12;
    byte[] img;
    String hobbyStr, qValue = "", idd;
    private static final int CAMERA_REQUEST = 1888;
    public static final int DATE_DIALOG_ID = 999;

    //////////////////////////
    int count = 1;
     LinearLayout loginLayout;


    RadioGroup rgGender1;
    RadioButton rbMale1,rbFemale1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


        loginLayout = new LinearLayout(this);
        loginLayout.setOrientation(LinearLayout.VERTICAL);
        loginLayout.setBackgroundResource(R.drawable.bc2);
        scrollView.addView(loginLayout);


 //fld_$row[fffg]

        TextView login = new TextView(this);
        login.setText("Personal Info");
        login.setTextSize(40);
        login.setGravity(Gravity.CENTER_VERTICAL);


        LinearLayout.LayoutParams tvLoginParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1.0f);

        LinearLayout.LayoutParams tvLoginParams1 =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        //tvLoginParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        loginLayout.addView(login, tvLoginParams1);

        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout layout3 = new LinearLayout(this);
        LinearLayout layout4 = new LinearLayout(this);
        LinearLayout layout5 = new LinearLayout(this);
        LinearLayout layout6 = new LinearLayout(this);
        LinearLayout layout7 = new LinearLayout(this);
        LinearLayout layout8 = new LinearLayout(this);
        LinearLayout layout9 = new LinearLayout(this);
        LinearLayout layout10 = new LinearLayout(this);
        LinearLayout layout11 = new LinearLayout(this);

        //layout12 = new LinearLayout[count];


        loginLayout.addView(layout1, tvLoginParams1);
        loginLayout.addView(layout2);
        loginLayout.addView(layout3);
        loginLayout.addView(layout4);
        loginLayout.addView(layout5);
        loginLayout.addView(layout6);
        loginLayout.addView(layout7);
        loginLayout.addView(layout8);
        loginLayout.addView(layout9);
        loginLayout.addView(layout10);
        loginLayout.addView(layout11);

        //layout8.addView(layout10);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout4.setOrientation(LinearLayout.HORIZONTAL);
        layout5.setOrientation(LinearLayout.HORIZONTAL);
        layout6.setOrientation(LinearLayout.HORIZONTAL);
        layout7.setOrientation(LinearLayout.HORIZONTAL);
        layout8.setOrientation(LinearLayout.HORIZONTAL);
        layout9.setOrientation(LinearLayout.HORIZONTAL);
        layout10.setOrientation(LinearLayout.HORIZONTAL);
        layout11.setOrientation(LinearLayout.HORIZONTAL);
        layout11.setGravity(View.TEXT_ALIGNMENT_CENTER);




        TextView name = new TextView(this);
        name.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView username = new TextView(this);
        username.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView password = new TextView(this);
        password.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView contact = new TextView(this);
        contact.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView dateofbirth = new TextView(this);
        dateofbirth.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView qualification = new TextView(this);
        qualification.setLayoutParams(new LinearLayout.LayoutParams(160,65));
        TextView gender = new TextView(this);
        gender.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView hobbies = new TextView(this);
        hobbies.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView proflePic = new TextView(this);
        proflePic.setLayoutParams(new LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT));

        EditText etName1 = new EditText(this);
        etName1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText etUserName1 = new EditText(this);
        etUserName1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText etContact1 = new EditText(this);
        etContact1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        etContact1.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText etPassword1 = new EditText(this);
        etPassword1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText etdate1 = new EditText(this);
        etdate1.setClickable(true);
        etdate1.setFocusable(false);
        etdate1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        DatePicker datePicker1 = new DatePicker(this);
        datePicker1.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        Spinner sp11 = new Spinner(this);
        sp11.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        rgGender1 = new RadioGroup(this);
        rgGender1.setOrientation(RadioGroup.VERTICAL);
        rbMale1 = new RadioButton(this);
        rbFemale1 = new RadioButton(this);

        CheckBox cbFootball1 = new CheckBox(this);
        CheckBox cbBasketball1 = new CheckBox(this);
        CheckBox cbCricket1 = new CheckBox(this);

        ImageView imageView1 = new ImageView(this);

        Button btnInsert1 = new Button(this);
        btnInsert1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Button btnView1 = new Button(this);
        btnView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Button btnSignIn1 = new Button(this);
        Button btnListView1 = new Button(this);

        name.setText("Name ");
        username.setText("Username ");
        password.setText("Password ");
        contact.setText("Contact ");
        dateofbirth.setText("Dateofbirth ");
        qualification.setText("Qualification ");
        gender.setText("Gender ");
        hobbies.setText("Hobby ");
        proflePic.setText("ProfilePic ");
        rbMale1.setText("Male");
        rbFemale1.setText("Female");
        cbFootball1.setText("Football");
        cbBasketball1.setText("Basketball");
        cbCricket1.setText("Cricket");
        btnInsert1.setText("Add Data");
        btnView1.setText("View Data");
        btnSignIn1.setText("Click Here For SignIn");
        btnListView1.setText("View Data in List View");
        imageView1.setImageResource(R.drawable.ic_cam);

        etName1.setId(1);
        etUserName1.setId(2);//etUsername
        etContact1.setId(12);
        etPassword1.setId(3);//etPassword
        etdate1.setId(4);//etdate
        sp11.setId(5);//sp1
        imageView1.setId(6);//imageView
        cbFootball1.setId(7);//cbFootball
        cbBasketball1.setId(8);//cbBasketball
        cbCricket1.setId(9);//cbCricket
        rbMale1.setId(10);//rbMale
        rbFemale1.setId(11);//rbFemale
        //dpResult.setId(12);//dpResult
        btnInsert1.setId(13);//btnInsert
        btnView1.setId(15);
        datePicker1.setId(14);//btnInsert
        btnSignIn1.setId(18);//btnInsert
        btnListView1.setId(16);//btnInsert
        rgGender1.setId(17);//btnInsert

        rgGender1.addView(rbMale1);
        rgGender1.addView(rbFemale1);
       // rgGender1.addView(rbFemale1);
//        rgGender1.addView(rbFemale1);
        layout1.addView(name);
        layout2.addView(username);
        layout3.addView(password);
        layout4.addView(contact);
        layout5.addView(dateofbirth);
        layout6.addView(qualification);
        layout7.addView(gender);
        layout8.addView(hobbies);
        layout10.addView(proflePic);

        layout1.addView(etName1);
        layout2.addView(etUserName1);
        layout3.addView(etPassword1);
        layout4.addView(etContact1);
        layout5.addView(etdate1);
        layout5.addView(datePicker1);

        layout6.addView(sp11);
        layout7.addView(rgGender1);
        //layout7.addView(rbMale1);
       // layout7.addView(rbFemale1);

        layout8.addView(cbFootball1);
        layout8.addView(cbBasketball1);
        layout8.addView(cbCricket1);
        layout10.addView(imageView1);
        layout11.addView(btnInsert1,tvLoginParams);
        layout11.addView(btnView1,tvLoginParams);

        loginLayout.addView(btnSignIn1);
        loginLayout.addView(btnListView1);

        setContentView(scrollView);
        //setContentView(R.layout.activity_main);



        myDb = new DatabaseHelper(getBaseContext());
        etName = (EditText) findViewById(etName1.getId());
        etUserName = (EditText) findViewById(etUserName1.getId());
        etContact = (EditText) findViewById(etContact1.getId());
        etPassword = (EditText) findViewById(etPassword1.getId());
        etdate = (EditText) findViewById(etdate1.getId());
        btnInsert = (Button) findViewById(btnInsert1.getId());
        btnView = (Button) findViewById(btnView1.getId());
        sp1=(Spinner)findViewById(sp11.getId());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hobbylist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        imageView= (ImageView) findViewById(imageView1.getId());
        cbFootball=(CheckBox)findViewById(cbFootball1.getId());
        cbBasketball=(CheckBox)findViewById(cbBasketball1.getId());
        cbCricket=(CheckBox)findViewById(cbCricket1.getId());
        rbFemale = (RadioButton) findViewById(rbFemale1.getId());
        rbMale = (RadioButton) findViewById(rbMale1.getId());
        //tvDisplayDate = (EditText)findViewById(etdate1.getId());
        dpResult = (DatePicker)findViewById(datePicker1.getId());
        btnSignIn =(Button)findViewById(btnSignIn1.getId());
        rgGender = (RadioGroup) findViewById(rgGender1.getId());

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v
            ) {

                Intent intent = new Intent(RegisterActivity.this, DynamicForm.class);
                startActivity(intent);
                /*layoutMethod();
                textView();
                editTextDemo();*/
            }
        });
        btnListview = (Button)findViewById(btnListView1.getId());
        btnListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,DataListActivity.class);
                startActivity(intent);
            }
        });


      //  Log.d("name","name"+rgGender.getText());

        final Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        idd = getIntent().getStringExtra("idd");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        if(idd == null)
        {
            insertData();
            setCurrentDate();
        }
        else {

            updateData();
            btnInsert.setText("Update Data");
        }
        genderSelection();
        getData();
        viewData();

        dateDialog();
    }

    public void layoutMethod(){
        layout12 = new LinearLayout[count];
        for(int i = 0; i< count;i++) {
            layout12[i] = new LinearLayout(this);
            loginLayout.addView(layout12[i]);
            layout12[i].setOrientation(LinearLayout.HORIZONTAL);
        }
    }

    public void textView() {
        TextView[] textView = new TextView[count];
        for (int i = 0; i < count; i++) {
            textView[i] = new TextView(this);
            textView[i].setText("TextView");
            textView[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout12[i].addView(textView[i]);
        }
    }
    public void editTextDemo()
    {

        EditText[] editText = new EditText[count];
        for(int i = 0; i< count; i++) {

            editText[i] = new EditText(this);
            editText[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout12[i].addView(editText[i]);
        }
    }

    public void spinnerDemo()
    {
        Spinner spinner = new Spinner(this);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }
    public void radioButton()
    {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }
    public void radioGroup()
    {
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }
    public void checkBox()
    {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void insertData() {

        etUserName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Cursor cursor = myDb.getSingleData(etUserName.getText().toString());
                if (cursor.getCount() > 0)
                    etUserName.setError("User name already exist");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor cursor = myDb.getSingleData(etUserName.getText().toString());
                hobby = new StringBuilder();
                if (cbFootball.isChecked()) {
                    hobby.append("Football ");
                }

                if (cbBasketball.isChecked()) {
                    hobby.append("Basketball ");
                }

                if (cbCricket.isChecked()) {
                    hobby.append("Cricket ");
                }

                if (etName.getText().toString().trim().equals(""))
                    etName.setError("Enter Name");


                else if (etUserName.getText().toString().trim().equals(""))
                    etUserName.setError("Enter Username");
                else if (etContact.getText().toString().trim().equals(""))
                    etContact.setError("Enter Contact");
                else if (etPassword.getText().toString().trim().equals(""))
                    etPassword.setError("Enter Password");
                else if (cursor.getCount() > 0) {
                    etUserName.setError("User name already exist");
                } else {
                    boolean isInserted = myDb.insertData(etName.getText().toString(), etUserName.getText().toString(), etContact.getText().toString(), etPassword.getText().toString(), etdate.getText().toString(), sp1.getSelectedItem().toString(), gender, hobby.toString(), img);
                    if (isInserted == true) {
                        Toast.makeText(RegisterActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(RegisterActivity.this, "Data Not Inerted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getData() {

        try {
           // myDb = new DatabaseHelper(getBaseContext());
            Cursor res = myDb.getData(idd);


            if (res.moveToFirst()) {
                do {
                    etName.setText(res.getString(1));
                    etUserName.setText(res.getString(2));
                    etContact.setText(res.getString(3));
                    etPassword.setText(res.getString(4));
                    etdate.setText(res.getString(5));
                    qValue = res.getString(6);
                    genderStr = res.getString(7);
                    hobbyStr = res.getString(8).toString();
                    //Toast.makeText(getBaseContext(),"Values"+ res.getString(1) + res.getString(2)+ res.getString(3)+ res.getString(4)+ res.getString(5)+ res.getString(6)+ res.getString(7)+ res.getString(8) + res.getColumnIndex("PROFILEPIC"),Toast.LENGTH_LONG).show();
                    img = res.getBlob(res.getColumnIndex("PROFILEPIC"));


                    Log.d("Gender", genderStr);
                    if (res.getString(7).equals("Male")) {
                        rbMale.setChecked(true);
                    } else if (res.getString(7).equals("Female")){
                        rbFemale.setChecked(true);
                    }

                    String [] hobbieList = hobbyStr.split(" ");

                    for (String value : hobbieList){
                        if(value.equals("Football"))
                            cbFootball.setChecked(true);
                        else if (value.equals("Basketball"))
                            cbBasketball.setChecked(true);
                        else if (value.equals("Cricket"))
                            cbCricket.setChecked(true);
                    }

                    setQualification();

                }
                while (res.moveToNext());
                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                imageView.setImageBitmap(bitmap);

            }


        } catch (Exception e) {
            Log.d("TAG", "username" + e.toString());
        }
    }

public void updateData(){
    etUserName.setEnabled(false);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    //String idd = getIntent().getStringExtra("idd");
                    hobby = new StringBuilder();
                    if (cbFootball.isChecked()) {
                        hobby.append("Football ");
                    }

                    if (cbBasketball.isChecked()) {
                        hobby.append("Basketball ");
                    }

                    if (cbCricket.isChecked()) {
                        hobby.append("Cricket ");
                    }
                    Log.d("Values",(idd+ etName.getText().toString()+ etUserName.getText().toString()+ etContact.getText().toString()+ etPassword.getText().toString()+ etdate.getText().toString()+ sp1.getSelectedItem().toString()+ gender+ hobby.toString()+ img));
                    boolean isUpdate = myDb.updateData(idd, etName.getText().toString(), etUserName.getText().toString(), etContact.getText().toString(), etPassword.getText().toString(), etdate.getText().toString(), sp1.getSelectedItem().toString(), gender, hobby.toString(), img);
                    if (isUpdate == true) {
                        Toast.makeText(RegisterActivity.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(RegisterActivity.this,DataListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Data Not Updated Successfully", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Log.d("error", e.toString());
                }
            }
        });
    }


       // setGender();
       // checkBox();


    public void setQualification(){
        if(qValue.equalsIgnoreCase("SSC"))sp1.setSelection(0);
        else if(qValue.equalsIgnoreCase("HSC"))sp1.setSelection(1);
        else if(qValue.equalsIgnoreCase("MCA"))sp1.setSelection(2);
    }
    public void setGender()
    {
        if (genderStr.equalsIgnoreCase("Male")) {
            rbMale.setChecked(true);
        }
        else {
            rbFemale.setChecked(true);
        }
    }
 /*   public void checkBox()
    {
        String [] hobbieList = hobbyStr.split(" ");

        for (String value : hobbieList){
            if(value.equals("Football"))
                cbFootball.setChecked(true);
            else if (value.equals("Basketball"))
                cbBasketball.setChecked(true);
            else if (value.equals("Cricket"))
                cbCricket.setChecked(true);
        }

    }*/
    public void genderSelection() {




        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == rbMale1.getId()) {
                    gender = "Male";
                } else if (checkedId == rbFemale1.getId()) {
                    gender = "Female";
                }

            }
        });
    }

    public void viewData(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    Toast.makeText(RegisterActivity.this, "Nothing Found !!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id  :  " + res.getString(0) + "\n");
                        buffer.append("Name :  " + res.getString(1) + "\n");
                        buffer.append("Username  :  " + res.getString(2) + "\n");
                        buffer.append("Contact  :  " + res.getString(3) + "\n");
                        buffer.append("Password  :  " + res.getString(4) + "\n");
//                        buffer.append("Date  :  " + res.getString(5) + "\n");
                       // buffer.append("Qualification  :  " + res.getString(6) + "\n");
                       // buffer.append("Gender  :  " + res.getString(7) + "\n");
                       // buffer.append("Hobby  :  " + res.getString(8) + "\n");

                    }
                    showMessage("Data", buffer.toString());
                }
            }
        });
    }
    public void showMessage(String title, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
   /* public void onButtonClick(View v)
    {
    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

    }*/
    public void setCurrentDate()
    {


        etdate.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year).append(" "));
        dpResult.init(year, month, day, null);


    }
    public void dateDialog()
    {
        etdate.setOnClickListener(new View.OnClickListener() {
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
            etdate.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year).append(" "));
            dpResult.init(year,month,day,null);
        }
    };
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);


                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
                img=bos.toByteArray();

                Log.d("Image Value", img.toString());
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

 /*  public void viewDetailsListView(View view)
   {
       Intent intent=new Intent(this,DataListActivity.class);
       startActivity(intent);
   }*/
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    }



