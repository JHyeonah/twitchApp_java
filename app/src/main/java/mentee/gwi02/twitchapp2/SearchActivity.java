package mentee.gwi02.twitchapp2;

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

import mentee.gwi02.twitchapp2.Adapter.FollowOnlineAdapter;
import mentee.gwi02.twitchapp2.Adapter.OfflineAdapter;
import mentee.gwi02.twitchapp2.Model.ChannelData;
import mentee.gwi02.twitchapp2.Model.Search;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    Call<ChannelData> callChannel;
    ArrayList<ChannelData.Stream> cData;
    RecyclerView channelRecyclerView;
    FollowOnlineAdapter followOnlineAdapter;
    EditText search_edittext;
    ImageView search_icon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        channelRecyclerView = findViewById(R.id.channelRecyclerView);
        channelRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        search_edittext = findViewById(R.id.search_edittext);
        search_icon = findViewById(R.id.search_icon);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

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


        final TwitchService twitchService = TwitchService.retrofit.create(TwitchService.class);
        callChannel = twitchService.searchChannels(search);

        callChannel.enqueue(new Callback<ChannelData>() {
            @Override
            public void onResponse(Call<ChannelData> call, Response<ChannelData> response) {
                ChannelData ex = response.body();
                cData = new ArrayList<>(ex.getStreams());
                Log.d("search콜",cData.get(0).getChannel().getDisplayName());

                followOnlineAdapter = new FollowOnlineAdapter(cData, getApplicationContext());
                channelRecyclerView.setAdapter(followOnlineAdapter);
            }

            @Override
            public void onFailure(Call<ChannelData> call, Throwable t) {

            }
        });
    }
}
