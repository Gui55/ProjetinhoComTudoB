package com.example.supermerganimeroom;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimeTest {

    @Test
    public void deveRetornarOsParametrosCorretos(){

        Anime anime = new Anime("Karakai Jouzu no Takagi-san", "Slice of Life", 2018, "Shin-Ei Animation");
        String nome = anime.getNome();
        String genero = anime.getGenero();
        int ano = anime.getAno();
        String estudio = anime.getEstudio();


        //Expected = valor esperado
        //Actual = objeto à ser testado

        assertEquals("Karakai Jouzu no Takagi-san", nome);
        assertEquals("Slice of Life", genero);
        assertEquals(2018, anime.getAno());
        assertEquals("Shin-Ei Animation", anime.getEstudio());



    }

}