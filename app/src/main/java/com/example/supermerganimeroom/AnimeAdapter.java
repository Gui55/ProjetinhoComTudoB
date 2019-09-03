package com.example.supermerganimeroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private List<Anime> animeList;
    private Context context;

    public AnimeAdapter(List<Anime> animeList, Context context) {
        this.animeList = animeList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Retorna um ViewHolder

        return new AnimeViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.anime_recycle_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {

        Anime anime = animeList.get(position);

        holder.nome.setText(anime.getNome());
        holder.ano.setText(String.valueOf(anime.getAno()));
        holder.genero.setText(anime.getGenero());
        holder.estudio.setText(anime.getEstudio());

    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public void adiciona(Anime anime){

        animeList.add(anime);
        notifyDataSetChanged();

    }


    protected class AnimeViewHolder extends RecyclerView.ViewHolder {

        TextView nome, ano, genero, estudio;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = (TextView)itemView.findViewById(R.id.recycleName);
            ano = (TextView)itemView.findViewById(R.id.recycleAno);
            genero = (TextView)itemView.findViewById(R.id.recycleGenero);
            estudio = (TextView)itemView.findViewById(R.id.recycleEstudio);

        }
    }


}
