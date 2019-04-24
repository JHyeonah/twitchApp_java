package mentee.gwi02.twitchapp2.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import mentee.gwi02.twitchapp2.Adapter.FollowOnlineAdapter;
import mentee.gwi02.twitchapp2.Adapter.OfflineAdapter;
import mentee.gwi02.twitchapp2.Model.ChannelData;
import mentee.gwi02.twitchapp2.Model.Follows;
import mentee.gwi02.twitchapp2.Model.Recommend;
import mentee.gwi02.twitchapp2.R;
import mentee.gwi02.twitchapp2.TwitchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView onlineRecyclerView, recommendRecyclerView, offlineRecyclerView;
    EditText search_edittext;
    ImageView search_icon;
    FollowOnlineAdapter followOnlineAdapter;
    OfflineAdapter offlineAdapter;
    Call<ChannelData> callData;
    Call<Recommend> callRe;
    Call<Follows> callFollows;
    ArrayList<ChannelData.Stream> exData;
    ArrayList<Recommend.Featured> reData;
    ArrayList<Follows.Follow> foData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onlineRecyclerView = findViewById(R.id.onlineRecyclerView);
        recommendRecyclerView = findViewById(R.id.recommendRecyclerView);
        offlineRecyclerView = findViewById(R.id.offlineRecyclerView);
        search_edittext = findViewById(R.id.search_edittext);
        search_icon = findViewById(R.id.search_icon);

        onlineRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recommendRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        offlineRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final TwitchService twitchService = TwitchService.retrofit.create(TwitchService.class);
        callData = twitchService.getOnlineChannel();
        callRe = twitchService.getRecommend();
        callFollows = twitchService.getFollows();

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);

                String search = search_edittext.getText().toString();
                intent.putExtra("search", search);

                startActivity(intent);
            }
        });

        getResult();
    }

    public void getResult(){
        callData.enqueue(new Callback<ChannelData>() {
            @Override
            public void onResponse(Call<ChannelData> call, Response<ChannelData> response) {
                if(response.body() != null){

                    ChannelData example = response.body();
                    exData = new ArrayList<>(example.getStreams());
                    followOnlineAdapter = new FollowOnlineAdapter(exData, getApplicationContext());
                    onlineRecyclerView.setAdapter(followOnlineAdapter);
                    ViewCompat.setNestedScrollingEnabled(onlineRecyclerView, false);
                }else{
                    Log.d("Ex콜 실행", "body 없음");
            }

            }

            @Override
            public void onFailure(Call<ChannelData> call, Throwable t) {
                t.printStackTrace();
                Log.d("Ex콜 실행", "onFailure 실행" + t.toString());
            }
        });


        callRe.enqueue(new Callback<Recommend>() {
            @Override
            public void onResponse(Call<Recommend> call, Response<Recommend> response) {
                if(response.body() != null){
                    Recommend recommend = response.body();
                    reData = new ArrayList<>(recommend.getFeatured());
                    Log.d("Re콜 실행", reData.toString());
                    followOnlineAdapter = new FollowOnlineAdapter(reData, getApplicationContext(), true);
                    recommendRecyclerView.setAdapter(followOnlineAdapter);
                    ViewCompat.setNestedScrollingEnabled(recommendRecyclerView, false);
                }else{
                    Log.d("Re콜 실행", "body 없음");
                }
            }

            @Override
            public void onFailure(Call<Recommend> call, Throwable t) {
                Log.d("Re콜 실행", t.toString());
            }
        });

        callFollows.enqueue(new Callback<Follows>() {
            @Override
            public void onResponse(Call<Follows> call, Response<Follows> response) {
                if(response.body() != null){
                    Follows follows = response.body();
                    foData = new ArrayList<>(follows.getFollows());

                    if(exData.size() != 0){
                        for(int i = 0; i<exData.size(); i++){
                            for(int j = 0; j<foData.size(); j++){
                                if(exData.get(i).getChannel().getName().equals(foData.get(j).getChannel().getName())){
                                    foData.remove(j);
                                }
                            }
                        }
                    }

                    offlineAdapter = new OfflineAdapter(foData,getApplicationContext());
                    offlineRecyclerView.setAdapter(offlineAdapter);
                    ViewCompat.setNestedScrollingEnabled(offlineRecyclerView, false);

                }else{
                    Log.d("Fo콜 실행", "body 없음");
                }
            }

            @Override
            public void onFailure(Call<Follows> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private static final class CustomLinearLayoutManager extends LinearLayoutManager{
        private boolean isEnabledScrolling = false;

        public CustomLinearLayoutManager(Context context){
            super(context);
        }

        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout){
            super(context,orientation,reverseLayout);
        }

        public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public void setEnabledScrolling(boolean b){
            isEnabledScrolling = b;
        }

        @Override
        public boolean canScrollVertically() {
            return isEnabledScrolling && super.canScrollVertically();
        }
    }

}
