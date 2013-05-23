package com.univpm1.firenzestreests.dao;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.univpm1.firenzestreests.entities.Sinistro;

public class SinistroSource {

	
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

	public ArrayList<Sinistro> fatchAllIndirizzi() {
		
		ArrayList<Sinistro> sinistri = new ArrayList<Sinistro>();

		Cursor cursor = database.query("sinistro", allColumns, null, null,null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Sinistro sinistro = cursorToSinistro(cursor);
			sinistri.add(sinistro);
			cursor.moveToNext();
		}
		cursor.close();
		return sinistri;
	}

	public ArrayList<Sinistro> getSinistroByVia(String[] id_via) {
		
		ArrayList<Sinistro> sinistri = new ArrayList<Sinistro>();

        //query("sinistro", allColumns, "id_via_Fk = ?", id_via, groupBy, having, orderBy,)
		Cursor cursor=database.query("sinistro", allColumns, "id_via_Fk = ?", id_via, null, null, null);
		//.rawQuery("select * from sinistro where id_via_Fk = ", id_via);

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
		sinistro.setIdSinistro(cursor.getInt(0));
		sinistro.setAnno(cursor.getInt(1));
		sinistro.setNumero(cursor.getInt(2));
		sinistro.setIdVia(cursor.getInt(3));
		return sinistro;
	}
}
