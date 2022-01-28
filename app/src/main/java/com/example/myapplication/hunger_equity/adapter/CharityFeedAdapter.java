package com.example.myapplication.hunger_equity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.hunger_equity.CharityFeed;
import com.example.myapplication.hunger_equity.CharityRequestForm;
import com.example.myapplication.hunger_equity.R;
import com.example.myapplication.hunger_equity.model.DFeedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CharityFeedAdapter extends RecyclerView.Adapter<CharityFeedView> {
    public static String status="Deactive";
    Context context;
    ArrayList<DFeedModel> list;

    public CharityFeedAdapter(Context context, ArrayList<DFeedModel> list)
    {
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public CharityFeedView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.charity_feed_item,parent,false);
        return new CharityFeedView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityFeedView holder, int position) {
        DFeedModel model=list.get(position);
        holder.name.setText(model.getFeedUsername());
        holder.title.setText(model.getFeedTitle());
        holder.quantity.setText(model.getFeedQuantity());
        holder.place.setText(model.getFeedPlace());
        holder.time.setText(model.getFeedTime());
        holder.date.setText(model.getFeedDate());
        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Donor_feed");
                mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i=0;
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(i==position)
                            {
                                String key=dataSnapshot.getKey();
                                mFirebaseDatabase.child(key).child("status").setValue("Deactive");
                                ((CharityFeed)context).announce();
                                ((CharityFeed)context).finish();

                            }
                            i++;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class CharityFeedView extends RecyclerView.ViewHolder{
    TextView name,quantity,title,place,time,date,request;
    public CharityFeedView(@NonNull View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.charity_feed_name);
        quantity=itemView.findViewById(R.id.charity_feed_quantity);
        title=itemView.findViewById(R.id.charity_feed_title);
        place=itemView.findViewById(R.id.charity_feed_place);
        time=itemView.findViewById(R.id.charity_feed_time);
        date=itemView.findViewById(R.id.charity_feed_date);
        request=itemView.findViewById(R.id.charity_item_request);

    }
}
