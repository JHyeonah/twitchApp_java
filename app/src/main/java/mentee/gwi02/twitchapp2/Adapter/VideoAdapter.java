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

import mentee.gwi02.twitchapp2.Activity.VideoActivity;
import mentee.gwi02.twitchapp2.Activity.WebviewActivity;
import mentee.gwi02.twitchapp2.Model.Videos;
import mentee.gwi02.twitchapp2.R;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Videos.Video> vList;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    public VideoAdapter(ArrayList<Videos.Video> list, Context context){
        this.vList = list;
        this.context= context;
    }

    public class VideoViewHolders extends RecyclerView.ViewHolder{
        TextView display_name_text, title_text, game_text, time_text;
        ImageView preview_small_img, logo_img;

        public VideoViewHolders(View view){
            super(view);
            this.display_name_text = view.findViewById(R.id.display_name_text);
            this.title_text = view.findViewById(R.id.title_text);
            this.time_text = view.findViewById(R.id.time_text);
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RecyclerView.ViewHolder holder;

        if(i == TYPE_HEADER){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_header,viewGroup,false);
            holder = new VideoAdapter.HeaderViewHolder(view);
        }
        else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.follow_item_video, viewGroup, false);
            holder = new VideoAdapter.VideoViewHolders(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
            headerViewHolder.header_text.setText(R.string.video_list);
        }else {
            final Videos.Video item = vList.get(i-1);
            VideoViewHolders holders = (VideoViewHolders)holder;

            String icon_url = item.getChannel().getLogo();
            String preview_url = item.getPreview().getSmall();
            final String video_url = item.getUrl();
            String time = getTime(item.getLength());

            holders.display_name_text.setText(item.getChannel().getDisplayName() + " (" + item.getChannel().getName()+")");
            holders.title_text.setText(item.getTitle());
            holders.game_text.setText(item.getPublishedAt().substring(0,10) + " | " + item.getGame());
            holders.time_text.setText(time);

            Glide.with(context)
                    .load(icon_url)
                    .into(holders.logo_img);

            Glide.with(context)
                    .load(preview_url)
                    .into(holders.preview_small_img);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebviewActivity.class);
                    intent.putExtra("url", video_url);
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
        return vList.size() + 1;
    }

    public String getTime(int time){
        int hour, minute, second;
        String sSecond = "";
        String sMinute = "";
        String sTime = "";

        hour = (int)time/3600;
        time -= hour*3600;
        minute = (int)time/60;
        time -= minute*60;
        second = time;

        sSecond = String.valueOf(second);
        sMinute = String.valueOf(minute);

        if(minute < 10){
            sMinute = "0" + sMinute;
        }

        if(second < 10){
            sSecond = "0" + sSecond;
        }

        sTime = String.valueOf(hour) + ":" + sMinute + ":" + sSecond;
        return sTime;
    }
}
