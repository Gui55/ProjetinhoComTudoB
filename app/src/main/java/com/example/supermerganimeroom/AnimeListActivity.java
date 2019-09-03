package com.example.supermerganimeroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AnimeListActivity extends AppCompatActivity {

    private AnimeAdapter animeAdapter;
    private List<Anime> animeList;
    private RecyclerView recyclerView;

    private EditText nome, genero, ano, estudio;

    public static AnimeDAO animeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);


        animeDAO = Room.databaseBuilder(this, AnimeDatabase.class, "animeDB")
                .allowMainThreadQueries()
                .build()
                .animeDAO();

        nome = (EditText)findViewById(R.id.enterAnimeNome);
        genero = (EditText)findViewById(R.id.enterAnimeGenero);
        ano = (EditText)findViewById(R.id.enterAnimeAno);
        estudio = (EditText)findViewById(R.id.enterAnimeEstudio);

        recyclerView = (RecyclerView)findViewById(R.id.animeRecyclerView);

        recyclerView.setAdapter(new AnimeAdapter(animeDAO.getAnimes(), this));


    }

    public void cadastrarAnime(View view) {

        animeDAO.insert(new Anime(

                nome.getText().toString(),
                genero.getText().toString(),
                Integer.valueOf(ano.getText().toString()),
                estudio.getText().toString()

        ));

        recyclerView.setAdapter(new AnimeAdapter(animeDAO.getAnimes(), this));

        recyclerView.getAdapter().notifyDataSetChanged();

    }

    public void limparListaAnime(View view) {

        animeDAO.apagarTudo();

        recyclerView.setAdapter(new AnimeAdapter(animeDAO.getAnimes(), this));

        recyclerView.getAdapter().notifyDataSetChanged();

    }
}
