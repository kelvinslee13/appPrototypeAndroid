package com.example.seunghyunlee.androidproject1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputNumber = (EditText) findViewById(R.id.inputNumber);
        final EditText inputBase = (EditText) findViewById(R.id.inputBase);

        final EditText outputBase = (EditText) findViewById(R.id.outputBase);
        final TextView answer = (TextView) findViewById(R.id.answer);

        final Button enterButton = (Button) findViewById(R.id.enterButton);


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = inputNumber.getText().toString();
                String base = inputBase.getText().toString();
                String output = outputBase.getText().toString();

                ArrayList<Integer> listOfNumbers = new ArrayList();

                int baseNumber = Integer.parseInt(base);

                for(int i=0; i<number.length();i++){
                    listOfNumbers.add(number.indexOf(i));
                }

                for(int i=0; i<listOfNumbers.size();i++){
                    if(listOfNumbers.get(i)>baseNumber){
                        inputBase.setBackgroundResource(R.color.shade);
                        Toast.makeText(MainActivity.this, "Wrong Input", Toast.LENGTH_SHORT).show();
                    }
                }


                if(number.isEmpty() || base.isEmpty() || output.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fill in the blanks", Toast.LENGTH_SHORT).show();
                }
                try {
                    int decimalInput = Integer.parseInt(number, Integer.parseInt(base));
                    answer.setText(Integer.toString(decimalInput, Integer.parseInt(output)));

                }catch (NumberFormatException nfe){


                }


            }
        });



    }




}
