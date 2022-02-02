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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CharityHomeAdapter2 extends RecyclerView.Adapter<CharityHomeView2> {
    Context context;
    ArrayList<DFeedModel> list;

    public CharityHomeAdapter2(Context context, ArrayList<DFeedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CharityHomeView2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.donor_home_item2,parent,false);
        return new CharityHomeView2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityHomeView2 holder, int position) {
        DFeedModel model=list.get(position);
        holder.title.setText(model.getFeedTitle());
        holder.donor.setText(model.getFeedUsername());
        holder.date.setText(model.getFeedDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class CharityHomeView2 extends RecyclerView.ViewHolder{
    TextView title,donor,date;
    public CharityHomeView2(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.charity_home_title_2);
        donor=itemView.findViewById(R.id.charity_home_donor_2);
        date=itemView.findViewById(R.id.charity_home_date_2);
    }
}
