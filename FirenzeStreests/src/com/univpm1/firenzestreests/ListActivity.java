package com.univpm1.firenzestreests;

import java.util.ArrayList;

import com.univpm1.firenzestreests.entities.Indirizzo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		Context appContext = this.getApplicationContext();

		Bundle extras = getIntent().getExtras();
		ArrayList<Indirizzo> indirizzi = new ArrayList<Indirizzo>();
		if (extras != null) {
			indirizzi = (ArrayList<Indirizzo>) extras.getSerializable("com.univpm1.firenzestreests.VIEW_LIST_COORDS");
		}

		class LocalListner implements View.OnClickListener {
			private int id;

			public LocalListner(int arg) {
				this.id = arg;
			}

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						ShowStreetActivity.class);

				intent.putExtra("com.univpm1.firenzestreests.ID_INDIRIZZO",
						Integer.toString(id));
				startActivity(intent);
			}
		}

		for (Indirizzo address : indirizzi) {
			Button currentText = new Button(appContext);
			currentText.setText(address.getNome());

			currentText.setOnClickListener(new LocalListner(address.getId()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list, menu);
		return true;
	}

}
