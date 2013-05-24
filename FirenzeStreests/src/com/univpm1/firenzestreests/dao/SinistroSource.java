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

<<<<<<< HEAD
import com.univpm1.firenzestreests.entities.Indirizzo;
=======
import com.univpm1.firenzestreests.entities.Danno;
>>>>>>> 5639f187311afbb59fd95ddee7a286f104b88981
import com.univpm1.firenzestreests.entities.Sinistro;
import com.univpm1.firenzestreests.util.AddressToCoords;

public class SinistroSource {

	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	private String[] allColumns = { "id_sinistro", "anno", "numero",
			"id_via_Fk" };
	private Context cont;

	public SinistroSource(Context context) {
		dbHelper = new DaoHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
<<<<<<< HEAD

	public ArrayList<String> fetchAllAnno() {
		open();
=======
	public ArrayList<Integer>getVieByNumberof(int anno, boolean moreThan, int howMany){
		ArrayList<Integer> sinistri = new ArrayList<Integer>();
		String filterComparer = moreThan?" > ?":" < ?"; 
		String filterList= Integer.valueOf(anno).toString();
		String howMuch = Integer.valueOf(howMany).toString();
		Cursor cursor=database.query("danno", allColumns, "anno = ? AND numero "+filterComparer+" ? " , new String[]{filterList,howMuch}, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Sinistro sinistro = cursorToSinistro(cursor);
			sinistri.add(sinistro.getIdVia());
			cursor.moveToNext();
		}
		cursor.close();
		return sinistri;
	}
	public ArrayList<String> fetchAllAnno(){
>>>>>>> 5639f187311afbb59fd95ddee7a286f104b88981
		ArrayList<String> anni = new ArrayList<String>();
		Cursor cursor = database.query("sinistro", new String[] { "anno" },
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			anni.add("Sinistri " + Integer.valueOf(cursor.getInt(0)).toString());
			cursor.moveToNext();
		}
		cursor.close();
		close();
		return anni;
	}
	public void loadSinistro() throws FileNotFoundException {

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
					values.put("anno",  2009);
					values.put("numero", subArray[1] );
					database.insert("danno", null,values);
					
					values.put("id_via_Fk",  idVia);
					values.put("anno",  2010);
					values.put("numero", subArray[2] );
					database.insert("danno", null,values);
					
					values.put("id_via_Fk",  idVia);
					values.put("anno",  2011);
					values.put("numero", subArray[3] );
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

	public ArrayList<Sinistro> fatchAllIndirizzi() {
		open();
		ArrayList<Sinistro> sinistri = new ArrayList<Sinistro>();

		Cursor cursor = database.query("sinistro", allColumns, null, null,
				null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Sinistro sinistro = cursorToSinistro(cursor);
			sinistri.add(sinistro);
			cursor.moveToNext();
		}
		cursor.close();
		close();
		return sinistri;
	}

	public ArrayList<Sinistro> getSinistroByVia(String[] id_via) {
		open();
		ArrayList<Sinistro> sinistri = new ArrayList<Sinistro>();

		// query("sinistro", allColumns, "id_via_Fk = ?", id_via, groupBy,
		// having, orderBy,)
		Cursor cursor = database.query("sinistro", allColumns, "id_via_Fk = ?",
				id_via, null, null, null);
		// .rawQuery("select * from sinistro where id_via_Fk = ", id_via);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Sinistro sinistro = cursorToSinistro(cursor);
			sinistri.add(sinistro);
			cursor.moveToNext();
		}
		cursor.close();
		close();
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
