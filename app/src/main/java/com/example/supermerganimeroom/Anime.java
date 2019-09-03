package com.example.supermerganimeroom;

import android.graphics.Bitmap;

import java.util.List;

public class Anime {

    String nome;
    String genero;
    int ano;
    String estudio;

    public Anime(String nome, String genero, int ano, String estudio) {
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.estudio = estudio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }



}
