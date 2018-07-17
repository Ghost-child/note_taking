package com.example.claudio.sql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //step 1 create a variable to SQLiteDatabase
    SQLiteDatabase database;

    EditText medit, medit1, medit2;
    Button msave, mview, mupdate, mdelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medit = findViewById(R.id.edtEnterName);
        medit1 = findViewById(R.id.edtID);
        medit2 = findViewById(R.id.edtCons);

        msave = findViewById(R.id.btnSave);
        mview = findViewById(R.id.btnView);
        mupdate = findViewById(R.id.btnUpdate);
        mdelete = findViewById(R.id.btnDelete);

        // step 2 create a database
        database = openOrCreateDatabase("voter", MODE_PRIVATE, null);

        //step 3 create a table
        database.execSQL("CREATE TABLE IF NOT EXISTS voters (name VARCHAR, id_no VARCHAR, constituency VARCHAR)");

        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (medit.getText().toString().length()==0){
                   display("Enter name", "kindly enter your name");

                } else if( medit1.getText().toString().length()==0 ){
                    display("ID_No?", "Kindly enter your Identification number");

                }else if(medit2.getText().toString().length()==0 ){
                    display("constituency?", "Kindly enter your constituency");

                }else {
                    database.execSQL("INSERT INTO voters VALUES ('" + medit.getText() + "','" + medit1.getText() + "','" + medit2.getText() + "')");
                    display("SAVED SUCCESSFULLY", "DETAILS SAVED");
                    clear();
                }

            }
        });

        mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor view = database.rawQuery("SELECT * FROM voters", null);
                if (view.getCount()==0 ){
                    display("No data", "No data stored");
                }
                StringBuffer sbuffer = new StringBuffer();

                while(view.moveToNext()){
                    sbuffer.append("Name " + view.getString(0));
                    sbuffer.append("\n");
                    sbuffer.append("id_no " + view.getString(1));
                    sbuffer.append("\n");
                    sbuffer.append("constituency " + view.getString(2));
                    sbuffer.append("\n..........................................\n");

                }
                display("My details", sbuffer.toString());
            }
        });

        mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Update your records", Toast.LENGTH_SHORT).show();
            }
        });

        mdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if it dosent work il use if statement for all edit boxes to check if they have something
                //..........then run below statements as else{

                Intent myListview = new Intent(getApplicationContext(), RecordsActivity.class);
                startActivity(myListview);

            }
        });
    }
    //method to create a dialog box
    public void display(String title, String message){

        AlertDialog.Builder popup = new AlertDialog.Builder(this); //" this" is the context

        popup.setCancelable(true);
        popup.setTitle(title);
        popup.setMessage(message);
        popup.show();
    }

    //method to make text clear after being saved

    public void clear() {
        medit.setText("");
        medit1.setText("");
        medit2.setText("");
    }
}
