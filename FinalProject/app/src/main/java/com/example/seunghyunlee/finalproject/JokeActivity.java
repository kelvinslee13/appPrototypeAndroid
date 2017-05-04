package com.example.seunghyunlee.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class JokeActivity extends AppCompatActivity {

    private Client client = new Client();
    private String intentCode;

    public static final String SERVER = "10.32.95.80";
    public static final int PORT = 12345;
    public static final int REQUEST = 2;

    public static final String extraString = "send";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent = getIntent();
        intentCode = intent.getStringExtra(extraString);
        new Thread(client).start();
    }

    public void show(String setup,String punchLine) {
        setContentView(R.layout.activity_joke);
        LinearLayout info = (LinearLayout) findViewById(R.id.info);

        TextView text = new TextView(this);
        TextView text2 = new TextView(this);
        text.setPadding(12, 12, 12, 12);
        text2.setPadding(12, 12, 12, 12);
        text.setTextSize(18);
        text2.setTextSize(18);
        text.setText(setup);
        text2.setText(punchLine);
        info.addView(text2, 0);
        info.addView(text,0);
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
                out.println(REQUEST);
                out.println(Integer.parseInt(intentCode));

                runOnUiThread(new Runnable() {
                    public void run() {
                        setContentView(R.layout.loading);
                    }
                });

                final String setup= in.nextLine();
                final String punchLine= in.nextLine();

                runOnUiThread(new Runnable() {
                    public void run() {
                        show(setup,punchLine);
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
    }

}
