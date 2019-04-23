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

import mentee.gwi02.twitchapp2.Model.Example;
import mentee.gwi02.twitchapp2.Model.Recommend;
import mentee.gwi02.twitchapp2.R;
import mentee.gwi02.twitchapp2.WebviewActivity;

public class FollowOnlineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Example.Stream> sList;
    private ArrayList<Recommend.Featured> fList;
    private boolean isFeatured = false;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        TextView display_name_text, status_text, game_text, view_text;
        ImageView preview_small_img, logo_img;

        public RecyclerViewHolders(View view){
            super(view);
            this.display_name_text = view.findViewById(R.id.display_name_text);
            this.view_text = view.findViewById(R.id.view_text);
            this.status_text = view.findViewById(R.id.status_text);
            this.game_text = view.findViewById(R.id.game_text);
            this.preview_small_img = view.findViewById(R.id.preview_small_img);
            this.logo_img = view.findViewById(R.id.logo_img);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView header_text;
        HeaderViewHolder(View headerView){
            super(headerView);
            this.header_text = headerView.findViewById(R.id.header_text);
        }
    }

    public FollowOnlineAdapter(ArrayList<Example.Stream> list, Context context){
        this.sList = list;
        this.context = context;
    }

    public FollowOnlineAdapter(ArrayList<Recommend.Featured> list, Context context, boolean isFeatured){
        if(isFeatured){
            this.fList = list;
            this.context = context;
            this.isFeatured = isFeatured;
        }
    }
    public FollowOnlineAdapter(ArrayList<Example.Stream> sList, ArrayList<Recommend.Featured> fList, Context context){
        this.fList = fList;
        this.sList = sList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RecyclerView.ViewHolder holder;

        if(i == TYPE_HEADER){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_header,viewGroup,false);
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
        if(isFeatured)
            return fList.size() + 1;
        else
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;

            if(isFeatured){
                headerViewHolder.header_text.setText("추천 채널");
            }else{
                headerViewHolder.header_text.setText("생방송 채널");
            }
        }else{
            Example.Stream item = null;

            if(isFeatured){
                item = fList.get(i-1).getStream();
            }else{
                item = sList.get(i-1);
            }

            RecyclerViewHolders holders = (RecyclerViewHolders)holder;

            String preview_url = item.getPreview().getSmall();
            String logo_url = item.getChannel().getLogo();

            holders.display_name_text.setText(item.getChannel().getDisplayName() + " (" + item.getChannel().getName() + ")");
            holders.view_text.setText(item.getViewers());
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

                    Example.Stream stream = null;

                    if(isFeatured){
                        stream = fList.get(i-1).getStream();
                    }else{
                        stream = sList.get(i-1);
                    }
                    Intent intent = new Intent(context, WebviewActivity.class);
                    intent.putExtra("url", stream.getChannel().getUrl());
                    context.startActivity(intent);
                }
            });

        }
    }
}
