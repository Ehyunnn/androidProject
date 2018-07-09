package com.test.android09spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> datas = new ArrayList<>();
        for(int i=0;i<5;i++){
            datas.add("KT"+i);
        }

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                datas));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("testLog", datas.get(position));
                Toast.makeText(getApplicationContext(),
                        datas.get(position),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CheckBox cb01 = findViewById(R.id.checkBox);
        CheckBox cb02 = findViewById(R.id.checkBox2);
        CheckBox cb03 = findViewById(R.id.checkBox3);

        cb01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.i("testLog","isChecked:"+isChecked);
            }
        });

        RadioGroup rg = findViewById(R.id.rg01);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group,
                                         int checkedId) {
                Log.i("testLog","checkedId:"+checkedId);

                Toast.makeText(getApplicationContext(),
//                        "checkedId:"+checkedId,
                        ((RadioButton)findViewById(checkedId)).getText(),
                        Toast.LENGTH_LONG).show();
            }
        });

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar,
                                        float rating,
                                        boolean fromUser) {
                Toast.makeText(getApplicationContext(),
                        "rating:"+rating,
                        Toast.LENGTH_LONG).show();
            }
        });

    }//end onCreate()
}//end class
