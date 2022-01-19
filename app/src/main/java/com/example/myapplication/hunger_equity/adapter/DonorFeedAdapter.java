package com.example.myapplication.hunger_equity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.hunger_equity.R;
import com.example.myapplication.hunger_equity.model.CFeedModel;
import com.example.myapplication.hunger_equity.model.DFeedModel;

import java.util.ArrayList;

public class DonorFeedAdapter  extends RecyclerView.Adapter<DonorFeedView>{

    Context context;
    ArrayList<CFeedModel> list;

    public DonorFeedAdapter(Context context, ArrayList<CFeedModel> list)
    {
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


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class DonorFeedView extends RecyclerView.ViewHolder{
    TextView name,quantity,title,address,date,description;
    public DonorFeedView(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.donor_feed_name);
        title=itemView.findViewById(R.id.donor_feed_title);
        quantity=itemView.findViewById(R.id.donor_feed_quantity);
        address=itemView.findViewById(R.id.donor_feed_address);
        date=itemView.findViewById(R.id.donor_feed_date);
        description=itemView.findViewById(R.id.donor_feed_description);


    }
}