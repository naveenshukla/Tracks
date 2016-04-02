package com.app.hackathon.tracks;

/**
 * Created by rudraksha on 2/4/16.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naveen on 1/11/15.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {
    public Activity activity;
    public String json_string = null;
    Context ctx;
    public boolean isConnectedToServer(String url, int timeout) {
        try{
            URL myUrl = new URL("http://www.google.co.uk");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }
    BackgroundTask(Context ctx,Activity activity){
        this.activity = activity;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("hello","do In background");
        //String reg_url = "http://oneconnect.blogjist.com/register.php";
        String search_url = "http://www.rajatnaveen.hoxty.com/searchandquery.php";
        //String login_url="http://oneconnect.blogjist.com/login.php";
        //String update_url="http://oneconnect.blogjist.com/update.php";
        String method = params[0];

        if (method.equals("search")){
            Log.d("hello","method search");
            String name = params[1];
            String phone = params[2];
            String src = params[3];
            String dest = params[4];
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d("hello", "connection");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                        URLEncoder.encode("src","UTF-8") + "=" + URLEncoder.encode(src,"UTF-8") + "&" +
                        URLEncoder.encode("dest","UTF-8") + "=" + URLEncoder.encode(dest,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String urlString="";
                while ((json_string = bufferedReader.readLine())!=null){
                    urlString += json_string;
                }
                /*bufferedReader.close();
                inputStream.close();*/
                /*httpURLConnection.disconnect();*/
                json_string = urlString;
                Log.d("hello",json_string);
                //showonscreen(urlString);
                return urlString;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*Log.d("appcreate","doInBackground for search is working properly");*/
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> phone = new ArrayList<String>();
        ArrayList<String> src = new ArrayList<String>();
        ArrayList<String> dest = new ArrayList<String>();
        //mylist.add(mystring); //this adds an element to the list.
        if(result.equals("Successs ...")){

            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();

        }
        else if(result.equals("{\"result\":[]}")){
            TextView textViewname = (TextView) this.activity.findViewById(R.id.textView);
            textViewname.setText("Looks like this user hasn't" +
                    " created contact on ContactHub");
        }
        else {
            Log.d("hello","this is cool");
            //TextView textViewname = (TextView) this.activity.findViewById(R.id.textView);
            //TextView textViewcontact = (TextView) this.activity.findViewById(R.id.viewphonenumber);
            //TextView textViewaddress = (TextView) this.activity.findViewById(R.id.viewaddress);
            Log.d("hello",result);
            try {
                JSONObject mainObject = new JSONObject(result);
                JSONArray cast = mainObject.getJSONArray("Orders");
                for (int i = 0; i < cast.length(); i++) {
                    JSONArray user = cast.getJSONArray(i);
                    name.add((String) user.get(0));
                    phone.add((String)user.get(1));
                    src.add((String)user.get(2));
                    dest.add((String) user.get(3));
                }
            } catch (JSONException e) {
                Toast.makeText(ctx,"no one is going ",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Log.d("hello", String.valueOf(dest.size()));
        }
    }
    public String returnJSON(String result){
        json_string = result;
        return json_string;
    }
}
