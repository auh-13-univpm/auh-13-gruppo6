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
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;
import com.univpm1.firenzestreests.util.AddressToCoords;

public class IndirizzoSource {
	
	
	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	private String[] allColumns = { "id_via","nome","latitudine","longitudine" };

	Context cont;
	public IndirizzoSource(Context context) {
		cont=context;
		dbHelper = new DaoHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	
	public void loadIndirizzo() throws FileNotFoundException {
		
	    FileReader fileReader = new FileReader(Environment.getExternalStorageDirectory().toString()
				+"/FirenzeStreets/database/sinistri.csv");
        BufferedReader bufferReader = new BufferedReader(fileReader);
        int i=0;
	    String nextLine;
	    open();
	    DaoHelper dao=new DaoHelper(cont);
	    dao.onUpgrade(database,database.getVersion(), database.getVersion()+1);
	    ContentValues values = new ContentValues();
	    try{
	    	database.beginTransaction();
	    	while ((nextLine = bufferReader.readLine()) != null) {
	    		if(i>0){
		    		
		            String[] subArray = nextLine.split(",");
		            values.put("nome",  subArray[0]);
		            values.put("latitudine", new AddressToCoords(subArray[0],cont).getLatitude());
		            values.put("longitudine",  new AddressToCoords(subArray[0],cont).getLongitude());
		            
		            database.insert("indirizzo", null,values);
	            }
	    		i++;
	    	}
	        database.setTransactionSuccessful();
	        database.endTransaction();
		    close();
			bufferReader.close();
	    }catch(Exception e){
	    	
	    }  
	}
	

	public Indirizzo getViaByName(String nome) {

		Indirizzo ind=new Indirizzo();
		String[] name= new String[] {nome};
		open();
		Cursor cursor = database.query("indirizzo", allColumns, "nome = ?",
				name, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ind= cursorToIndirizzo(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		close();
		return ind;
	}
	
	public ArrayList<Indirizzo> fetchAllIndirizzi() {
		open();
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
		close();
		return indirizzi;
		
	}
	public Indirizzo fetchIndirizzoById(String[] idVia) {
		open();
		Cursor cursor=database.query("danno", allColumns, "id_via = ?", idVia, null, null, null);

		cursor.moveToFirst();
		Indirizzo via = null;
		while (!cursor.isAfterLast()) {
			via = cursorToIndirizzo(cursor);
		}
		cursor.close();
		close();
		return via;
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