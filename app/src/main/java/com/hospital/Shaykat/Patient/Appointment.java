package com.hospital.Shaykat.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hospital.Shaykat.R;

/**
 * Created by Shaykat on 28-Sep-2021.
 */
public class Appointment extends AppCompatActivity {

    String username,password,user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);

        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");
    }

    public void appointment(View view){

        Intent i;
        Bundle b = new Bundle();
        b.putString("username",username);
        b.putString("password",password);
        b.putString("user_type",user_type);

        switch (view.getId())
        {
            case R.id.b_new:
                i = new Intent(Appointment.this, New_Appointment.class);
                break;

            default:
                i = new Intent(Appointment.this, Confirmed_Appointment.class);
                break;
        }
        i.putExtras(b);
        startActivity(i);
    }

}
