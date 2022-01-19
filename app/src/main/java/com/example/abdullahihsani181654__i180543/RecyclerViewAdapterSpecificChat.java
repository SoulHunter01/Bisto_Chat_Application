package com.example.abdullahihsani181654__i180543;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerViewAdapterSpecificChat extends RecyclerView.Adapter<RecyclerViewAdapterSpecificChat.MyViewHolder> {

    List<SpecificChatClass> ls;
    Context c;


    public RecyclerViewAdapterSpecificChat(List<SpecificChatClass> ls, Context c) {
        this.ls = ls;
        this.c = c;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(c).inflate(R.layout.specificchatrow,parent,false);
        return new MyViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.message.setText(ls.get(position).getMessage());
        holder.time_view.setText(ls.get(position).getTime());
        holder.message.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               holder.message.setText("Message Deleted");
               holder.time_view.setText("");
               return false;
           }
       });

        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.message.getEditableText();
            }
        });



    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class MyViewHolder<V> extends RecyclerView.ViewHolder{
        TextView message;
        TextView time_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.rounded_message);
            time_view=itemView.findViewById(R.id.time_view);
        }

    }




}

