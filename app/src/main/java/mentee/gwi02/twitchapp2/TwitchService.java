package mentee.gwi02.twitchapp2;

import java.util.List;

import mentee.gwi02.twitchapp2.Model.Example;
import mentee.gwi02.twitchapp2.Model.Follows;
import mentee.gwi02.twitchapp2.Model.Recommend;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TwitchService {
    @Headers({
            "Accept: application/vnd.twitchtv.v5+json",
            "Client-ID: emxt0p6s6th2tetp5swle01t5ptmiq",
            "Authorization: OAuth fw5kins9bby5tpp88kdz5dpa395npf"
    })
    @GET("kraken/streams/followed")
    Call<Example> getOnlineChannel();

    @Headers({
            "Accept: application/vnd.twitchtv.v5+json",
            "Client-ID: emxt0p6s6th2tetp5swle01t5ptmiq"
    })
    @GET("kraken/streams/featured?limit=5")
    Call<Recommend> getRecommend();

    @Headers({
            "Accept: application/vnd.twitchtv.v5+json",
            "Client-ID: emxt0p6s6th2tetp5swle01t5ptmiq"
    })
    @GET("kraken/users/188618213/follows/channels")
    Call<Follows> getFollows();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
