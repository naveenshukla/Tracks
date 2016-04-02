package com.app.hackathon.tracks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText tname,tnumber,tlocation;
    public String s1,s2,s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("hello", "started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button blogin = (Button)findViewById(R.id.blogin);
        tname=(EditText)findViewById(R.id.name);
        tnumber=(EditText)findViewById(R.id.number);
        tlocation=(EditText) findViewById(R.id.place);
        blogin.setOnClickListener(this);
        /*blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = tname.getText().toString();
                s2 = tnumber.getText().toString();
                s3 = tlocation.getText().toString();
            }
        });*/
        //Log.d("hello",s4)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.blogin:
              //  Log.d("hello","thiscase");
                /*s1 = tname.getText().toString();
                s2 = tnumber.getText().toString();
                s3 = tlocation.getText().toString();
                */SharedPreferences sharedPreferences = getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
                /*SharedPreferences.Editor e = sharedPreferences.edit();
                //Log.d("hello", s1);
                e.putString("Name", s1);
                e.putString("PhoneNumber", s2);
                e.putString("Address", s3);
                e.commit();*/
                String some = sharedPreferences.getString("Name", null);
                if(some==null){
                    Log.d("hello","ni bnayi id");
                }
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
                break;
        }
    }
}
