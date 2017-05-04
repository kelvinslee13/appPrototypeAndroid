package com.example.seunghyunlee.emergency;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int CONTACT_REQUEST = 1;

    private Uri contact;
    private String name;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        if(prefs.contains("contact") && prefs.contains("name")){
            contact = Uri.parse(prefs.getString("contact", null));
            name = prefs.getString("name", null);
            showEmergencyContact();
        }
        Button set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Choose options from contact
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);


                startActivityForResult(intent, CONTACT_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result){
        if(requestCode == CONTACT_REQUEST && resultCode == RESULT_OK){
            contact = result.getData();
            name = getContactName();
            showEmergencyContact();
        }
    }

    private String getContactName(){
        String[] columns = {ContactsContract.Contacts.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(contact, columns, null, null,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    private void showEmergencyContact(){
        TextView choice = (TextView) findViewById(R.id.choice);
        choice.setText(name);
        choice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, contact);
                startActivity(intent);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        System.out.println("Paused");
    }

    //Sa
    protected void onStop(){
        super.onStop();
        System.out.println("Stopped");

        if(contact != null){
            //Mode_private (only this app has access to this)
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("contact", contact.toString());
            editor.putString("name", name);
            editor.commit();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        System.out.println("Destroyed");
    }
}
