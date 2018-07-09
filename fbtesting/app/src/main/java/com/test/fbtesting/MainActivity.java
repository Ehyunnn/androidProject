package com.test.fbtesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText id;
    EditText pw;
    Button btn_send;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i("testLog","onChildChanged>>"+dataSnapshot.getKey());
                Log.i("testLog","onChildChanged>>"+dataSnapshot.getChildrenCount());

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.i("testLog",
                            "postSnapshot>>"+postSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        myRef.setValue("yangssem");
                TestVO vo = new TestVO(id.getText().toString(),pw.getText().toString());
                //myRef.setValue(vo);
                myRef.child("member").push().setValue(vo);
                startActivity(new Intent(getApplicationContext(),ListMember.class));

            }
        });
    }
}
