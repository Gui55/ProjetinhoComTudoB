package com.example.supermerganimeroom;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AnimeDatabase_Impl extends AnimeDatabase {
  private volatile AnimeDAO _animeDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Anime` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `genero` TEXT, `ano` INTEGER NOT NULL, `estudio` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b5215fbefd8fefc08fa17778d61ba407\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Anime`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAnime = new HashMap<String, TableInfo.Column>(5);
        _columnsAnime.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsAnime.put("nome", new TableInfo.Column("nome", "TEXT", false, 0));
        _columnsAnime.put("genero", new TableInfo.Column("genero", "TEXT", false, 0));
        _columnsAnime.put("ano", new TableInfo.Column("ano", "INTEGER", true, 0));
        _columnsAnime.put("estudio", new TableInfo.Column("estudio", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnime = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAnime = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAnime = new TableInfo("Anime", _columnsAnime, _foreignKeysAnime, _indicesAnime);
        final TableInfo _existingAnime = TableInfo.read(_db, "Anime");
        if (! _infoAnime.equals(_existingAnime)) {
          throw new IllegalStateException("Migration didn't properly handle Anime(com.example.supermerganimeroom.Anime).\n"
                  + " Expected:\n" + _infoAnime + "\n"
                  + " Found:\n" + _existingAnime);
        }
      }
    }, "b5215fbefd8fefc08fa17778d61ba407", "0888317883f60cc6a58fa79b550f85be");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Anime");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Anime`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public AnimeDAO animeDAO() {
    if (_animeDAO != null) {
      return _animeDAO;
    } else {
      synchronized(this) {
        if(_animeDAO == null) {
          _animeDAO = new AnimeDAO_Impl(this);
        }
        return _animeDAO;
      }
    }
  }
}
