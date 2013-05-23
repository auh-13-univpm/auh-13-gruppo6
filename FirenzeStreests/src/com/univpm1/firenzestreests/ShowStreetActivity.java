package com.univpm1.firenzestreests;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShowStreetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_street);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_street, menu);
		return true;
	}

}
