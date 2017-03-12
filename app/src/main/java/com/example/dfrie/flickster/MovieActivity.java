package com.example.dfrie.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dfrie.flickster.adapter.MovieArrayAdapter;
import com.example.dfrie.flickster.model.Movie;
import com.example.dfrie.flickster.util.MovieUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class MovieActivity extends AppCompatActivity {

    public static final String EXTRA_MEDIA_ID = "com.example.dfrie.flickster.MEDIA_ID";

    ArrayList<Movie> movieList;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvItems = (ListView)findViewById(R.id.lvMovies);
        movieList = new ArrayList<Movie>();
        movieAdapter = new MovieArrayAdapter(this, movieList);
        lvItems.setAdapter(movieAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent i = new Intent(getApplicationContext(), PlayMovieActivity.class);
                i.putExtra(EXTRA_MEDIA_ID, movieList.get(position).getId());
                startActivity(i);

                //Toast.makeText(MovieActivity.this, "try this", Toast.LENGTH_LONG).show();
            }
        });


        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                JSONArray movieResults = null;

                try {
                    movieResults = response.getJSONArray("results");
                    log.d("MovieActivity:getNowPlaying", movieResults.toString());
                    // Don't disconnect the movieList from the adapter here...
                    //movieList = MovieUtil.moviesFromJsonArray(movieResults);
                    movieList.addAll(MovieUtil.moviesFromJsonArray(movieResults));
                    movieAdapter.notifyDataSetChanged();

                    log.d("MovieActivity:getNowPlaying", movieList.toString());
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
}
