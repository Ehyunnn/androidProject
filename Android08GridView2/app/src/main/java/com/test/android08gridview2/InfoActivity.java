package com.test.android08gridview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    ArrayList<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        for (int i=0;i<TestAdapter.imgs.length;i++){
            datas.add("kami>>"+i);
        }

        int position = getIntent().getIntExtra(
                "position",0);

        ImageView iv = findViewById(R.id.imageView);
        TextView tv = findViewById(R.id.textView);
        tv.setText(datas.get(position));

        iv.setImageResource(TestAdapter.imgs[position]);

        Toast.makeText(this,
                "position:"+position,
                Toast.LENGTH_SHORT).show();
    }
}
