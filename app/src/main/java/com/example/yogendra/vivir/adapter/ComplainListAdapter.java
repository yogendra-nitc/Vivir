package com.example.yogendra.vivir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yogendra.vivir.user.ComplaintValue;
import com.example.yogendra.vivir.R;

import java.util.List;

/**
 * Created by yogendra on 4/4/18.
 */

public class ComplainListAdapter  extends
        RecyclerView.Adapter<ComplainListAdapter.Holderview>{

    private List<ComplaintValue> complaintList;
    private Context context;

    public ComplainListAdapter(List<ComplaintValue> complaintList, Context context) {
        this.complaintList = complaintList;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_activity_complain_list, parent,false);

        return new Holderview(layout);
    }

    @Override    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_name.setText(complaintList.get(position).getName());
        holder.v_image.setImageResource(complaintList.get(position).getPicture());
        holder.v_date.setText(complaintList.get(position).getCdate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Toast.makeText(context, "click on " + complaintList.get(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override    public int getItemCount() {
        return complaintList.size();
    }

    class Holderview extends RecyclerView.ViewHolder
    {
        ImageView v_image;
        TextView v_name;
        TextView v_date;

        Holderview(View itemview)
        {
            super(itemview);
            v_image=(ImageView) itemView.findViewById(R.id.userImage);
            v_name = (TextView) itemView.findViewById(R.id.userName);
            v_date = (TextView) itemView.findViewById(R.id.cdate);

        }
    }
}
