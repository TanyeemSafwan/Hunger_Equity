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

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class CharityHomeAdapter extends RecyclerView.Adapter<CharityHomeView>{
    Context context;
    ArrayList<CFeedModel> list;

    public CharityHomeAdapter(Context context, ArrayList<CFeedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CharityHomeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.charity_home_item,parent,false);
        return new CharityHomeView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityHomeView holder, int position) {
        CFeedModel model=list.get(position);
        holder.title.setText(model.getFeedTitle());
        holder.donor.setText(model.getStatus());
        holder.date.setText(model.getFeedDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class CharityHomeView extends RecyclerView.ViewHolder{
    TextView title,donor,date;
    public CharityHomeView(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.charity_home_title);
        donor=itemView.findViewById(R.id.charity_home_donor);
        date=itemView.findViewById(R.id.charity_home_date);
    }
}