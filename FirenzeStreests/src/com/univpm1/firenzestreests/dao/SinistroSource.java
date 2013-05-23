package com.univpm1.firenzestreests.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.univpm1.firenzestreests.entities.Sinistro;

public class SinistroSource {
	// Database fields
	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	private String[] allColumns = { "id_sinistro","anno","numero","id_via_Fk"};

	public SinistroSource(Context context) {
		dbHelper = new DaoHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertSinistro(Sinistro newSinistro) {
		ContentValues values = new ContentValues();
		database.insert("sinistro", null, values);
	}

	public List<Sinistro> fatchAllIndirizzi() {
		List<Sinistro> sinistri = new ArrayList<Sinistro>();

		Cursor cursor = database.query("indirizzo", allColumns, null, null,
				null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Sinistro sinistro = cursorToSinistro(cursor);
			sinistri.add(sinistro);
			cursor.moveToNext();
		}
		cursor.close();
		return sinistri;
	}

	public List<Sinistro> getSinistroByVia(String[] id_via) {
		List<Sinistro> sinistri = new ArrayList<Sinistro>();

		Cursor cursor = database.rawQuery("select * from sinistro where id_via_Fk = ", id_via);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Sinistro sinistro = cursorToSinistro(cursor);
			sinistri.add(sinistro);
			cursor.moveToNext();
		}
		cursor.close();
		return sinistri;
	}
	
	
	private Sinistro cursorToSinistro(Cursor cursor) {
		Sinistro sinistro = new Sinistro();
		
		return sinistro;
	}
}
