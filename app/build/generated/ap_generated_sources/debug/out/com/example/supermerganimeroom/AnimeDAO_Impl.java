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
public final class AnimeDAO_Impl implements AnimeDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAnime;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfAnime;

  private final SharedSQLiteStatement __preparedStmtOfApagarTudo;

  public AnimeDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAnime = new EntityInsertionAdapter<Anime>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Anime`(`id`,`nome`,`genero`,`ano`,`estudio`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Anime value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getGenero() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGenero());
        }
        stmt.bindLong(4, value.getAno());
        if (value.getEstudio() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEstudio());
        }
      }
    };
    this.__deletionAdapterOfAnime = new EntityDeletionOrUpdateAdapter<Anime>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Anime` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Anime value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfApagarTudo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM anime";
        return _query;
      }
    };
  }

  @Override
  public void insert(Anime... anime) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAnime.insert(anime);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Anime... anime) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfAnime.handleMultiple(anime);
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
  public List<Anime> getAnimes() {
    final String _sql = "SELECT * FROM anime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfGenero = _cursor.getColumnIndexOrThrow("genero");
      final int _cursorIndexOfAno = _cursor.getColumnIndexOrThrow("ano");
      final int _cursorIndexOfEstudio = _cursor.getColumnIndexOrThrow("estudio");
      final List<Anime> _result = new ArrayList<Anime>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Anime _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpGenero;
        _tmpGenero = _cursor.getString(_cursorIndexOfGenero);
        final int _tmpAno;
        _tmpAno = _cursor.getInt(_cursorIndexOfAno);
        final String _tmpEstudio;
        _tmpEstudio = _cursor.getString(_cursorIndexOfEstudio);
        _item = new Anime(_tmpNome,_tmpGenero,_tmpAno,_tmpEstudio);
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
}
