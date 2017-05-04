package com.example.setup.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE = "com.example.setup.notes.NOTE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        final EditText note = (EditText) findViewById(R.id.note);
        final Button add = (Button) findViewById(R.id.add);

        // Tapping the add button sends note text back to MainActivity
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String note_input = note.getText().toString();
                if (!note_input.isEmpty()) {
                    Intent result = new Intent();
                    result.putExtra(NOTE, note_input);
                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });
    }
}