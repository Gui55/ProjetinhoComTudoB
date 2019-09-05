package com.example.supermerganimeroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AnimeListActivity extends AppCompatActivity {

    private AnimeAdapter animeAdapter;
    private List<Anime> animeList;
    private RecyclerView recyclerView;

    private EditText nome, genero, ano, estudio;

    public static AnimeDAO animeDAO;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);

        ajustarDao();

        associarEditTexts();

        configurarRecyclerView();

        configurarAlertDialog();

    }

    public void ajustarDao(){

        animeDAO = Room.databaseBuilder(this, AnimeDatabase.class, "animeDB")
                .allowMainThreadQueries()
                .build()
                .animeDAO();

    }

    public void associarEditTexts(){

        nome = (EditText)findViewById(R.id.enterAnimeNome);
        genero = (EditText)findViewById(R.id.enterAnimeGenero);
        ano = (EditText)findViewById(R.id.enterAnimeAno);
        estudio = (EditText)findViewById(R.id.enterAnimeEstudio);

    }

    public void configurarRecyclerView(){

        recyclerView = (RecyclerView)findViewById(R.id.animeRecyclerView);

        recyclerView.setAdapter(new AnimeAdapter(animeDAO.getAnimes(), getApplicationContext(), new AnimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Anime anime) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, anime.getNome());
                startActivity(intent);
            }
        }));

    }

    public void configurarAlertDialog(){

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Título");

        builder.setMessage("Deseja mesmo limpar a lista de animes?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                animeDAO.apagarTudo();

                recyclerView.setAdapter(new AnimeAdapter(animeDAO.getAnimes(), getApplicationContext(),
                        new AnimeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Anime anime) {
                                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                                intent.putExtra(SearchManager.QUERY, anime.getNome());
                                startActivity(intent);
                            }
                        }));

                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Não foi deletado", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog = builder.create();

    }

    public void cadastrarAnime(View view) {

        animeDAO.insert(new Anime(

                nome.getText().toString(),
                genero.getText().toString(),
                Integer.valueOf(ano.getText().toString()),
                estudio.getText().toString()

        ));

        recyclerView.setAdapter(new AnimeAdapter(animeDAO.getAnimes(), this,
                new AnimeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Anime anime) {
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, anime.getNome());
                        startActivity(intent);
                    }
                }));

        recyclerView.getAdapter().notifyDataSetChanged();

    }

    public void limparListaAnime(View view) {

        alertDialog.show();

    }
}
