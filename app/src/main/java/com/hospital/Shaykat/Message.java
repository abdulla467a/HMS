package com.hospital.Shaykat;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Shaykat on 28-Sep-2021.
 */
public class Message {
    public static void message(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
