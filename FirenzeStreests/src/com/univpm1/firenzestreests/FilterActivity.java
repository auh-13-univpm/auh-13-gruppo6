package com.univpm1.firenzestreests;

import java.util.ArrayList;

import com.univpm1.firenzestreests.dao.DannoSource;
import com.univpm1.firenzestreests.dao.IndirizzoSource;
import com.univpm1.firenzestreests.dao.SinistroSource;
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;

public class FilterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		// Show the Up button in the action bar.
		setupActionBar();
		Spinner spinner = (Spinner) findViewById(R.id.filterSpinner);
		SinistroSource sinistriDb = new SinistroSource(getApplicationContext());
		ArrayList<String> anni;
		sinistriDb.open();
		anni = sinistriDb.fetchAllAnno();
		sinistriDb.close();
		anni.add("Morti");
		anni.add("Contusi");
		anni.add("Lesioni");
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, anni);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}
	public void show(View view){
		IndirizzoSource indirizziDb = new IndirizzoSource(getApplicationContext());
		String filtro = ((Spinner) findViewById(R.id.filterSpinner)).getSelectedItem().toString();
		boolean isMaggiore = ((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString().equals(getResources().getStringArray(R.array.compareArray)[0]);
		ArrayList<Indirizzo> indirizzi = new ArrayList<Indirizzo>();
		int howMany = Integer.valueOf(((EditText) findViewById(R.id.editText1)).getText().toString()).intValue();
				
		ArrayList<Integer> idVie = new ArrayList<Integer>();
		if(filtro.equals("Morti") || filtro.equals("Contusi") || filtro.equals("Lesioni"))
		{
			DannoSource ds = new DannoSource(getApplicationContext());
			ds.open();
			idVie = ds.getVieByNumberof(filtro, isMaggiore, howMany);
			ds.close();
		}else if(filtro.substring(0, 9).equals("Sinistri ")){
			int anno = Integer.valueOf(filtro.substring(9)).intValue();
			SinistroSource ss = new SinistroSource(getApplicationContext());
			ss.open();
			idVie = ss.getVieByNumberof(anno, isMaggiore, howMany);
			ss.close();
		}else{
			return;
		}
		indirizziDb.open();
		for(int i=0; i<idVie.size(); i++){
		indirizzi.add(indirizziDb.fetchIndirizzoById(new String[]{Integer.valueOf(idVie.get(i)).toString()}));
		}
		indirizziDb.close();
		Intent intent = new Intent(this, MapActivity.class);
	    intent.putExtra("com.univpm1.firenzestreests.VIEW_MAP_COORDS",indirizzi);
	    startActivity(intent);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
