package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.hunger_equity.adapter.CharityNoficationAdapter;
import com.example.myapplication.hunger_equity.adapter.DonorNotificationAdapter;
import com.example.myapplication.hunger_equity.model.CharityNotificaitionModel;
import com.example.myapplication.hunger_equity.model.DonorNotificationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonorNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_notification);
        RecyclerView recyclerView;
        DatabaseReference databaseReference;
        DonorNotificationAdapter adapter;
        ArrayList<DonorNotificationModel> list;
        SharedPreferences sp;
        sp=getApplicationContext().getSharedPreferences("DonorInfo", Context.MODE_PRIVATE);
        String UserName=sp.getString("name","");

        recyclerView=findViewById(R.id.donor_notification_list);
        databaseReference= FirebaseDatabase.getInstance().getReference("DonorNotification");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(DonorNotification.this);
        recyclerView.setLayoutManager(layoutmanager);

        list=new ArrayList<>();
        adapter=new DonorNotificationAdapter(DonorNotification.this,list);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DonorNotificationModel model=dataSnapshot.getValue(DonorNotificationModel.class);
                    if(model.getUserName().equals(UserName))
                    {
                        list.add(model);
                    }

                }
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}