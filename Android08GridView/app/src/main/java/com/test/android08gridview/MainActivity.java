package com.test.android08gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new ArrayAdapter<String>(this,
                R.layout.tv_item,
                new String[]{"aaa","bbb","ccc"}));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Log.i("testLog",String.valueOf(position));
//                Toast.makeText(getApplicationContext(),
//                        parent.getItemAtPosition(position).toString(),
////                        ((TextView)view).getText(),
//                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
