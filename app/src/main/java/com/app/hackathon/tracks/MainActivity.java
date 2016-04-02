package com.app.hackathon.tracks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import DBManager.MyDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tname,tnumber,tlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button blogin = (Button)findViewById(R.id.blogin);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        blogin.setOnClickListener(this);
        tname = (TextView)findViewById(R.id.name);
        tnumber = (TextView)findViewById(R.id.number);
        tlocation = (TextView)findViewById(R.id.place);
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
                String s1,s2,s3;
                s1 = tname.getText().toString();
                s2 = tname.getText().toString();
                s3 = tname.getText().toString();
                MyDatabase entry = new MyDatabase(this);
                entry.openandwrite();
                entry.createEntry(s1, s2, s3);
                entry.close();
                Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
                break;
        }
    }
}
