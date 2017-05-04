package com.example.seunghyunlee.hw8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = (Button) findViewById(R.id.enter);

        final EditText cardNumber = (EditText) findViewById(R.id.cardNumber);
        final EditText CVC = (EditText) findViewById(R.id.CVC);
        final EditText name = (EditText) findViewById(R.id.Name);
        final EditText street = (EditText) findViewById(R.id.Street);
        final EditText city = (EditText) findViewById(R.id.City);
        final EditText state = (EditText) findViewById(R.id.State);
        final EditText zip = (EditText) findViewById(R.id.Zip);
        final EditText name2 = (EditText) findViewById(R.id.Name2);
        final EditText street2 = (EditText) findViewById(R.id.Street2);
        final EditText city2 = (EditText) findViewById(R.id.City2);
        final EditText state2 = (EditText) findViewById(R.id.State2);
        final EditText zip2 = (EditText) findViewById(R.id.Zip2);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);

        final RadioButton visa = (RadioButton) findViewById(R.id.visa);
        final RadioButton mastercard = (RadioButton) findViewById(R.id.mastercard);
        final RadioButton amex = (RadioButton) findViewById(R.id.amex);
        final RadioButton discover = (RadioButton) findViewById(R.id.discover);

        final TextView message = (TextView) findViewById(R.id.message);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    name2.setText(name.getText());
                    street2.setText(street.getText());
                    city2.setText(city.getText());
                    state2.setText(state.getText());
                    zip2.setText(zip.getText());
                }else{
                    name2.setText("");
                    street2.setText("");
                    city2.setText("");
                    state2.setText("");
                    zip2.setText("");
                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!visa.isChecked() || !mastercard.isChecked() || !amex.isChecked() || !discover.isChecked()) {
                    message.setText("Select a card");
                    message.requestFocus();
                }

                // 16digit
                if (cardNumber.length() != 16 || !TextUtils.isDigitsOnly(cardNumber.getText())) {
                    message.setText("Please enter valid card number");
                    message.requestFocus();
                    return;
                }

                if (CVC.length() != 3 || !TextUtils.isDigitsOnly(CVC.getText())) {
                    message.setText("Wrong CVC number");
                    message.requestFocus();
                    return;
                }
                //Billing
                if (street.length() == 0) {
                    message.setText("Please enter street address");
                    message.requestFocus();
                    return;
                }

                if (city.length() == 0) {
                    message.setText("Please enter city");
                    message.requestFocus();
                    return;
                }

                if (state.length() != 2 ) {
                    message.setText("Please enter state(2characters)");
                    message.requestFocus();
                    return;
                }

                if (zip.length() != 5 || !TextUtils.isDigitsOnly(zip.getText())) {
                    message.setText("Please enter valid zip code");
                    message.requestFocus();
                    return;
                }

                if (name.length() == 0) {
                    message.setText("Please enter name");
                    message.requestFocus();
                    return;
                }
                //Shiping
                if (street2.length() == 0) {
                    message.setText("Please enter street address");
                    message.requestFocus();
                    return;
                }

                if (city2.length() == 0) {
                    message.setText("Please enter city");
                    message.requestFocus();
                    return;
                }

                if (state2.length() != 2) {
                    message.setText("Please enter state(2characters)");
                    message.requestFocus();
                    return;
                }

                if (zip2.length() != 5 || !TextUtils.isDigitsOnly(zip2.getText())) {
                    message.setText("Please enter valid zip code");
                    message.requestFocus();
                    return;
                }

                if (name2.length() == 0) {
                    message.setText("Please enter name");
                    message.requestFocus();
                    return;
                }




                message.setText("Thank you");
                message.requestFocus();
            }

        });

    }
}
