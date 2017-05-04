package com.example.setup.factors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // View references
    private int cols;
    private EditText[] numbers;
    private Button[] buttons;
    private TextView[] factors;
    private Factorer[] threads;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get views out of the layout
        LinearLayout columns = (LinearLayout) findViewById(R.id.columns);
        cols = columns.getChildCount();
        numbers = new EditText[cols];
        buttons = new Button[cols];
        factors = new TextView[cols];
        threads = new Factorer[cols];

        for (int i=0; i < cols; i++) {
            LinearLayout column = (LinearLayout) columns.getChildAt(i);
            numbers[i] = (EditText) column.getChildAt(0);
            buttons[i] = (Button) column.getChildAt(1);
            factors[i] = (TextView) ((ScrollView)column.getChildAt(2)).getChildAt(0);
        }

        // Tapping a button activates factoring in that column
        for (int i=0; i < cols; i++) {
            final int col = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    factors[col].setText("");
                    factor(col);
                }
            });
        }
    }

    // Begin factoring in column i of the layout
    private void factor(int i) {
        try {
            long n = Long.parseLong(numbers[i].getText().toString());
            threads[i] = new Factorer(i, n);
            new Thread(threads[i]).start();
        }

        // Pop-up notification for failed parsing
        catch (NumberFormatException e) {
            Toast error = Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT);
            error.setGravity(Gravity.CENTER, 0, 0);
            error.show();
        }
    }

    // Temporarily preserve the views if the app is destroyed unexpectedly
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for (int i=0; i < cols; i++) {
            savedInstanceState.putString("numbers" + i, numbers[i].getText().toString());
            savedInstanceState.putString("factors" + i, factors[i].getText().toString());

            if(threads[i] != null && threads[i].f < threads[i].n);
        }
    }

    // Restore the views if we're coming back from unexpected destruction
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i=0; i < cols; i++) {
            numbers[i].setText(savedInstanceState.getString("numbers" + i));
            factors[i].setText(savedInstanceState.getString("factors" + i));
        }
    }


    // Make sure threads don't linger if the activity dies
    protected void onDestroy() {
        super.onDestroy();
        for (int i=0; i < cols; i++) {
            if (threads[i] != null)
                threads[i].cancelled = true;
        }
    }

    private class Factorer implements Runnable {

        private int i; // Column number
        private long n; // Number to factor
        private boolean cancelled;

        public Factorer(int i, long n) {
            this.i = i;
            this.n = n;
        }

        public void run() {

            private long f;

            // Ask the main thread to disable the column
            runOnUiThread(new Runnable() {
                public void run() {
                    numbers[i].setEnabled(false);
                    buttons[i].setEnabled(false);
                }
            });

            // Do the factoring
            for (long f = 2; f < n; f++) {
                if (cancelled)
                    break; // We were asked to stop

                if (n % f == 0) {
                    final long factor = f;

                    // Ask the main thread to add a factor to the view
                    runOnUiThread(new Runnable() {
                        public void run() {
                            factors[i].append(factor + "\n");
                        }
                    });
                }
            }

            // Ask the main thread to enable the column
            runOnUiThread(new Runnable() {
                public void run() {
                    numbers[i].setEnabled(true);
                    buttons[i].setEnabled(true);
                }
            });
        }
    }
}