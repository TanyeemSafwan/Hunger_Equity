package com.example.myapplication.hunger_equity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.hunger_equity.R;
import com.example.myapplication.hunger_equity.model.DFeedModel;

import java.util.ArrayList;

public class DonorHomeAdapter extends RecyclerView.Adapter<DonorHomeView>{
    Context context;
    ArrayList<DFeedModel> list;

    public DonorHomeAdapter(Context context, ArrayList<DFeedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DonorHomeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.donor_home_item,parent,false);
        return new DonorHomeView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorHomeView holder, int position) {
        DFeedModel model=list.get(position);
        holder.title.setText(model.getFeedTitle());
        holder.charity.setText(model.getStatus());
        holder.date.setText(model.getFeedDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class DonorHomeView extends RecyclerView.ViewHolder{
    TextView title,charity,date;
    public DonorHomeView(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.donor_home_title);
        charity= itemView.findViewById(R.id.donor_home_charity);
        date=itemView.findViewById(R.id.donor_home_date);
    }
}
