package com.example.myapplication.hunger_equity.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.hunger_equity.R;
import com.example.myapplication.hunger_equity.model.DonorNotificationModel;

import java.util.ArrayList;

public class DonorNotificationAdapter extends RecyclerView.Adapter<DonorNotificationView>{
    Context context;
    ArrayList<DonorNotificationModel> list;


    public DonorNotificationAdapter(Context context, ArrayList<DonorNotificationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DonorNotificationView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.donor_notification_item,parent,false);
        return new DonorNotificationView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorNotificationView holder, int position) {
        DonorNotificationModel model= list.get(position);
        holder.UserName.setText(model.getSender());
        holder.Title.setText(model.getTitle());
        holder.Information.setText(model.getInformation());
        holder.Date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class DonorNotificationView extends RecyclerView.ViewHolder{
    TextView UserName,Information,Date,Title;
    public DonorNotificationView(@NonNull View itemView) {
        super(itemView);
        UserName=itemView.findViewById(R.id.notification_username_1);
        Information=itemView.findViewById(R.id.notification_information_1);
        Date=itemView.findViewById(R.id.notification_date_1);
        Title=itemView.findViewById(R.id.notification_title_1);

    }
}
