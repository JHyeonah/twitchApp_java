package mentee.gwi02.twitchapp2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import mentee.gwi02.twitchapp2.Adapter.VideoAdapter;
import mentee.gwi02.twitchapp2.Model.Videos;
import mentee.gwi02.twitchapp2.R;
import mentee.gwi02.twitchapp2.TwitchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {
    Call<Videos> callVideo;
    ArrayList<Videos.Video> vData;
    RecyclerView videoRecyclerView;
    VideoAdapter videoAdapter;
    EditText search_edittext;
    ImageView search_icon;
    SwipeRefreshLayout swipeLayout;
    TwitchService twitchService;
    int offset = 0;
    String id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoRecyclerView = findViewById(R.id.videoRecyclerView);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        videoRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        search_edittext = findViewById(R.id.search_edittext);
        search_icon = findViewById(R.id.search_icon);
        swipeLayout = findViewById(R.id.swipe_layout);

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);

                String search = search_edittext.getText().toString();
                intent.putExtra("search", search);

                startActivity(intent);
                finish();
            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                videoAdapter.notifyDataSetChanged();

                swipeLayout.setRefreshing(false);
            }
        });

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        vData = new ArrayList<>();

        twitchService = TwitchService.retrofit.create(TwitchService.class);
        callVideo = twitchService.getVideo(id, offset);
        getResult();

    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(!videoRecyclerView.canScrollVertically(1)){
                offset += 10;

                callVideo = twitchService.getVideo(id, offset);
                callVideo.enqueue(new Callback<Videos>() {
                    @Override
                    public void onResponse(Call<Videos> call, Response<Videos> response) {
                        int size = response.body().getVideos().size();

                        for(int i=0; i<size; i++){
                            vData.add(response.body().getVideos().get(i));
                        }

                        videoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Videos> call, Throwable t) {

                    }
                });
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    public void getResult(){
        callVideo.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                int size = response.body().getVideos().size();

                for(int i=0; i<size; i++){
                    vData.add(0, response.body().getVideos().get(i));
                }

                Log.d("video콜",vData.toString());

                videoAdapter = new VideoAdapter(vData, getApplicationContext());
                videoRecyclerView.setAdapter(videoAdapter);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                Log.d("video콜","falilure호출" + t.toString());
            }
        });
    }


}
