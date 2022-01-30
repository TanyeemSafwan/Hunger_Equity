package com.example.myapplication.hunger_equity.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.hunger_equity.CharityFeed;
import com.example.myapplication.hunger_equity.DonationFeed;
import com.example.myapplication.hunger_equity.R;
import com.example.myapplication.hunger_equity.model.CFeedModel;
import com.example.myapplication.hunger_equity.model.CharityNotificaitionModel;
import com.example.myapplication.hunger_equity.model.DFeedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DonorFeedAdapter  extends RecyclerView.Adapter<DonorFeedView>{

    Context context;
    ArrayList<CFeedModel> list;
    String UserName;
    DatabaseReference mfirebaseDatabase2;


    public DonorFeedAdapter(Context context, ArrayList<CFeedModel> list, String UserName)
    {
        this.UserName=UserName;
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public DonorFeedView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.donor_feed_item,parent,false);
        return new DonorFeedView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorFeedView holder, int position) {
        CFeedModel model=list.get(position);
        holder.name.setText(model.getFeedUsername());
        holder.title.setText(model.getFeedTitle());
        holder.quantity.setText(model.getFeedQuantity());
        holder.address.setText(model.getFeedAddress());
        holder.date.setText(model.getFeedDate());
        holder.description.setText(model.getFeedDescription());
        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Charity_feed");
                mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i=0;
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(i==position)
                            {

                                String key=dataSnapshot.getKey();
                                mFirebaseDatabase.child(key).child("status").setValue(UserName);
                                mfirebaseDatabase2=FirebaseDatabase.getInstance().getReference("CharityNotification");
                                String key2=mfirebaseDatabase2.push().getKey();
                                Date date = Calendar.getInstance().getTime();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                                String strDate = dateFormat.format(date);
                                CharityNotificaitionModel not=new CharityNotificaitionModel(model.getFeedUsername(),UserName,"have accepted your request",
                                        strDate,model.getFeedTitle());
                                mfirebaseDatabase2.child(key2).setValue(not);

                                ((DonationFeed)context).announce();
                                ((DonationFeed)context).finish();

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
class DonorFeedView extends RecyclerView.ViewHolder{

    TextView name,quantity,title,address,date,description,request;
    public DonorFeedView(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.donor_feed_name);
        title=itemView.findViewById(R.id.donor_feed_title);
        quantity=itemView.findViewById(R.id.donor_feed_quantity);
        address=itemView.findViewById(R.id.donor_feed_address);
        date=itemView.findViewById(R.id.donor_feed_date);
        description=itemView.findViewById(R.id.donor_feed_description);
        request=itemView.findViewById(R.id.donor_item_request);

    }
}