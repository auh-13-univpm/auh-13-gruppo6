package com.univpm1.firenzestreests.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DaoHelper extends SQLiteOpenHelper{
	
	  private static final String DATABASE_NAME = "sinistri.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "CREATE TABLE  " +
	  		"indirizzo (id_via integer primary key autoincrement," +
	  		"nome varchar(60) not null," +
	  		"latidudine varchar(15)," +
	  		"longitudine varchar(15));" +
	  		" CREATE TABLE sinistro(id_sinistro integer primary key autoincrement, " +
	  		"id_via_Fk integer," +
	  		"FOREIGN KEY(id_via_Fk) REFERENCES via(id_via)," +
	  		"anno integer not null, " +
	  		"numero intger);" +
	  		" CREATE TABLE danno(id_danno integer primary key autoincrement," +
	  		" id_via_Fk integer," +
	  		"FOREIGN KEY(id_via_Fk) REFERENCES via(id_via)," +
	  		" lesioni integer," +
	  		" contusi integer," +
	  		" morti integer);";

	  public DaoHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
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
