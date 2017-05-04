package com.example.seunghyunlee.colorbuttons;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button redButton = (Button) findViewById(R.id.red_button);
        redButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(android.R.id.content).setBackgroundColor(Color.RED);
            }
        });

        Button greenButton = (Button) findViewById(R.id.green_button);
        greenButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                findViewById(android.R.id.content).setBackgroundColor(Color.GREEN);
            }
        });

        Button blueButton = (Button) findViewById(R.id.blue_button);
        blueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                findViewById(android.R.id.content).setBackgroundColor(Color.BLUE);
            }
        });

        Button whiteButton = (Button) findViewById(R.id.white_button);
        whiteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                findViewById(android.R.id.content).setBackgroundColor(Color.WHITE);
            }
        });
    }


}
