package com.example.supermerganimeroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface AnimeDAO {

    @Insert
    void insert(Anime... anime);

    @Delete
    void delete(Anime... anime);

    @Query("SELECT * FROM anime")
    List<Anime> getAnimes();

    @Query("DELETE FROM anime")
    void apagarTudo();

}
