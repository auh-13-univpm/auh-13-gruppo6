package com.univpm1.firenzestreests;

import java.util.ArrayList;

import com.univpm1.firenzestreests.dao.IndirizzoSource;
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.util.ArrayListWrapper;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onClick(View view) {
		IndirizzoSource indirizziDb = new IndirizzoSource(getApplicationContext());
		ArrayList<Indirizzo> indirizzi;
		indirizziDb.open();
		indirizzi = indirizziDb.fetchAllIndirizzi();
		indirizziDb.close();
		//ArrayListWrapper<Indirizzo> wrapper = new ArrayListWrapper<Indirizzo>(indirizzi);
		//Bundle b = new Bundle();
		//b.putSerializable("com.univpm1.firenzestreests.VIEW_MAP_COORDS", wrapper);
	    Intent intent = new Intent(this, MapActivity.class);
	    intent.putExtra("com.univpm1.firenzestreests.VIEW_MAP_COORDS",indirizzi);
	    startActivity(intent);
	}

	public void lista(View view) {
		IndirizzoSource indirizziDb = new IndirizzoSource(getApplicationContext());
		ArrayList<Indirizzo> indirizzi;
		indirizziDb.open();
		indirizzi = indirizziDb.fetchAllIndirizzi();
		indirizziDb.close();
		//ArrayListWrapper<Indirizzo> wrapper = new ArrayListWrapper<Indirizzo>(indirizzi);
		//Bundle b = new Bundle();
		//b.putSerializable("com.univpm1.firenzestreests.VIEW_MAP_COORDS", wrapper);
	    Intent intent = new Intent(this, ListActivity.class);
	    intent.putExtra("com.univpm1.firenzestreests.VIEW_LIST_COORDS",indirizzi);
	    startActivity(intent);
	}
	public void dbTest(View View){
		 Intent intent = new Intent(this, TestActivity.class);
		 startActivity(intent);
	}
	public void settings(View View){
		 Intent intent = new Intent(this, SettingActivity.class);
		 startActivity(intent);
	}
	public void Filter(View view){
		Intent intent = new Intent(this, FilterActivity.class);
		 startActivity(intent);		
	}
}
