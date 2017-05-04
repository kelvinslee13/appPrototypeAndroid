package com.example.seunghyunlee.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    //source
    //http://stackoverflow.com/questions/17241745/android-how-do-ı-disable-reload-page-on-change-screen-orientation
    //class examples


    private Client client = new Client();
    public static final String SERVER = "10.32.95.80";
    public static final int PORT = 12345;
    public static final int REQUEST = 1;

    public static final String extraString = "send";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(client).start();
    }

    public void runPage(ArrayList<String> data) {
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        CharSequence welcome = "Welcome!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,welcome,duration);
        toast.show();


        LinearLayout jokes = (LinearLayout) findViewById(R.id.jokes);
        for (int i = 0; i < data.size(); i++) {
            TextView text = new TextView(this);
            text.setPadding(12, 12, 12, 12);
            text.setTextSize(18);
            text.setText(data.get(i));
            jokes.addView(text, 0);
            final String index = String.valueOf(i);

            text.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                    intent.putExtra(extraString, index);
                    MainActivity.this.startActivity(intent);
                }
            });
        }

        final Button post = (Button) findViewById(R.id.postMain);
        post.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        final Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                new Thread(client).start();
            }
        });

    }

    private class Client implements Runnable {

        private ArrayList<String> arrays;
        private Scanner in;
        private PrintWriter out;
        private Socket socket;

        public void run() {
            try {

                socket = new Socket(SERVER, PORT);
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                runOnUiThread(new Runnable() {
                    public void run() {
                        setContentView(R.layout.loading);
                    }
                });

                out.println(REQUEST);

                arrays = new ArrayList();
                while(in.hasNext()){
                    final String some = in.nextLine();
                    arrays.add(some);
                }

                runOnUiThread(new Runnable() {
                    public void run() {
                        runPage(arrays);
                    }
                });

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

        public void disconnect() {
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

    protected void onStop() {
        super.onStop();
        if (client != null) {
            client.disconnect();
        }
    }
}