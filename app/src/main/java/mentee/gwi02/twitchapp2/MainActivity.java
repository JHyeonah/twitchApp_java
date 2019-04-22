package mentee.gwi02.twitchapp2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mentee.gwi02.twitchapp2.Model.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String USER_ID = "188618213";
    TextView textView;
    RecyclerView onlineRecyclerView;
    LinearLayoutManager mLayoutManager;
    FollowOnlineAdapter followOnlineAdapter;
    Call<Example> call;
    ArrayList<Example.Stream> exData;
    // GET https://api.twitch.tv/kraken/user/?client_id=emxt0p6s6th2tetp5swle01t5ptmiq <-- 유저정보
    // GET https://api.twitch.tv/kraken/users/188618213/follows/channels <-- 팔로우 채널
    // GET https://api.twitch.tv/kraken/streams/followed <-- 팔로우채널_온라인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onlineRecyclerView = findViewById(R.id.onlineRecyclerView);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        onlineRecyclerView.setLayoutManager(mLayoutManager);

        TwitchService twitchService = TwitchService.retrofit.create(TwitchService.class);
        call = twitchService.getOnlineChannel();
        //new NetCall().execute(call);


        getResult();
    }

    public void getResult(){
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.body() != null){

                    Example example = response.body();
                    exData = new ArrayList<>(example.getStreams());
                    followOnlineAdapter = new FollowOnlineAdapter(exData, getApplicationContext());
                    onlineRecyclerView.setAdapter(followOnlineAdapter);

                    String ex = response.body().getStreams().get(1).getChannel().getDisplayName();
                    Log.d("콜 실행", ex);
                }else{
                    Log.d("콜 실행", "body 없음");
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();
                Log.d("콜 실행", "onFailure 실행" + t.toString());
            }
        });
    }
/*
    private class NetCall extends AsyncTask<Call, Void, List<Example.Stream>> {
        @Override
        protected List<Example.Stream> doInBackground(Call... calls) {
            try{
                Call<Example> call = calls[0];
                Response<Example> response = call.execute();
                return response.body().getStreams();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Example.Stream> s) {
            textView.setText(s.get());
        }
    }*/
}
