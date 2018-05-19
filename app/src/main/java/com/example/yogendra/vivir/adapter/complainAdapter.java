package com.example.yogendra.vivir.adapter;

/**
 * Created by yogendra on 9/5/18.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.yogendra.vivir.R;
        import com.example.yogendra.vivir.user.complainDetails;
        import com.example.yogendra.vivir.user.complainItem;
        import java.util.ArrayList;
        import java.util.List;


public class complainAdapter  extends
        RecyclerView.Adapter<complainAdapter.Holderview>{

    private List<complainItem> complainList;
    private Context context;

    public complainAdapter(List<complainItem> complainList, Context context) {
        this.complainList = complainList;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.complain_custom_layout, parent,false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_sname.setText(complainList.get(position).getSname());
        holder.v_rname.setText(complainList.get(position).getRname());
        holder.v_cdate.setText(complainList.get(position).getCdate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context,complainDetails.class);
                in.putExtra("cid" , complainList.get(position).getCid());
                view.getContext().startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount() {
        return complainList.size();
    }


    public void setfilter(List<complainItem> listitem)
    {
        complainList=new ArrayList<>();
        complainList.addAll(listitem);
        notifyDataSetChanged();
    }
    class Holderview extends RecyclerView.ViewHolder
    {
        TextView v_sname;
        TextView v_rname;
        TextView v_cdate;

        Holderview(View itemview)
        {
            super(itemview);
            v_sname = (TextView)itemView.findViewById(R.id.sender_name);
            v_rname = (TextView)itemview.findViewById(R.id.receiver_name);
            v_cdate = (TextView)itemview.findViewById(R.id.complain_date);
        }
    }
}