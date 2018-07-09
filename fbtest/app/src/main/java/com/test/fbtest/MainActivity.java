package com.test.fbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();


    }

    public void onClick(View v){
        Log.i("testLog","onclick"+ ++count);
        myRef = database.getReference("message");
        myRef.setValue("Hello, World!"+count);


        MemberVO vo = new MemberVO("kim"+count,"hi1234"+count);
        myRef.child("test").child("member").push().setValue(vo);
    }
}
