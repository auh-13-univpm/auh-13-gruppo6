package com.univpm1.firenzestreests.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;

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

	public Sinistro createSinistro(Sinistro newSinistro) {
		ContentValues values = new ContentValues();

		long insertId = database.insert("sinistro", null, values);

		Cursor cursor = database.query("sinistro", allColumns, "id_sinistro = "
				+ insertId, null, null, null, null);
		cursor.moveToFirst();
		Sinistro insetedSinistro = cursorToSinistro(cursor);
		cursor.close();
		return insetedSinistro;
	}

	public void deleteComment(Indirizzo viaToDelete) {
		long id = viaToDelete.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete("indirizzo", "id_via = " + id, null);
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

	private Sinistro cursorToSinistro(Cursor cursor) {
		Sinistro sinistro = new Sinistro();

		return sinistro;
	}
}
