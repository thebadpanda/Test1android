package com.example.arsenko.test1;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<RecyclerItem> listItems;
    private Context myContext;

    public MyAdapter(List<RecyclerItem> listItems, Context myContext){
        this.listItems = listItems;
        this.myContext = myContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        final RecyclerItem itemList = listItems.get(position);
        holder.titleTV.setText(itemList.getTitle());
        holder.descriptionTV.setText(itemList.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTV;
        public TextView descriptionTV;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            descriptionTV = (TextView) itemView.findViewById(R.id.descriptionTV);
        }
    }
}


