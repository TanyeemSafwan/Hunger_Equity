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

import java.util.ArrayList;

public class DonorHomeAdapter2 extends  RecyclerView.Adapter<DonorHomeView2> {
    Context context;
    ArrayList<CFeedModel> list;

    public DonorHomeAdapter2(Context context, ArrayList<CFeedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DonorHomeView2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.donor_home_item2,parent,false);
        return new DonorHomeView2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorHomeView2 holder, int position) {
        CFeedModel model=list.get(position);
        holder.title.setText(model.getFeedTitle());
        holder.charity.setText(model.getFeedUsername());
        holder.date.setText(model.getFeedDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class DonorHomeView2 extends RecyclerView.ViewHolder{
    TextView title,charity,date;
    public DonorHomeView2(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.donor_home_title_2);
        charity=itemView.findViewById(R.id.donor_home_charity_2);
        date=itemView.findViewById(R.id.donor_home_date_2);
    }
}
