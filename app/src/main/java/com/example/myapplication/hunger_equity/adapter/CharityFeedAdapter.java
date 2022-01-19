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

public class CharityFeedAdapter extends RecyclerView.Adapter<CharityFeedView> {

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class CharityFeedView extends RecyclerView.ViewHolder{
    TextView name,quantity,title,place,time,date;
    public CharityFeedView(@NonNull View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.charity_feed_name);
        quantity=itemView.findViewById(R.id.charity_feed_quantity);
        title=itemView.findViewById(R.id.charity_feed_title);
        place=itemView.findViewById(R.id.charity_feed_place);
        time=itemView.findViewById(R.id.charity_feed_time);
        date=itemView.findViewById(R.id.charity_feed_date);

    }
}
