package mentee.gwi02.twitchapp2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mentee.gwi02.twitchapp2.Activity.VideoActivity;
import mentee.gwi02.twitchapp2.Model.Follows;
import mentee.gwi02.twitchapp2.R;

public class OfflineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Follows.Follow> fList;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    public class OfflineViewHolders extends RecyclerView.ViewHolder{
        TextView streamer_name;
        ImageView offline_icon;

        public OfflineViewHolders(View view){
            super(view);
            this.streamer_name = view.findViewById(R.id.streamer_name);
            this.offline_icon = view.findViewById(R.id.offline_icon);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView header_text;
        HeaderViewHolder(View headerView){
            super(headerView);
            this.header_text = headerView.findViewById(R.id.header_text);
        }
    }

    public OfflineAdapter(ArrayList<Follows.Follow> list, Context context){
        this.fList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RecyclerView.ViewHolder holder;

        if(i == TYPE_HEADER){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_header,viewGroup,false);
            holder = new OfflineAdapter.HeaderViewHolder(view);
        }
        else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_offline, viewGroup, false);
            holder = new OfflineAdapter.OfflineViewHolders(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
            headerViewHolder.header_text.setText(R.string.offline_channel);
        }else {
            final Follows.Follow item = fList.get(i-1);
            OfflineViewHolders holders = (OfflineViewHolders)holder;

            String icon_url = item.getChannel().getLogo();
            holders.streamer_name.setText(item.getChannel().getDisplayName() + " (" + item.getChannel().getName()+")");

            Glide.with(context)
                    .load(icon_url)
                    .into(holders.offline_icon);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = String.valueOf(item.getChannel().getId());
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return fList.size() + 1;
    }
}
