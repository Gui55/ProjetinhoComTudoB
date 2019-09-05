package com.example.supermerganimeroom;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class UsuarioDAO_Impl implements UsuarioDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUsuario;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUsuario;

  private final SharedSQLiteStatement __preparedStmtOfApagarTudo;

  public UsuarioDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuario = new EntityInsertionAdapter<Usuario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Usuario`(`id`,`nome`,`sobrenome`,`nomeDeUsuario`,`email`,`endereco`,`fotoPerfil`,`senha`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Usuario value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getSobrenome() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSobrenome());
        }
        if (value.getNomeDeUsuario() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNomeDeUsuario());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEmail());
        }
        if (value.getEndereco() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndereco());
        }
        if (value.getFotoPerfil() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFotoPerfil());
        }
        if (value.getSenha() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getSenha());
        }
      }
    };
    this.__deletionAdapterOfUsuario = new EntityDeletionOrUpdateAdapter<Usuario>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Usuario` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Usuario value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfApagarTudo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM usuario";
        return _query;
      }
    };
  }

  @Override
  public void insert(Usuario... usuario) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsuario.insert(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Usuario usuario) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUsuario.handle(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void apagarTudo() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfApagarTudo.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfApagarTudo.release(_stmt);
    }
  }

  @Override
  public List<Usuario> getUsuarios() {
    final String _sql = "SELECT * FROM usuario";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfSobrenome = _cursor.getColumnIndexOrThrow("sobrenome");
      final int _cursorIndexOfNomeDeUsuario = _cursor.getColumnIndexOrThrow("nomeDeUsuario");
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfEndereco = _cursor.getColumnIndexOrThrow("endereco");
      final int _cursorIndexOfFotoPerfil = _cursor.getColumnIndexOrThrow("fotoPerfil");
      final int _cursorIndexOfSenha = _cursor.getColumnIndexOrThrow("senha");
      final List<Usuario> _result = new ArrayList<Usuario>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Usuario _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpSobrenome;
        _tmpSobrenome = _cursor.getString(_cursorIndexOfSobrenome);
        final String _tmpNomeDeUsuario;
        _tmpNomeDeUsuario = _cursor.getString(_cursorIndexOfNomeDeUsuario);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpEndereco;
        _tmpEndereco = _cursor.getString(_cursorIndexOfEndereco);
        final String _tmpFotoPerfil;
        _tmpFotoPerfil = _cursor.getString(_cursorIndexOfFotoPerfil);
        final String _tmpSenha;
        _tmpSenha = _cursor.getString(_cursorIndexOfSenha);
        _item = new Usuario(_tmpNome,_tmpSobrenome,_tmpNomeDeUsuario,_tmpEmail,_tmpEndereco,_tmpFotoPerfil,_tmpSenha);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int existeUsuarioComEssaSenha(String nUser, String nPass) {
    final String _sql = "SELECT COUNT(*) FROM usuario WHERE nomeDeUsuario LIKE ? AND senha LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (nUser == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nUser);
    }
    _argIndex = 2;
    if (nPass == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nPass);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int existeUsuario(String nUser) {
    final String _sql = "SELECT COUNT(*) FROM usuario WHERE nomeDeUsuario LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nUser == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nUser);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getFoto(String nUser) {
    final String _sql = "SELECT fotoPerfil FROM usuario WHERE nomeDeUsuario LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nUser == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nUser);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int idDoSelecionado(String nUser) {
    final String _sql = "SELECT id FROM usuario WHERE nomeDeUsuario LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nUser == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nUser);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String usernameDoSelecionado(int nId) {
    final String _sql = "SELECT nomeDeUsuario FROM usuario WHERE id LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, nId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
