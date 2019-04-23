package mentee.gwi02.twitchapp2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import mentee.gwi02.twitchapp2.Adapter.FollowOnlineAdapter;
import mentee.gwi02.twitchapp2.Adapter.OfflineAdapter;
import mentee.gwi02.twitchapp2.Model.ChannelData;
import mentee.gwi02.twitchapp2.Model.Follows;
import mentee.gwi02.twitchapp2.Model.Recommend;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView onlineRecyclerView, recommendRecyclerView, offlineRecyclerView;
    FollowOnlineAdapter followOnlineAdapter;
    OfflineAdapter offlineAdapter;
    Call<ChannelData> callEx;
    Call<Recommend> callRe;
    Call<Follows> callFo;
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

        onlineRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recommendRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        offlineRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        TwitchService twitchService = TwitchService.retrofit.create(TwitchService.class);
        callEx = twitchService.getOnlineChannel();
        callRe = twitchService.getRecommend();
        callFo = twitchService.getFollows();

        getResult();
    }

    public void getResult(){
        callEx.enqueue(new Callback<ChannelData>() {
            @Override
            public void onResponse(Call<ChannelData> call, Response<ChannelData> response) {
                if(response.body() != null){

                    ChannelData example = response.body();
                    exData = new ArrayList<>(example.getStreams());
                    followOnlineAdapter = new FollowOnlineAdapter(exData, getApplicationContext());
                    onlineRecyclerView.setAdapter(followOnlineAdapter);

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
                }else{
                    Log.d("Re콜 실행", "body 없음");
                }
            }

            @Override
            public void onFailure(Call<Recommend> call, Throwable t) {
                Log.d("Re콜 실행", t.toString());
            }
        });

        callFo.enqueue(new Callback<Follows>() {
            @Override
            public void onResponse(Call<Follows> call, Response<Follows> response) {
                if(response.body() != null){
                    Follows follows = response.body();
                    foData = new ArrayList<>(follows.getFollows());

                    Log.d("EXDATA", String.valueOf(exData.size()));
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
