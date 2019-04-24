package mentee.gwi02.twitchapp2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoRecyclerView = findViewById(R.id.videoRecyclerView);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        search_edittext = findViewById(R.id.search_edittext);
        search_icon = findViewById(R.id.search_icon);

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

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        final TwitchService twitchService = TwitchService.retrofit.create(TwitchService.class);
        callVideo = twitchService.getVideo(id);

        callVideo.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                vData = new ArrayList<>(response.body().getVideos());
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
