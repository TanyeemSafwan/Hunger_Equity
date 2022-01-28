package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.hunger_equity.adapter.CharityFeedAdapter;
import com.example.myapplication.hunger_equity.model.DFeedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CharityFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        DatabaseReference databaseReference;
        CharityFeedAdapter adapter;
        ArrayList<DFeedModel> list;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_feed);

        recyclerView=findViewById(R.id.charity_feed_list);
        databaseReference= FirebaseDatabase.getInstance().getReference("Donor_feed");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(CharityFeed.this);
        recyclerView.setLayoutManager(layoutmanager);

        ImageButton back=(ImageButton)findViewById(R.id.charity_feed_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityFeed.this.finish();
            }
        });

        list=new ArrayList<>();
        adapter=new CharityFeedAdapter(CharityFeed.this,list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   DFeedModel model=dataSnapshot.getValue(DFeedModel.class);
                   if(model.getStatus().equals("Active"))
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
    public void announce()
    {
        Toast.makeText(CharityFeed.this, "Request Complete!", Toast.LENGTH_SHORT).show();
    }

}