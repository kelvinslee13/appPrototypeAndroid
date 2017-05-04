package com.example.seunghyunlee.nameform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = (Button) findViewById(R.id.enter);
        final EditText firstName = (EditText) findViewById(R.id.first_name);
        final EditText lastName = (EditText) findViewById(R.id.last_name);
        final TextView message = (TextView) findViewById(R.id.message);


        //Do validation on form submit
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firstName.getText().length() ==0){
                    TextView message = (TextView) findViewById(R.id.message);
                    message.setText("First name required");
                    firstName.requestFocus();
                    return;
                }


                if(lastName.getText().length() ==0){

                    message.setText("Last name required");
                    firstName.requestFocus();
                    return;
                }


                message.setText("Thank you");
                message.requestFocus();

            }
        });
    }
}
