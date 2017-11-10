package com.example.seunghyunlee.newapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;



public class SignupActivity extends AppCompatActivity {


    TextView yearText, monthText, dayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        yearText = (TextView) findViewById(R.id.yearText);
        monthText = (TextView) findViewById(R.id.monthText);
        dayText = (TextView) findViewById(R.id.dayText);





        /*
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }

        }
        */
    }
}
