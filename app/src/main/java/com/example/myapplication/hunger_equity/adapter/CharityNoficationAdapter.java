package com.example.myapplication.hunger_equity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.hunger_equity.R;
import com.example.myapplication.hunger_equity.model.CharityNotificaitionModel;

import java.util.ArrayList;

public class CharityNoficationAdapter extends RecyclerView.Adapter<CharityNotificationView>{
    Context context;
    ArrayList<CharityNotificaitionModel> list;

    public CharityNoficationAdapter(Context context, ArrayList<CharityNotificaitionModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CharityNotificationView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.charity_notification_item,parent,false);
        return new CharityNotificationView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityNotificationView holder, int position) {
           CharityNotificaitionModel model=list.get(position);
           holder.UserName.setText(model.getSender());
           holder.Information.setText(model.getInformation());
           holder.Date.setText(model.getDate());
           holder.Title.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class CharityNotificationView extends RecyclerView.ViewHolder{
    TextView UserName,Information,Date,Title;

    public CharityNotificationView(@NonNull View itemView) {
        super(itemView);
        Date=itemView.findViewById(R.id.notification_date);
        UserName=itemView.findViewById(R.id.notification_username);
        Information=itemView.findViewById(R.id.notification_information);
        Title=itemView.findViewById(R.id.notification_title);

    }
}