package com.test.android10network;

import android.net.UrlQuerySanitizer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Handler mHandler = new Handler();
    String data = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(){
            @Override
            public void run() {
                testNetWork();
            }
        }.start();
    }
    void testNetWork(){
        //http://192.168.0.163/IOTstudy177/DOC/sql_testvo.txt
//        String path = "http://192.168.0.163/IOTstudy177/DOC/sql_testvo.txt";
        String path = "http://192.168.0.163/IOTstudy177/DOC/json.txt";
        HttpURLConnection conn = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(path);
            Log.i("testLog",url.toString());
            conn = (HttpURLConnection)url.openConnection();
            Log.i("testLog","conn.getResponseCode():"+conn.getResponseCode());
            Log.i("testLog","conn.getContentType():"+conn.getContentType());
            Log.i("testLog","conn.getContentLength():"+conn.getContentLength());
            Log.i("testLog","conn.getResponseMessage():"+conn.getResponseMessage());

            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String str = "";
            final StringBuilder sb = new StringBuilder();
            while((str = br.readLine()) != null){
                sb.append(str);
            }
            Log.i("testLog",sb.toString());

            JSONArray array = new JSONArray(sb.toString());

            for(int i=0;i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                data += obj.getString("name");
                data += obj.getInt("age");
                data += "\n";
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    TextView tv = findViewById(R.id.tv);
                    tv.setText(data);

                    Toast.makeText(getApplicationContext(),
                            data,
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MalformedURLException e) {
            Log.e("testLog",e.getMessage());
        } catch (IOException e) {
            Log.e("testLog",e.getMessage());
        } catch (JSONException e) {
            Log.e("testLog",e.getMessage());
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if(isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                }
            }
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if(conn!=null){
                conn.disconnect();
            }
        }
    }
}
