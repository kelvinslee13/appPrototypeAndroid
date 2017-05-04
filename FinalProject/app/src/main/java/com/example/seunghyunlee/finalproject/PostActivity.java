package com.example.seunghyunlee.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PostActivity extends AppCompatActivity {

    private Client client = new Client();
    private String setupString;
    private String punchLineString;
    public static final String SERVER = "10.32.95.80";
    public static final int PORT = 12345;
    public static final int REQUEST = 3;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText setup = (EditText) findViewById(R.id.setup);
        final EditText punchLine = (EditText) findViewById(R.id.punchLine);


        final Button post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setupString = setup.getText().toString();
                punchLineString = punchLine.getText().toString();

                if(!setupString.isEmpty() && !punchLineString.isEmpty()){
                    setup.setText("");
                    punchLine.setText("");
                    Context context = getApplicationContext();
                    CharSequence text = "Thank you for sharing jokes!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,text,duration);
                    toast.show();
                    new Thread(client).start();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill both Setup and Punch Line";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,text,duration);
                    toast.show();
                }
            }
        });


        final Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Come on. Let's share some jokes!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                Intent intent = new Intent(PostActivity.this,MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private class Client implements Runnable {

        private Socket socket;
        private Scanner in;
        private PrintWriter out;

        public void run() {
            try {
                socket = new Socket(SERVER, PORT);
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                //loading screen
                runOnUiThread(new Runnable() {
                    public void run() {
                        setContentView(R.layout.loading);
                    }
                });

                //send request3
                out.println(REQUEST);

                //send setupString
                out.println(setupString);

                //send punchLineString
                out.println(punchLineString);

                //get a blank line from server else error window
                if (in.nextLine().equals("")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(PostActivity.this, MainActivity.class);
                            startActivityForResult(intent, 0);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            setContentView(R.layout.error);
                        }
                    });
                }
            }

            catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    public void run() {
                        setContentView(R.layout.error);
                    }
                });
            }

            finally {
                try {
                    out.close();
                } catch (Exception e) {
                }
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }



    }

}
