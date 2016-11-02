package com.example.megavision01.sqliteapp;
        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.BitmapFactory;
        import android.preference.CheckBoxPreference;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Gravity;
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
        import android.widget.ScrollView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class DynamicForm extends AppCompatActivity {
    LinearLayout.LayoutParams fittype,textLayout;
    private int year,year2;
    private int month,month2;
    private int day,day2;
    public static final int DATE_DIALOG_ID = 999;
    //EditText tvDisplayDate;
    DatePicker dpResult;
    DatabaseHelper myDb;
    EditText etUsername,etdate;
    Spinner sp1;
    RadioGroup gender;
    String genderStr;
    RadioButton rbMale1,rbFemale1;
    CheckBox hobby;
    String datevalue;
    DatePickerDialog.OnDateSetListener datePickerListener;
    String str_name="",str_type="",str_value="",project_id="";
    String TAG = DynamicForm.class.getSimpleName();
    int str_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(getBaseContext());
        /*int[] id = {1,2,3,4,5};
        String[] label = {"Name","DOB","Qualifiaction","Gender","checkbox"};
        String[] type = {"text", "date", "dropdown","radio","checkbox"};
        String option = "SSC,HSC,MCA";
        String optionRadio = "Male,Female,other,ck";
        String str_name_value = "hobby1,hobby2,hobby3";*/

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout formLayout = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(formLayout);
/*
        LinearLayout formLayoutChild = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout formLayoutChild1 = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout formLayoutChild2 = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout formLayoutChild3 = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout formLayoutChild4 = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout formLayoutChild5 = new LinearLayout(this);
        formLayout.setOrientation(LinearLayout.VERTICAL);*/



        /*formLayout.addView(formLayoutChild);
        formLayout.addView(formLayoutChild1);
        formLayout.addView(formLayoutChild3);
        formLayout.addView(formLayoutChild4);
        formLayout.addView(formLayoutChild5);*/


        fittype = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        fittype.setMargins(10,0,10,0);
        textLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textLayout.setMargins(10,0,10,0);
        try {

            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            SQLiteDatabase db1 = databaseHelper.getWritableDatabase();
            Cursor cursor = db1.rawQuery("select * from myInfo1 where P_ID=1 ",null);
            if (cursor.moveToFirst()) {
                do {
                    str_id=cursor.getInt(0);
                    str_name=cursor.getString(2);
                    str_type=cursor.getString(3);
                    str_value=cursor.getString(4);
                    project_id=cursor.getString(1);
                    Log.d("val",str_id+str_name+str_type+str_value);

                    if(str_type.equals("text"))
                    {
                        formLayout.addView(textView(str_name));
                        formLayout.addView(editText(str_name, str_id));
                    }
                    if(str_type.equals("date")){
                        formLayout.addView(textView(str_name));
                        formLayout.addView(editText(str_name, str_id));
                    }
                    if(str_type.equals("dropdown")){

                        formLayout.addView(textView(str_name));
                        formLayout.addView(qualifiaction(str_value));
                    }
                    if(str_type.equals("radio")){

                        formLayout.addView(textView(str_name));
                        formLayout.addView(radiogroup(str_value, str_id));
                    }

                    if(str_type.equals("checkbox")){

                        formLayout.addView(textView(str_name));
                        String [] str_chk1=str_value.split(",");
                        for(int i=0;i<str_chk1.length;i++)
                        {
                            formLayout.addView(checkBox(1, str_chk1[i]));
                        }

                    }

                }
                while (cursor.moveToNext());
            }


        }catch (Exception e){
            Log.d("Exp",e.toString());
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
        formLayout.addView(submit);
        setContentView(scrollView);


    }

    private TextView textView(String label ) {
        TextView textView = new TextView(this);
        //textView.setId(hint);
        textView.setTextSize(20);
        //textView.setHint(uname);
        textView.setText(label);
        textView.setLayoutParams(textLayout);
        //  loginLayout.addView(textView);
        return textView;
    }

    private EditText editText(String name, int id){
        final EditText editText = new EditText(this);
        editText.setId(id);
        etUsername = (EditText) findViewById(editText.getId());
        if(name == "DOB"){

            setCurrentDate(editText);
            addListerOnButton(editText);
            datechanged(editText);
            return editText;
        }
        else {
            editText.setLayoutParams(fittype);
            editText.setHint(name);

            return editText;
        }

    }


    private Spinner qualifiaction(String options){
        Spinner qualifiactionSpinner = new Spinner(this);

        qualifiactionSpinner.setId(1002);
        sp1= (Spinner) findViewById(qualifiactionSpinner.getId());
        String[] optionList = options.split(",");

        List<String> spinnerArray =  new ArrayList<String>();
        for(String value : optionList){
            spinnerArray.add(value);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            qualifiactionSpinner.setAdapter(adapter);
        }

        qualifiactionSpinner.setLayoutParams(fittype);


        return qualifiactionSpinner;


    }

    private RadioGroup radiogroup(String optionRadio,int id){

        RadioGroup radioGroup = new RadioGroup(getApplicationContext());
        radioGroup.setId(id);
        gender = (RadioGroup) findViewById(radioGroup.getId());
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setLayoutParams(fittype);
        String[] optionRadioList = optionRadio.split(",");

        RadioButton[] radioButton = new RadioButton[optionRadioList.length];

        for(int i = 0 ; i<optionRadioList.length;i++){

            radioButton[i] = new RadioButton(this);
            radioButton[i].setText(optionRadioList[i]);
            radioButton[i].setTextSize(22);
            radioGroup.addView(radioButton[i]);
        }
        return radioGroup;
    }

    private CheckBox checkBox(int a_id,String strvalue) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        hobby = (CheckBox) findViewById(checkBox.getId());
        //textCheckBoxList.add(checkBox);


        return checkBox;
    }


  /*  private CheckBox checkBox(int a_id,String strvalue,String settext) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        if(settext.equals("null"))
        {
            checkBox.setChecked(false);
        }
        else
        {
            checkBox.setChecked(true);
        }


        return checkBox;
    }
*/


    public void setCurrentDate(EditText editText)
    {  dpResult = new DatePicker(this);
        //dpResult = (DatePicker)findViewById(R.id.datePicker);
        final Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        editText.setText(new StringBuilder().append(day).append("-").append(month+1).append("-").append(year).append(" "));
        dpResult.init(year, month, day, null);


    }

    public void addListerOnButton(EditText editText)
    {

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    // Show your calender here
                    showDialog(DATE_DIALOG_ID);

                } else {
                    // Hide your calender here
                }
            }
        });


    }

    public Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);
        }
        return null;
    }
    public  void datechanged(final EditText edit) {
        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                // datevalue = (new StringBuilder().append(day).append("-").append(month).append("-").append(year).append(" ")).toString();
                //Log.d(TAG,datevalue.toString());
                edit.setText(new StringBuilder().append(day).append("-").append(month+1).append("-").append(year).append(" "));
                dpResult.init(year, month, day, null);
            }
        };
    }







}
        /*ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


        loginLayout = new LinearLayout(this);
        loginLayout.setOrientation(LinearLayout.VERTICAL);
        loginLayout.setBackgroundResource(R.drawable.bc2);
        LinearLayout layout1 = new LinearLayout(this);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout layout3 = new LinearLayout(this);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout layout4 = new LinearLayout(this);
        layout4.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout layout5 = new LinearLayout(this);
        layout5.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout layout1 = new LinearLayout(this);
        layout1.setOrientation(LinearLayout.HORIZONTAL);

        loginLayout.addView(layout1);
        sp1 = new Spinner(this);
        sp1.setLayoutParams(new LinearLayout.LayoutParams(220, LinearLayout.LayoutParams.WRAP_CONTENT));
        Button btnAdd = new Button(this);
        btnAdd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp1.setId(1);
        btnAdd.setId(2);
        btnAdd.setText("Add");
        layout1.addView(sp1);
        layout1.addView(btnAdd);*//**//*

        String[] str_name_type={"TextView","EditText","Dropdown","Radio","CheckBox"};
         String[] str_value = {"Male","Female"};
        String[] str_name_value = {"ck1","ck2","ck3"};

        scrollView.addView(loginLayout);
        loginLayout.addView(layout1);
        loginLayout.addView(layout2);
        loginLayout.addView(layout3);
        loginLayout.addView(layout4);
        loginLayout.addView(layout5);
        if(str_name_type[0] == "TextView")
        {
          layout1.addView(textView(1, "uname", ""));
        }
        if(str_name_type[1] == "EditText")
        {
            layout2.addView(textView(1, "uname", "Username"));
            layout2.addView(editLongText(1, "fsd"));
        }

        if(str_name_type[2] == "Dropdown")
        {
            layout3.addView(textView(1, "uname", "Qualification"));
            layout3.addView(spinner(1));
        }
        if(str_name_type[3] == "Radio")
        {
            layout4.addView(textView(1, "uname", "Gender"));
            layout4.addView(radioGroup(1,"male,female,other"));
        }

        setContentView(scrollView);




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
    public void editText()
    {

        EditText[] editText = new EditText[count];
        for(int i = 0; i< count; i++) {

            editText[i] = new EditText(this);
            editText[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout12[i].addView(editText[i]);
        }
    }


    private LinearLayout linearLayout(int a_id) {

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }

    private TextView textView(int hint,String uname ,String setText) {
        TextView textView = new TextView(this);
        textView.setId(hint);
        textView.setTextSize(1, 20);
        textView.setHint(uname);
        textView.setText(setText);
      //  loginLayout.addView(textView);
        return textView;
    }

    private EditText editLongText(int a_id, String settext) {
        EditText editLongText = new EditText(this);
        editLongText.setId(a_id);
        editLongText.setHint(settext);
        editLongText.setTextSize(1, 20);
        editLongText.setGravity(Gravity.TOP);
        editLongText.setHeight(100);
        editLongText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return editLongText;
    }

    private Spinner spinner(int a_id) {
        Spinner spinner = new Spinner(this);
        spinner.setId(a_id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hobbylist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return spinner;
    }

    private RadioGroup radioGroup(int a_id,String strvalue) {
        RadioGroup radioGroup = new RadioGroup(this);
            // radioButton.setText(strvalue);
            radioGroup.setId(a_id);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.clearCheck();
            String[] str_chk1 = strvalue.split(",");
            for (int i = 0; i < str_chk1.length; i++) {
                radioGroup.addView(radioButton(a_id,str_chk1[i]));
            }

        RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
        for (int i=0; i<5; i++) {
            rb[i] = new RadioButton(this);
            rb[i].setText(" " + ContactsActivity.phonetype.get(i)
                    + "    " + ContactsActivity.phone.get(i));
            rb[i].setId(i + 100);
            rg.addView(rb[i]);
        }
        return rb;
    }

    private RadioButton radioButton(int a_id,String strvalue) {

        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(strvalue);
        radioButton.setId(a_id);
        return radioButton;
    }



    private CheckBox checkBox(int a_id,String strvalue) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        return checkBox;
    }

    private CheckBox checkBox(int a_id,String strvalue,String settext) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        if(settext.equals("null"))
        {
            checkBox.setChecked(false);
        }
        else
        {
            checkBox.setChecked(true);
        }
        textCheckBoxList.add

                (checkBox);


        return checkBox;
    }
   private Spinner spinner(int a_id,String strvalue,String matchvalue) {
        Spinner spinner = new Spinner(this);
        spinner.setId(a_id);
        List<String> list = new ArrayList<String>();
        try{
            String [] str_chk1=strvalue.split(",");
            list.add("Select");
            int selectspinner=0;
            for(int i=0;i<str_chk1.length;i++)
            {
                list.add(str_chk1[i]);
                if (str_chk1[i].equals(matchvalue))
                {

                    selectspinner=i+1;
                }
            }
            textSpinnerList.add(spinner.getSelectedItemPosition(),spinner);
            if (selectspinner>0)
            {
                spinner.setSelection(selectspinner);
            }
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error code: fd814", Toast.LENGTH_SHORT).show();
            System.out.println("  EXCEPTION fd814=="+e);
        }
        //spinner=spinner.getSelectedItemPosition();
        return spinner;
    }


   private RadioButton radioButton(int hint,int a_id,String strvalue,String matchvalue) {   //965

        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(strvalue);
        radioButton.setId(hint);
        textRadioButtonList.add(radioButton);
        return radioButton;
    }

   private CheckBox checkBox(int a_id,String strvalue) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(strvalue);
        checkBox.setId(a_id);
        textCheckBoxList.add(checkBox);
        return checkBox;
    }



    private ImageView imageView(String strvalue) {

    }
*/


