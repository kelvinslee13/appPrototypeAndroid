package com.example.seunghyunlee.hw9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    public static final int PAGE_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button build = (Button) findViewById(R.id.build);
        final Button clear = (Button) findViewById(R.id.clear);
        final LinearLayout teams = (LinearLayout) findViewById(R.id.teams);

        ArrayList<String> some = new ArrayList();
        for(int i=0; i<teams.getChildCount();i++){
            some.add(teams.getChildAt(i).toString());
        }

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        for (int i=0; prefs.contains(Integer.toString(i)); i++)
            some.add(prefs.getString(Integer.toString(i), null));

        addTeam(some);




        build.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivityForResult(intent, PAGE_REQUEST);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                teams.removeAllViews();
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.clear().commit();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == PAGE_REQUEST && resultCode == RESULT_OK)
            addTeam(result.getStringArrayListExtra(SecondaryActivity.STRINGNAME));
    }

    private void addTeam(ArrayList<String> teams) {
        final LinearLayout row = (LinearLayout) findViewById(R.id.teams);

        for(int i=0; i<teams.size();i++){
            TextView text = new TextView(this);
            text.setText(teams.get(i));
            text.setPadding(12, 12, 12, 12);
            text.setTextSize(24);
            row.addView(text);
        }
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit= prefs.edit();

        LinearLayout teams = (LinearLayout) findViewById(R.id.teams);
        for(int i =0; i<teams.getChildCount();i++){
            TextView team = (TextView) teams.getChildAt(i);
            edit.putString(Integer.toString(i), team.getText().toString());
        }
        edit.commit();
    }

}
