package com.univpm1.firenzestreests.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.univpm1.firenzestreests.entities.Indirizzo;

public class IndirizzoSource {
	// Database fields
	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	private String[] allColumns = { "id_via", "nome", "latitudine",
			"longitudine" };

	public IndirizzoSource(Context context) {
		dbHelper = new DaoHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertIndirizzo(Indirizzo newIndirizzo) {
		ContentValues values = new ContentValues();
		values.put("nome", newIndirizzo.getNome());
		values.put("longitudine", newIndirizzo.getLongitudine());
		values.put("latitudine", newIndirizzo.getLatitudine());

		//long insertId = 
		database.insert("indirizzo", null, values);

		/*
		 * Cursor cursor = database.query("indirizzo", allColumns,"id_via = " +
		 * insertId, null, null, null, null); cursor.moveToFirst(); Indirizzo
		 * insetedIndirizzo = cursorToIndirizzo(cursor); cursor.close();
		 */
	}

	public void deleteComment(Indirizzo viaToDelete) {
		long id = viaToDelete.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete("indirizzo", "id_via = " + id, null);
	}

	public ArrayList<Indirizzo> fatchAllIndirizzi() {
		ArrayList<Indirizzo> indirizzi = new ArrayList<Indirizzo>();

		Cursor cursor = database.query("indirizzo", allColumns, null, null,
				null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Indirizzo indirizzo = cursorToIndirizzo(cursor);
			indirizzi.add(indirizzo);
			cursor.moveToNext();
		}

		cursor.close();
		return indirizzi;
	}
	

	private Indirizzo cursorToIndirizzo(Cursor cursor) {
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setId(cursor.getInt(0));
		indirizzo.setNome(cursor.getString(1));
		indirizzo.setLatitudine(cursor.getString(2));
		indirizzo.setLongitudine(cursor.getString(3));
		return indirizzo;
	}
}