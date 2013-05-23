package com.univpm1.firenzestreests;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.univpm1.firenzestreests.dao.IndirizzoSource;
import com.univpm1.firenzestreests.entities.Indirizzo;

public class TestActivity extends ListActivity {

	private IndirizzoSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		getIntent();
		datasource = new IndirizzoSource(this);
		datasource.open();

		// ArrayList<Indirizzo> values = datasource.fatchAllIndirizzi();
		/* Iterator<Indirizzo> iterator=values.iterator();
		 
		 while(iterator.hasNext()){
			 System.out.println(iterator.next().getId());
		 }*/
		 
		 //ArrayAdapter<Indirizzo> adapter = new ArrayAdapter<Indirizzo>(this,android.R.layout.simple_list_item_1,values);
		 //setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Indirizzo> adapter = (ArrayAdapter<Indirizzo>) getListAdapter();
		switch (view.getId()) {
			case R.id.add:
				/*Indirizzo indirizzo = new Indirizzo("via uno", "12 ", "12");
				datasource.insertIndirizzo(indirizzo);
				Indirizzo indirizzoDue = new Indirizzo("via uno", "12 ", "12");
				datasource.insertIndirizzo(indirizzoDue);
				Indirizzo indirizzoTre = new Indirizzo("via uno", "12 ", "12");
				datasource.insertIndirizzo(indirizzoTre);*/
				break;
			case R.id.list:
				
				ArrayList<Indirizzo> values = datasource.fetchAllIndirizzi();
				
				ArrayAdapter<Indirizzo> adapter2 = new ArrayAdapter<Indirizzo>(
						this, android.R.layout.simple_list_item_1, values);
				setListAdapter(adapter2);
				
				//Iterator<Indirizzo> iterator = values.iterator();
				/*while (iterator.hasNext()) {
					System.out.println(iterator.next().getId() + " ITERO_ID");
					System.out.println(iterator.next().getNome() + " ITERO_Nome");
				}*/
				break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}