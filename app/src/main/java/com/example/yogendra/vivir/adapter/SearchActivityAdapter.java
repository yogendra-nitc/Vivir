package com.example.yogendra.vivir.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.tenant.RegUserSearch;
import com.example.yogendra.vivir.tenant.SearchItem;
import com.example.yogendra.vivir.user.flatDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/** * Created by yogenddra on 29/03/2018. */

public class SearchActivityAdapter  extends
        RecyclerView.Adapter<SearchActivityAdapter.Holderview>{

    private List<SearchItem> flatList;
    private Context context;

    public SearchActivityAdapter(List<SearchItem> flatList, Context context) {
        this.flatList = flatList;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.search_custom_layout, parent,false);

        return new Holderview(layout);
    }

    @Override    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_name.setText(flatList.get(position).getName());
        Picasso.with(context).load(flatList.get(position).getPicture()).into(holder.v_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context,flatDetails.class);
                in.putExtra("flatId" , flatList.get(position).getId());
                view.getContext().startActivity(in);
            }
        });
    }

    @Override    public int getItemCount() {
        return flatList.size();
    }


    public void setfilter(List<SearchItem> listitem)
    {
        flatList=new ArrayList<>();
        flatList.addAll(listitem);
        notifyDataSetChanged();
    }
    class Holderview extends RecyclerView.ViewHolder
    {
        ImageView v_image;
        TextView v_name;

        Holderview(View itemview)
        {
            super(itemview);
            v_image=(ImageView) itemView.findViewById(R.id.flatImage);
            v_name = (TextView) itemView.findViewById(R.id.flatTitle);

        }
    }
}