package com.example.seunghyunlee.hw9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {

    public static final String STRINGNAME = "com.example.setup.HW9.STRINGNAME";
    public static final int PAGE_REQUEST = 2;

    public ArrayList<String> arrays = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        final EditText name = (EditText) findViewById(R.id.name);

        final Button add = (Button) findViewById(R.id.add);
        final Button done = (Button) findViewById(R.id.done);
        final Button cancel = (Button) findViewById(R.id.cancel);
        final LinearLayout names = (LinearLayout) findViewById(R.id.names);

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(name.length() !=0){
                    addNote(name.getText().toString());
                    arrays.add(name.getText().toString());
                    name.setText("");
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondaryActivity.this, MainActivity.class);
                startActivityForResult(intent, PAGE_REQUEST);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!arrays.isEmpty()) {
                    Intent result = new Intent();
                    result.putExtra(STRINGNAME, arrays);
                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });


    }

    private void addNote(String note) {

        TextView text = new TextView(this);
        text.setText(note);
        text.setPadding(12, 12, 12, 12);
        text.setTextSize(24);

        final LinearLayout row = new LinearLayout(this);

        row.addView(text);

        final LinearLayout names = (LinearLayout) findViewById(R.id.names);
        names.addView(row);

    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for (int i=0; i < arrays.size(); i++) {
            savedInstanceState.putString()
        }
    }
}
