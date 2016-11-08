package com.example.megavision01.jsondemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private String TAG = MainActivity.class.getSimpleName();
    Spinner sp1;
    private ProgressDialog pDialog;
    private ListView lv;
    String a_id;
    Button viewButton;
    // URL to get contacts JSON
    private static String urlProject = "http://androidworkingapp.site88.net/projectlist.php";
    private static String urlForm = "http://androidworkingapp.site88.net//formdesign.php";

    ArrayList<HashMap<String, String>> contactList;
    List<String> spinnerArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(getBaseContext());
        contactList = new ArrayList<>();
        sp1 = (Spinner) findViewById(R.id.sp1);
        //lv = (ListView) findViewById(R.id.list);
        new GetContacts().execute();
        viewButton= (Button) findViewById(R.id.btnView);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData1();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nothing Found !!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Data Id  :  " + res.getString(0) + "\n");
                        buffer.append("Project Id  :  " + res.getString(1) + "\n");
                       buffer.append("Record ID  :  " + res.getString(2) + "\n");
                        buffer.append("Formid   :  " + res.getString(3) + "\n");
                        buffer.append("Value  :  " + res.getString(4) + "\n");
                        buffer.append("\n");
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





    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlProject);
            String jsonStr1 = sh.makeServiceCall(urlForm);
            Log.e(TAG, "Response from url: " + jsonStr);
            //Log.e(TAG, "Response from url1: " + jsonStr1);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject jsonObj1 = new JSONObject(jsonStr1);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");
                    JSONArray contacts1 = jsonObj1.getJSONArray("formData");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String idd = c.getString("proj_id");
                        String name = c.getString("proj_name");
                        HashMap<String, String> contact = new HashMap<>();
                        contact.put("id", idd);
                        contact.put("name", name);
                        Cursor res1 = myDb.getSingleData(idd);

                        if(res1.getCount() == 0 ) {
                            boolean isInserted = myDb.insertData(idd, name);
                            if (isInserted == true) {
                                Log.d("row inserted", idd + name);

                            } else
                                Toast.makeText(MainActivity.this, "Data Not Inerted", Toast.LENGTH_LONG).show();
                        }
                        contactList.add(contact);

                    }


                    for (int i = 0; i < contacts1.length(); i++) {
                        JSONObject c1 = contacts1.getJSONObject(i);

                        String fid = c1.getString("fid");
                        String proj_id = c1.getString("proj_id");
                        String slabel = c1.getString("slabel");
                        String type = c1.getString("type");
                        String options = c1.getString("options");
                        Log.d("Valueddd",fid+proj_id+slabel);

                        Cursor res1 = myDb.getSingleData1(fid);
                        if(res1.getCount()  == 0 ) {
                            boolean isInserted = myDb.insertData1( proj_id, slabel, type, options);
                            if (isInserted == true) {
                            } else
                                Toast.makeText(MainActivity.this, "Data Not Inerted", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

           /*ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[]{"name",
                    }, new int[]{R.id.name,
                    });
                    lv.setAdapter(adapter);*/
            try {
                // myDb = new DatabaseHelper(getBaseContext());
                Cursor res = myDb.getAllData();
                if (res.moveToFirst()) {
                    do {
                        //etName.setText(res.getString(1));
                        //etUserName.setText(res.getString(2));
                        String id= res.getString(1);
                        String project_name= res.getString(2);
                        spinnerArray.add(project_name);
                    }
                    while (res.moveToNext());
                }


            } catch (Exception e) {
                Log.d("TAG", "username" + e.toString());
            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                    MainActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adapter1);
           sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   String size = sp1.getSelectedItem().toString();
                   String item = parent.getItemAtPosition(position).toString();
                   DatabaseHelper dbh1 = new DatabaseHelper(getApplicationContext());
                   SQLiteDatabase db1 = dbh1.getWritableDatabase();
                   Cursor cursor = db1.rawQuery("Select P_ID from myProject where P_NAME = '"+item+"'", null);
                   if (cursor.moveToFirst()) {

                       do {
                            a_id = cursor.getString(0);
                           //Toast.makeText(parent.getContext(), "Selected: " + a_id, Toast.LENGTH_LONG).show();
                           Intent intent = new Intent(MainActivity.this,DynamicForm.class);
                           intent.putExtra("id", a_id);

                       }
                       while (cursor.moveToNext());
                   }
                   Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
                   btnSubmit.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(MainActivity.this, DynamicForm.class);
                           intent.putExtra("id", a_id);
                           startActivity(intent);
                       }
                   });

               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });
            }

        }


    public void showMessage(String title, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    }

