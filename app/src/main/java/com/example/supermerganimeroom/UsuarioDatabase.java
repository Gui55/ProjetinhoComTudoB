package com.example.supermerganimeroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)

public abstract class UsuarioDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();

}
