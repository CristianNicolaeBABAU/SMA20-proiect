package com.upt.cti.vlad.udrescu.booking.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.upt.cti.vlad.udrescu.booking.R;
import com.upt.cti.vlad.udrescu.booking.dabasehelper.DBHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginAsGuestActivity extends AppCompatActivity {

    EditText name, phone_number;
    Button login_as_guest;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_login_layout);

        name = (EditText) findViewById(R.id.name);
        phone_number = (EditText) findViewById(R.id.phone_number);
        login_as_guest = (Button) findViewById(R.id.login_as_guest);

        dbHelper = new DBHelper(this);

        login_as_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_user = name.getText().toString();
                Integer phone_user = Integer.parseInt(phone_number.getText().toString());

                Boolean check_data_inserted = dbHelper.updateUserData(name_user,phone_user);
                //this boolean could be modified when user activate the account when on the phone number is sent a verification code
                if(check_data_inserted) {
                    Intent home_intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home_intent);
                    finish();
                }
                else
                    Toast.makeText(LoginAsGuestActivity.this, "We could not register. Try again.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
