package com.univpm1.firenzestreests.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.univpm1.firenzestreests.entities.Danno;

public class DannoSource {
	// Database fields
	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	private String[] allColumns = {"id_danno","id_via","lesioni","contusi","morti" };

	public DannoSource(Context context) {
		dbHelper = new DaoHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertDanno(Danno newDanno) {
		ContentValues values = new ContentValues();
		
		values.put("id_via",  newDanno.getIdVia());
		values.put("lesioni",  newDanno.getLesioni());
		values.put("contusi",  newDanno.getMorti());
		
		database.insert("danno", null, values);

	}

	public List<Danno> fatchAllDanni() {
		List<Danno> danni = new ArrayList<Danno>();

		Cursor cursor = database.query("danno", allColumns, null, null,
				null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Danno danno = cursorToDanno(cursor);
			danni.add(danno);
			cursor.moveToNext();
		}
		cursor.close();
		return danni;
	}

	private Danno cursorToDanno(Cursor cursor) {
		Danno danno = new Danno();

		danno.setIdVia(cursor.getInt(1));
		danno.setContusi(cursor.getInt(2));
		danno.setMorti(cursor.getInt(3));
		
		return danno;
	}
}
