package com.example.dfrie.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dfrie.flickster.model.Trailer;
import com.example.dfrie.flickster.util.MovieUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class PlayMovieActivity extends YouTubeBaseActivity {

    private static String TRAILER_TYPE = "Trailer";
    private static String API_KEY = "AIzaSyBrDv3cCi5wnmS8RlgX5cFgQ9Xs6-NECgI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.vvPlayer);
        youTubePlayerView.initialize("a07e22bc18f5cb106bfe4cc1f83ad8ed",
            new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                    final YouTubePlayer youTubePlayer, boolean b) {
                    Intent intent = getIntent();
                    Long id = intent.getLongExtra(MovieActivity.EXTRA_MEDIA_ID, -1L);

                    String url = String.format("https://api.themoviedb.org/3/movie/%1$d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", id.longValue());
                    AsyncHttpClient client = new AsyncHttpClient();

                    client.get(url, new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            JSONArray movieResults = null;

                            try {
                                movieResults = response.getJSONArray("results");
                                log.d("PlayMovieActivity:init", movieResults.toString());

                                List<Trailer> list = MovieUtil.trailersFromJsonArray(movieResults);
                                log.d("PlayMovieActivity:init2", list.toString());

                                // TODO:  for now, play the first trailer only...
                                String key = null;
                                for (Trailer trailer: list) {
                                    if (trailer.getType().equals(TRAILER_TYPE)) {
                                        key = trailer.getKey();
                                        break;
                                    }
                                }
                                log.d("PlayMovieActivity:init3", "key=" + key);
                                youTubePlayer.loadVideo(key);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }
                    });
                }
                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                    YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(PlayMovieActivity.this, "Cannot load video: "+
                            youTubeInitializationResult.toString(), Toast.LENGTH_LONG);
                }
            });
    }
}
