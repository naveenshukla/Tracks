package com.app.hackathon.tracks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Satyam Poddar on 02-Apr-16.
 */
public class SecondActivity extends AppCompatActivity {
    private TextView dname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dname = (TextView)findViewById(R.id.displayname);
       /* MyDatabase retrive = new MyDatabase(this);
        Object[] obj = new Object[3];
        obj = retrive.getData(0);
        String s = (String)obj[0];
        dname.setText(s);
        retrive.close();*/
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_APPEND);
        dname.setText(sharedPreferences.getString("Name",null));
        Log.d("hello", sharedPreferences.getString("Name", null));

    }
}
