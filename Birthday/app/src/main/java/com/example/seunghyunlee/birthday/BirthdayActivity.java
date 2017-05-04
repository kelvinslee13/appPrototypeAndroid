package com.example.seunghyunlee.birthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BirthdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.NAME);

        TextView song = (TextView) findViewById(R.id.song);
        song.append(getString(R.string.happy_birthday_to_you )+"!\n");
        song.append(getString(R.string.happy_birthday_to_you)+"!!\n");
        song.append(getString(R.string.happy_birthday_dear)+" "+name+"\n");
        song.append(getString(R.string.happy_birthday_to_you)+"!!!");
    }
}
