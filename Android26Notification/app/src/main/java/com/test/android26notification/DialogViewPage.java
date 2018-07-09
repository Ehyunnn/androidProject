package com.test.android26notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by yangssem on 2016-02-15.
 */
public class DialogViewPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        Log.i("testLog",title+":"+content);

        TextView txt_message = (TextView)findViewById(R.id.txt_message);
        txt_message.setText(title+":"+content);

    }

    public void gotoMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
