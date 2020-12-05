package com.health.projetofilme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.health.projetofilme.Media_Activity.Genre;
import com.health.projetofilme.Media_Activity.MovieInfo;
import com.health.projetofilme.Media_Activity.Overview.Overview;

import com.health.projetofilme.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MediaActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdView mAdView;

    public String movie_id;
    public String media_type;
    public String movie_title;
    public String movie_year;
    public List<String> genre_string = new ArrayList<>();
    public String duration;
    private String overview;
    private String original_title;
    private String original_lang;
    private String director;
    private String budget;
    private String revenue;
    private String homepage;
    private String productions;
    private String releases;

    public Genre[] genre;

    private String URL_movieinfo1 = "https://api.themoviedb.org/3/movie/";
    private String URL_movieinfo2 = "?api_key=223444c9a68bd2763fbf89598bb95134&language=en-US";
    private String URL_movieinfo;



    public void addtabs(ViewPager v){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Overview(), "Overview");


        v.setAdapter(adapter);
    }

    private void setoverview(String s){
        overview = s;
    }
    private void setOriginal_title(String s){
        original_title = s;
    }
    private void setOriginal_lang(String s){
        original_lang = s;
    }
    private void setDirector(String s){
        director = s;
    }
    private void setBudget(String s){
        budget = s;
    }
    private void setRevenue(String s){
        revenue = s;
    }
    private void setHomepage(String s){
        homepage = s;
    }
    private void setProductions(String s){
        productions = s;
    }
    private void setReleases(String s){
        releases = s;
    }

    public String getoverview(){
        return overview;
    }

    public String getOriginal_title(){
        return original_title;
    }
    public String getOriginal_lang(){
        return original_lang;
    }
    public String getDirector(){
        return  director;
    }
    public String getBudget(){
        return  budget;
    }
    public String getRevenue(){
        return revenue ;
    }
    public String getHomepage(){
        return homepage ;
    }
    public String getProductions(){
        return productions;
    }
    public String getReleases(){
       return releases;
    }
    public String getmovie_id(){return movie_id;}
    public String getmedia_type(){return media_type;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);


        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        media_type = intent.getStringExtra("media_type");

        Log.d("iD" , movie_id);


        RequestQueue queue = Volley.newRequestQueue(this);
        final ImageView banner = findViewById(R.id.banner);

        switch (media_type){
            case "movie" :
                URL_movieinfo = URL_movieinfo1 + movie_id + URL_movieinfo2;

                StringRequest request1 = new StringRequest(URL_movieinfo, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        MovieInfo users = gson.fromJson(response, MovieInfo.class);
                        Toolbar toolbar = findViewById(R.id.media_toolbar);
                        toolbar.setTitle(users.getTitle());
                        genre = users.getGenres();
                        for(int i =0;i<genre.length; i++) {
                            genre_string.add(genre[i].getName());
                        }
                        if(genre_string.size() > 0) {
                            if(genre_string.size() > 1) {
                                toolbar.setSubtitle(users.getReleaseDate().substring(0,4) + " - " + genre_string.get(0) + " / " +genre_string.get(1) + " - " +users.getRuntime());
                            } else{
                                toolbar.setSubtitle(users.getReleaseDate().substring(0,4) + " - " + genre_string.get(0) + " - " +users.getRuntime());
                            }
                        } else{
                            toolbar.setSubtitle(users.getReleaseDate().substring(0,4) + " - " + users.getRuntime());
                        }
                        setSupportActionBar(toolbar);
                        Picasso.with(MediaActivity.this).load(users.getBackdropPath()).placeholder(R.drawable.movie_placeholder2).into(banner);
                        setoverview(users.getOverview());
                        setBudget(String.valueOf(users.getBudget()));
                        setHomepage(users.getHomepage());
                        setOriginal_lang(users.getOriginalLanguage());


                        setReleases(users.getReleaseDate());
                        setOriginal_title(users.getOriginalTitle());
                        setRevenue(String.valueOf(users.getRevenue()));
                        genre = users.getGenres();
                        movie_title = users.getTitle();
                        movie_year = users.getReleaseDate();
                        duration = users.getRuntime();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MediaActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });



                queue.add(request1);

                break;

        }


        viewPager = findViewById(R.id.media_viewpager);
        addtabs(viewPager);

        tabLayout = findViewById(R.id.media_tabs);
        tabLayout.setupWithViewPager(viewPager);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }
}
