package com.hospital.Shaykat.Patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hospital.Shaykat.R;

/**
 * Created by Shaykat on 28-Sep-2021.
 */
public class Wait_Appointment extends AppCompatActivity {

    String username,password,user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_appointment);

        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");
    }
}
