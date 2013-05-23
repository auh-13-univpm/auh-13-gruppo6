package com.univpm1.firenzestreests.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DaoHelper extends SQLiteOpenHelper{
	
	  private static final String DATABASE_NAME = "sinistri.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String TABLE_INDIRIZZO = "CREATE TABLE  " +
	  		"indirizzo (id_via integer primary key autoincrement," +
	  		"nome varchar(60) not null," +
	  		"latitudine varchar(15)," +
	  		"longitudine varchar(15));";
	  private static final String TABLE_SINISTRO =
	  		" CREATE TABLE sinistro(id_sinistro integer primary key autoincrement, " +
	  		"id_via_Fk integer," +
	  		"FOREIGN KEY(id_via_Fk) REFERENCES indirizzo(id_via)," +
	  		"anno integer not null, " +
	  		"numero intger);";
	  private static final String TABLE_DANNO =
	  		" CREATE TABLE danno(id_danno integer primary key autoincrement," +
	  		" id_via_Fk integer," +
	  		"FOREIGN KEY(id_via_Fk) REFERENCES indirizzo(id_via)," +
	  		" lesioni integer," +
	  		" contusi integer," +
	  		" morti integer);";

	  public DaoHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(TABLE_INDIRIZZO);
	    database.execSQL(TABLE_SINISTRO);
	    database.execSQL(TABLE_DANNO);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DaoHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLES IF EXISTS: indirizzo , danno, sinistro " );
	    onCreate(db);
	  }
}
