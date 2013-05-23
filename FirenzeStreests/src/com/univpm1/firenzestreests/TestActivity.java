package com.univpm1.firenzestreests;

import java.util.List;
import java.util.Random;

import android.app.Activity;
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
		
		datasource = new IndirizzoSource(this);
	    datasource.open();

	    List<Indirizzo> values = datasource.fatchAllIndirizzi();

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    
	    ArrayAdapter<Indirizzo> adapter = new ArrayAdapter<Indirizzo>(this,android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	// Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<Indirizzo> adapter = (ArrayAdapter<Indirizzo>) getListAdapter();
	    Indirizzo testInd = null;
	    switch (view.getId()) {
	    case R.id.add:
	      Indirizzo indirizzi = new Indirizzo("via prova"," 12 ", "12");
	      //int nextInt = new Random().nextInt(3);
	      // Save the new comment to the database
	      testInd = datasource.createIndirizzo(indirizzi);
	      adapter.add(testInd);
	      break;
	      /*case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	        comment = (Comment) getListAdapter().getItem(0);
	        datasource.deleteComment(comment);
	        adapter.remove(comment);
	      }
	      break;*/
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
