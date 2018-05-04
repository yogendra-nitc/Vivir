package com.example.yogendra.vivir.adapter;

/**
 * Created by yogendra on 3/5/18.
 */

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.yogendra.vivir.R;
        import com.example.yogendra.vivir.user.NotificationItem;

        import java.util.ArrayList;
        import java.util.List;


public class NotificationAdapter   extends
        RecyclerView.Adapter<NotificationAdapter.Holderview>{

    private List<NotificationItem> notificationList;
    private Context context;

    public NotificationAdapter(List<NotificationItem> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_activity_notification, parent,false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_icon.setImageResource(notificationList.get(position).getIcon());
        holder.v_content.setText(notificationList.get(position).getContent());
        holder.v_ndate.setText(notificationList.get(position).getNdate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    public void setfilter(List<NotificationItem> listitem)
    {
        notificationList=new ArrayList<>();
        notificationList.addAll(listitem);
        notifyDataSetChanged();
    }
    class Holderview extends RecyclerView.ViewHolder
    {
        ImageView v_icon;
        TextView v_content;
        TextView v_ndate;

        Holderview(View itemview)
        {
            super(itemview);
            v_icon = (ImageView) itemview.findViewById(R.id.notification_image);
            v_content = (TextView)itemView.findViewById(R.id.notification_text);
            v_ndate = (TextView)itemview.findViewById(R.id.notification_date);
        }
    }
}