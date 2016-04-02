package com.app.hackathon.tracks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Satyam Poddar on 02-Apr-16.
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    public String src, destination;
    EditText srctext,desttext;
    private TextView dname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("hello","activity 2 started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button result = (Button)findViewById(R.id.button);
        srctext = (EditText)findViewById(R.id.src);
        desttext = (EditText)findViewById(R.id.dest);
        result.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                destination = srctext.getText().toString();
               src = srctext.getText().toString();
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute("search","naveen","+9121314245","iiitallahabad","civillines");
                break;
        }
    }
}
