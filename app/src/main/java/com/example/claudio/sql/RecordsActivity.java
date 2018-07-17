package com.example.claudio.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.StringReader;

public class RecordsActivity extends AppCompatActivity {
    ListView mlist;
    Button mbtn1, mbtn2;
    SQLiteDatabase mdatabase;
    EditText medit;
   // String[] mvoters = {};  //brief explanation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);


        mbtn1 = findViewById(R.id.btnNewUpdate);
        mbtn2 = findViewById(R.id.btnNewDelete);

        mlist = findViewById(R.id.listViewVoters);

        medit = findViewById(R.id.edtDelete);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });




        mdatabase = openOrCreateDatabase("voter", MODE_PRIVATE, null);

        Cursor myCursor = mdatabase.rawQuery("SELECT * FROM voters", null);


        //check if any data exists
        if (myCursor.getCount()==0){
            display("NO DATA", "There is no data to be displayed");
        }

        StringBuffer stringBuffer = new StringBuffer();

        while (myCursor.moveToNext()){
            stringBuffer.append("Name : "+ myCursor.getString(0) + "\n" +"Id_no : " + myCursor.getString(1) + "\n" +"Constituency : " + myCursor.getString(2) + "\n#");


        }

        String[] stringArray=stringBuffer.toString().split("#");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.support, stringArray);
        mlist.setAdapter(adapter);


     //   ArrayAdapter<String> adapter= new ArrayAdapter<>(getBaseContext(), R.layout)

        //mdatabase.execSQL("SELECT * FROM voters");



        //mvoters = getIntent().getStringArrayExtra("voters");
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mvoters);

        //derived line from previous notes project code need explanation
       // mlist.setAdapter(adapter);
    }
    public void display(String title, String message) {

        AlertDialog.Builder display = new AlertDialog.Builder(this); //"this" is the context

        display.setCancelable(true);
        display.setTitle(title);
        display.setMessage(message);
        display.show();
    }
}
