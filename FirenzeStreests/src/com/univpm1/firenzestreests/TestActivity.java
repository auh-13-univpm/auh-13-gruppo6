package com.univpm1.firenzestreests;

import java.util.ArrayList;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

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
				break;
			case R.id.list:
				ArrayList<Indirizzo> values = datasource.fetchAllIndirizzi();
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
