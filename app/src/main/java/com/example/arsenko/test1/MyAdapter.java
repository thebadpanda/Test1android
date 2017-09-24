package com.example.arsenko.test1;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {

        final RecyclerItem itemList = listItems.get(position);
        holder.titleTV.setText(itemList.getTitle());
        holder.descriptionTV.setText(itemList.getDescription());
        holder.imageView.setImageResource(R.drawable._eastwood);
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listItems.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTV;
        public TextView descriptionTV;
        public TextView txtOption;
        public ImageView imageView;
        public Button delBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            descriptionTV = (TextView) itemView.findViewById(R.id.descriptionTV);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            txtOption = (TextView) itemView.findViewById(R.id.txtOption);
            delBtn = (Button) itemView.findViewById(R.id.delBtn);


        }
    }
}


