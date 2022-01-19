package com.example.abdullahihsani181654__i180543;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<ImageClass> ls;
    Context c;


    public RecyclerViewAdapter(List<ImageClass> ls, Context c) {
        this.ls = ls;
        this.c = c;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.image.setImageDrawable(ls.get(position).getImage());
        holder.name.setText(ls.get(position).getName());
        holder.message.setText(ls.get(position).getMessage());
        holder.status.setImageDrawable(ls.get(position).getStatus());
        final ImageClass temp=ls.get(position);
        holder.image.setOnClickListener(v -> {
            Intent intent=new Intent(c, SpecificChat.class);
            intent.putExtra("NAME",temp.getName());
            intent.putExtra("STATUS",temp.getStatus().toString());
            intent.putExtra("IMAGE",temp.getImage().toString());
            c.startActivity(intent);

        });




    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public void filterList(ArrayList<ImageClass> filteredList) {
        ls = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder<V> extends RecyclerView.ViewHolder{
        ImageView image;
        ImageView status;
        TextView name;
        TextView message;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.myimageview);
            name=itemView.findViewById(R.id.name);
            message=itemView.findViewById(R.id.message);
            status=itemView.findViewById(R.id.status);
        }

    }




}

