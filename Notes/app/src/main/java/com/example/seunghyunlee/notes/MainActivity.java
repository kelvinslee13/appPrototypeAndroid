package com.example.setup.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seunghyunlee.notes.R;

public class MainActivity extends AppCompatActivity {

    public static final int NOTE_REQUEST = 1; // Code to identify the result from NoteActivity

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore any notes that were previously saved
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        for (int i=0; prefs.contains(Integer.toString(i)); i++)
            addNote(prefs.getString(Integer.toString(i), null));

        // Tapping the add button starts NoteActivity (expecting a result)
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivityForResult(intent, NOTE_REQUEST);
            }
        });
    }

    // Result from NoteActivity gets processed here
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == NOTE_REQUEST && resultCode == RESULT_OK)
            addNote(result.getStringExtra(NoteActivity.NOTE));
    }

    // Add to the existing layout
    private void addNote(String note) {

        // Trash can icon
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_delete_24dp);
        icon.setPadding(24, 24, 24, 24);

        // The note itself
        TextView text = new TextView(this);
        text.setText(note);
        text.setPadding(12, 12, 12, 12);
        text.setTextSize(24);

        // Putting them side by side
        final LinearLayout row = new LinearLayout(this);
        row.addView(icon);
        row.addView(text);

        // In the space we set up for notes
        final LinearLayout notes = (LinearLayout) findViewById(R.id.notes);
        notes.addView(row);

        // Tapping the trash can beside a note deletes it
        icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                notes.removeView(row);
            }
        });
    }

    // This code runs whenever our application is put in the background
    protected void onPause() {
        super.onPause();

        LinearLayout notes = (LinearLayout) findViewById(R.id.notes);

        // Persistent storage for small amounts of simple data
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Store the notes using indices as keys
        for (int i=0; i < notes.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) notes.getChildAt(i);
            TextView note = (TextView) row.getChildAt(1);
            editor.putString(Integer.toString(i), note.getText().toString());
        }

        // Save
        editor.commit();
    }
}