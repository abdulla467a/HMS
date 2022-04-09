package com.hospital.Shaykat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hospital.Shaykat.Desktop_Admin.Desktop_Admin;
import com.hospital.Shaykat.Doctor.Doctor;
import com.hospital.Shaykat.Patient.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaykat on 27-Sep-2019.
 */
public class Register extends AppCompatActivity {

    EditText fname, lname, age, dd, yy, city, pincode, mobno, uname, password;
    String fnames, lnames, ages, genders, bgroups, dobs, dds, yys, mms, citys, pincodes, mobnos, unames, passwords, utypes;
    Button register;
    Spinner usertype, mm, gender, bgroup;

    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //DEFINING ALL VIEWS
        fname = (EditText) findViewById(R.id.etfname);
        lname = (EditText) findViewById(R.id.etlname);
        age = (EditText) findViewById(R.id.etage);
        dd = (EditText) findViewById(R.id.etdd);
        yy = (EditText) findViewById(R.id.etyy);
        city = (EditText) findViewById(R.id.etcity);
        pincode = (EditText) findViewById(R.id.etpin);
        mobno = (EditText) findViewById(R.id.etmobile);
        uname = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.etpassword);
        register = (Button) findViewById(R.id.bregister);
        usertype = (Spinner) findViewById(R.id.spinnerusertype);
        mm = (Spinner) findViewById(R.id.spinnermonth);
        gender = (Spinner) findViewById(R.id.spinnergender);
        bgroup = (Spinner) findViewById(R.id.spinnerbgroup);
        dbh = new DatabaseHelper(this);

        //SET UP THE SPINNER DROOPDOWN
        List<String> category = new ArrayList<>();
        category.add("Patient");
        category.add("Doctor");
        category.add("Admin");

        List<String> genderc = new ArrayList<>();
        genderc.add("Male");
        genderc.add("Female");

        List<String> bgroupc = new ArrayList<>();
        bgroupc.add("A+");
        bgroupc.add("A-");
        bgroupc.add("B+");
        bgroupc.add("B-");
        bgroupc.add("O+");
        bgroupc.add("O-");
        bgroupc.add("AB+");
        bgroupc.add("AB-");

        List<String> months = new ArrayList<>();
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");

        ArrayAdapter<String> acat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, category);
        ArrayAdapter<String> amonth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        ArrayAdapter<String> abgroup = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bgroupc);
        ArrayAdapter<String> agender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderc);


        acat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        abgroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(acat);
        mm.setAdapter(amonth);
        gender.setAdapter(abgroup);
        bgroup.setAdapter(agender);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnames = lname.getText().toString();
                fnames = fname.getText().toString();
                ages = age.getText().toString();
                dds = dd.getText().toString();
                yys = yy.getText().toString();
                citys = city.getText().toString();
                pincodes = pincode.getText().toString();
                unames = uname.getText().toString();
                passwords = password.getText().toString();
                mobnos = mobno.getText().toString();
                utypes = usertype.getSelectedItem().toString();
                mms = mm.getSelectedItem().toString();
                genders = gender.getSelectedItem().toString();
                bgroups = bgroup.getSelectedItem().toString();

                if (fnames.equals("") || lnames.equals("") || ages.equals("") || dds.equals("") ||
                        yys.equals("") || citys.equals("") || pincodes.equals("") || unames.equals("") ||
                        passwords.equals("") || mobnos.equals("")) {
                    Message.message(Register.this, "Please Fill in all your Details");
                } else {

                    //CHECK WHETHER THE ENTRY ALREADY EXISTS
                    Cursor y = dbh.checkduplicates_in_user_credentials(unames, passwords, getResources().getString(R.string.user_credentials));
                    if (y.moveToFirst()) {
                        Message.message(Register.this, "User Already Exists");
                        Message.message(Register.this, "Login With Your Username and Password");
                        finish();
                    } else {
                        //SETUP DATABASE QUERY
                        if (dds.length() == 1)
                            dds = "0" + dds;
                        dobs = dds + " " + mms + " " + yys;

                        boolean b = dbh.insert_user_credentials(fnames, lnames, ages, dobs, citys, pincodes, unames, passwords, mobnos, utypes, genders, bgroups);
                        if (b) {
                            Intent i;
                            Bundle bb = new Bundle();
                            bb.putString("username", unames);
                            bb.putString("password", passwords);
                            bb.putString("user_type", utypes);

                            if (utypes.equals("Patient")) {
                                i = new Intent(Register.this, Patient.class);
                            } else if (utypes.equals("Doctor")) {
                                i = new Intent(Register.this, Doctor.class);
                            } else {
                                i = new Intent(Register.this, Desktop_Admin.class);
                            }

                            i.putExtras(bb);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            }
        });
    }
}
