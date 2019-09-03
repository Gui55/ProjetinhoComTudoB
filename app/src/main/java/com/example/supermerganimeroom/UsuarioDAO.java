package com.example.supermerganimeroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert
    void insert(Usuario... usuario);

    @Delete
    void delete(Usuario usuario);

    @Query("DELETE FROM usuario")
    void apagarTudo();

    @Query("SELECT * FROM usuario")
    List<Usuario> getUsuarios();

    //CONTE QUANTOS ELEMENTOS HÁ NA TABELA "usuario" ONDE "nomeDeUsuario" é igual "nUser"
    // e senha é iagual "nPass"

    @Query("SELECT COUNT(*) FROM usuario WHERE nomeDeUsuario LIKE :nUser AND" +
            " senha LIKE :nPass")
    public int existeUsuarioComEssaSenha(String nUser, String nPass);

    @Query("SELECT COUNT(*) FROM usuario WHERE nomeDeUsuario LIKE :nUser")
    public int existeUsuario(String nUser);

    @Query("SELECT fotoPerfil FROM usuario WHERE nomeDeUsuario LIKE :nUser")
    public String getFoto(String nUser);

}
