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
import android.widget.TextView;

import java.util.ArrayList;

import mentee.gwi02.twitchapp2.Adapter.FollowOnlineAdapter;
import mentee.gwi02.twitchapp2.Model.ChannelData;
import mentee.gwi02.twitchapp2.R;
import mentee.gwi02.twitchapp2.TwitchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    Call<ChannelData> callChannel;
    ArrayList<ChannelData.Stream> cData;
    RecyclerView channelRecyclerView;
    FollowOnlineAdapter followOnlineAdapter;
    EditText search_edittext;
    ImageView search_icon;
    TextView search_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        channelRecyclerView = findViewById(R.id.channelRecyclerView);
        channelRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        search_edittext = findViewById(R.id.search_edittext);
        search_icon = findViewById(R.id.search_icon);
        search_text = findViewById(R.id.search_text);

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
                if(response.body() != null){
                    ChannelData ex = response.body();
                    cData = new ArrayList<>(ex.getStreams());

                    followOnlineAdapter = new FollowOnlineAdapter(cData, getApplicationContext());
                    channelRecyclerView.setAdapter(followOnlineAdapter);
                }else{
                    search_text.setText(R.string.result_none);
                }
            }

            @Override
            public void onFailure(Call<ChannelData> call, Throwable t) {

            }
        });
    }
}
