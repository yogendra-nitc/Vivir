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
        import android.widget.Toast;

        import com.example.yogendra.vivir.R;
        import com.example.yogendra.vivir.owner.RequestItem;
        import com.example.yogendra.vivir.tenant.RegUserSearch;
        import com.example.yogendra.vivir.tenant.SearchItem;
        import com.example.yogendra.vivir.user.flatDetails;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;
        import java.util.List;

/** * Created by yogenddra on 29/03/2018. */

public class RequestAdapter  extends
        RecyclerView.Adapter<RequestAdapter.Holderview>{

    private List<RequestItem> requestList;
    private Context context;

    public RequestAdapter(List<RequestItem> requestList, Context context) {
        this.requestList = requestList;
        this.context = context;
        //setHasStableIds(true);
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.search_custom_layout, parent,false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_name.setText(requestList.get(position).getName());
        Picasso.with(context).load(requestList.get(position).getPicture()).into(holder.v_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context,flatDetails.class);
                in.putExtra("flatId" , requestList.get(position).getId());
                view.getContext().startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount() {
        return requestList.size();
    }


    public void setfilter(List<SearchItem> listitem)
    {
        requestList=new ArrayList<>();
        requestList.addAll(listitem);
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