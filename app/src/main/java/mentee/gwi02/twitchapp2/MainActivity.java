package mentee.gwi02.twitchapp2;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    final String CLIENT_ID = "emxt0p6s6th2tetp5swle01t5ptmiq";
    TextView textView;

    // GET https://api.twitch.tv/kraken/user/?client_id=emxt0p6s6th2tetp5swle01t5ptmiq <-- 유저정보
    // GET https://api.twitch.tv/kraken/users/gwi02379/follows/channels?client_id=emxt0p6s6th2tetp5swle01t5ptmiq <-- 팔로우 채널
    // GET https://api.twitch.tv/kraken/videos/followed/?client_id=emxt0p6s6th2tetp5swle01t5ptmiq <-- 팔로우 비디오

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.twitch.tv/kraken/user/?client_id" + CLIENT_ID;

        textView = findViewById(R.id.textView);

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();

    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                JSONObject jsonData = new JSONObject(s);
                //Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference
                //s 값이 비어있음
                Log.d("JSONDATA", jsonData.toString());
                JSONObject jsonDatas = jsonData.getJSONObject("display_name");
                Log.d("JSONDATAS", jsonDatas.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
