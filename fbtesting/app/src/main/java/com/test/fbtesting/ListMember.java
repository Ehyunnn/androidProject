package com.test.fbtesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListMember extends AppCompatActivity {
    ListView lv;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_member);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        final ArrayList<String> list = new ArrayList<String>();
        list.add("aaaa");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("testLog","onChildAdded>>"+dataSnapshot.getKey());
                for(DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                    TestVO vo = (TestVO) childSnapShot.getValue(TestVO.class);
                    Log.i("testLog","onChildAdded>>"+vo.id);

                    list.add(vo.id+":"+vo.pw);

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list));

    }
}
