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

	public void insertIndirizzo(Indirizzo newIndirizzo) {
		ContentValues values = new ContentValues();
		values.put("nome", newIndirizzo.getNome());
		values.put("longitudine", newIndirizzo.getLongitudine());
		values.put("latitudine", newIndirizzo.getLatitudine());
		database.insert("indirizzo", null, values);
	}
	
	
	public void loadIndirizzo() throws FileNotFoundException {
		//CSVReader reader = new CSVReader(new FileReader(Environment.getExternalStorageDirectory().toString()
				//+"/FirenzeStreets/database/sinistri.csv"));
	    FileReader fileReader = new FileReader(Environment.getExternalStorageDirectory().toString()
				+"/FirenzeStreets/database/sinistri.csv");
        BufferedReader bufferReader = new BufferedReader(fileReader);
        
     //   String l = "";
        AddressToCoords adToCord;
        String tableName ="indirizzo";
        String columns = "nome,latitudine,longitudine";
        String InsertString1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String InsertString2 = ");";
        int i=0;
	    String nextLine;
	    try{
	    	database.beginTransaction();
	    	while ((nextLine = bufferReader.readLine()) != null) {
	    		if(i==0){
	    			
	    		} 
	    		StringBuilder stringBuildered = new StringBuilder(InsertString1);
	            String[] subArray = nextLine.split(",");
	            stringBuildered.append("'" + subArray[0] + "',");
	            stringBuildered.append(new AddressToCoords(subArray[0],cont).getLongitude());
	            stringBuildered.append(new AddressToCoords(subArray[0],cont).getLatitude());
	            stringBuildered.append(InsertString2);
	            database.execSQL(stringBuildered.toString());
	    		i++;
	    	}
	        database.setTransactionSuccessful();
	        database.endTransaction();
		    
	    }catch(Exception e){
	    	
	    }  
		/*ContentValues values = new ContentValues();
		values.put("nome", newIndirizzo.getNome());
		values.put("longitudine", newIndirizzo.getLongitudine());
		values.put("latitudine", newIndirizzo.getLatitudine());
		database.insert("indirizzo", null, values);*/
	}
	

	public ArrayList<Indirizzo> fetchAllIndirizzi() {
		
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
	public Indirizzo fetchIndirizzoById(String[] idVia) {
		
		Cursor cursor=database.query("danno", allColumns, "id_via = ?", idVia, null, null, null);

		cursor.moveToFirst();
		Indirizzo via = null;
		while (!cursor.isAfterLast()) {
			via = cursorToIndirizzo(cursor);
		}
		cursor.close();
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