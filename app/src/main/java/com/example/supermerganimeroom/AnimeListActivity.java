package com.example.supermerganimeroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AnimeListActivity extends AppCompatActivity {

    private AnimeAdapter animeAdapter;
    private List<Anime> animeList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);

        AnimeAdapter animeAdapter = new AnimeAdapter(animeLista(), this);

        recyclerView = (RecyclerView)findViewById(R.id.animeRecyclerView);

        recyclerView.setAdapter(animeAdapter);


    }


    private List<Anime> animeLista(){

        List<Anime> animeList = new ArrayList<Anime>();

        animeList.add(new Anime("Karakai Jouzu no Takagi-san", "Slice of Life / Comedy", 2018, "Shin-Ei Animation"));

        animeList.add(new Anime("Re: Zero kara Hajimeru Isekai Seikatsu", "Isekai / Adventure", 2016, "White Fox"));

        return animeList;

    }
}
