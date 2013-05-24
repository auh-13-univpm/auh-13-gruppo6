package com.univpm1.firenzestreests.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.univpm1.firenzestreests.entities.Danno;
import com.univpm1.firenzestreests.util.AddressToCoords;

public class DannoSource {

	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	private String[] allColumns = { "id_danno", "id_via_Fk", "lesioni", "contusi",
			"morti" };
	private Context cont;

	public DannoSource(Context context) {
		dbHelper = new DaoHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void loadDanno() throws FileNotFoundException {

		FileReader fileReader = new FileReader(Environment
				.getExternalStorageDirectory().toString()
				+ "/FirenzeStreets/database/sinistri.csv");
		BufferedReader bufferReader = new BufferedReader(fileReader);

		int i = 0;
		int idVia;
		String nextLine;
		IndirizzoSource via=new IndirizzoSource(cont);
		ContentValues values = new ContentValues();
		open();
		try {
			database.beginTransaction();
			while ((nextLine = bufferReader.readLine()) != null) {
				if (i > 0) {	
					String[] subArray = nextLine.split(",");
					idVia=via.getViaByName(subArray[0]).getId();
					values.put("id_via_Fk",  idVia);
					values.put("lesioni",  subArray[4]);
					values.put("contusi",  subArray[5]);
					values.put("morti",  subArray[6]);
					database.insert("danno", null,values);
				}
				i++;
			}
			database.setTransactionSuccessful();
			database.endTransaction();
			close();
			bufferReader.close();
		} catch (Exception e) {

		}
	}

	public ArrayList<Danno> fatchAllDanni() {
		open();
		ArrayList<Danno> danni = new ArrayList<Danno>();

		Cursor cursor = database.query("danno", allColumns, null, null, null,
				null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Danno danno = cursorToDanno(cursor);
			danni.add(danno);
			cursor.moveToNext();
		}
		cursor.close();
		close();
		return danni;
	}

	public ArrayList<Danno> getDannoByVia(String[] id_via) {
		open();
		ArrayList<Danno> danno = new ArrayList<Danno>();
		Cursor cursor = database.query("danno", allColumns, "id_via_Fk = ?",
				id_via, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Danno sinistro = cursorToDanno(cursor);
			danno.add(sinistro);
			cursor.moveToNext();
		}
		cursor.close();
		close();
		return danno;
	}

	private Danno cursorToDanno(Cursor cursor) {

		Danno danno = new Danno();
		danno.setIdDanno(cursor.getInt(0));
		danno.setIdVia(cursor.getInt(1));
		danno.setContusi(cursor.getInt(2));
		danno.setMorti(cursor.getInt(3));

		return danno;
	}
}