package com.example.yogendra.vivir.adapter;

/**
 * Created by yogendra on 29/4/18.
 */
        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.yogendra.vivir.R;
        import com.example.yogendra.vivir.owner.RequestItem;
        import com.example.yogendra.vivir.owner.requestDetails;

        import java.util.ArrayList;
        import java.util.List;


public class RequestAdapter  extends
        RecyclerView.Adapter<RequestAdapter.Holderview>{

    private List<RequestItem> requestList;
    private Context context;

    public RequestAdapter(List<RequestItem> requestList, Context context) {
        this.requestList = requestList;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.request_custom_layout, parent,false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_name.setText(requestList.get(position).getName());
        holder.v_rdate.setText(requestList.get(position).getRdate());
        holder.v_text.setText(requestList.get(position).getName()+"sent you a...");
        holder.v_image.setImageResource(requestList.get(position).getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent in = new Intent(context,requestDetails.class);
               in.putExtra("rid" , requestList.get(position).getId());
                view.getContext().startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount() {
        return requestList.size();
    }


    public void setfilter(List<RequestItem> listitem)
    {
        requestList=new ArrayList<>();
        requestList.addAll(listitem);
        notifyDataSetChanged();
    }
    class Holderview extends RecyclerView.ViewHolder
    {
        ImageView v_image;
        TextView v_name;
        TextView v_rdate;
        TextView v_text;

        Holderview(View itemview)
        {
            super(itemview);
            v_image=(ImageView)itemView.findViewById(R.id.request_image);
            v_name = (TextView)itemView.findViewById(R.id.tenant_name);
            v_rdate = (TextView)itemview.findViewById(R.id.request_date);
            v_text = (TextView)itemview.findViewById(R.id.request_text);
        }
    }
}