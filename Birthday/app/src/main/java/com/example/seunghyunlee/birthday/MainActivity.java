package com.example.seunghyunlee.birthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "com.example.setup.birthday.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get user interface elements
        final EditText name = (EditText) findViewById(R.id.name);
        final Button sing = (Button) findViewById(R.id.sing);

        sing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name_input = name.getText().toString();
                if(!name_input.isEmpty()){
                    Intent intent = new Intent(MainActivity.this,BirthdayActivity.class);
                    intent.putExtra(NAME, name_input);
                    startActivity(intent);

                }
            }
        });
    }
}
