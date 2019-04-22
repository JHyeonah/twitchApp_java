package mentee.gwi02.twitchapp2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mentee.gwi02.twitchapp2.Model.Example;

public class FollowOnlineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Example.Stream> sList;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        TextView display_name_text, status_text, game_text;
        ImageView preview_small_img, logo_img;

        public RecyclerViewHolders(View view){
            super(view);
            this.display_name_text = view.findViewById(R.id.display_name_text);
            this.status_text = view.findViewById(R.id.status_text);
            this.game_text = view.findViewById(R.id.game_text);
            this.preview_small_img = view.findViewById(R.id.preview_small_img);
            this.logo_img = view.findViewById(R.id.logo_img);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        HeaderViewHolder(View headerView){
            super(headerView);
        }
    }

    public FollowOnlineAdapter(ArrayList<Example.Stream> list, Context context){
        this.sList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RecyclerView.ViewHolder holder;

        if(i == TYPE_HEADER){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_online_header,viewGroup,false);
            holder = new HeaderViewHolder(view);
        }
        else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_online, viewGroup, false);
            holder = new RecyclerViewHolders(view);
        }

        return holder;
    }


    @Override
    public int getItemCount() {
        return sList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
        }else{
            Example.Stream item = sList.get(i-1);
            RecyclerViewHolders holders = (RecyclerViewHolders)holder;

            String preview_url = item.getPreview().getSmall();
            String logo_url = item.getChannel().getLogo();

            holders.display_name_text.setText(item.getChannel().getDisplayName() + " (" + item.getChannel().getName() + ")");
            holders.status_text.setText(item.getChannel().getStatus());
            holders.game_text.setText(item.getChannel().getGame());

            Glide.with(context)
                    .load(preview_url)
                    .into(holders.preview_small_img);

            Glide.with(context)
                    .load(logo_url)
                    .into(holders.logo_img);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
