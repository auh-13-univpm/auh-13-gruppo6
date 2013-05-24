package com.univpm1.firenzestreests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.univpm1.firenzestreests.dao.DannoSource;
import com.univpm1.firenzestreests.dao.IndirizzoSource;
import com.univpm1.firenzestreests.dao.SinistroSource;
import com.univpm1.firenzestreests.entities.Danno;
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ShowStreetActivity extends Activity {

	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_street);
		String idIndirizzo = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			idIndirizzo = extras
					.getString("com.univpm1.firenzestreests.ID_INDIRIZZO");
		}
		if (idIndirizzo == null) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(intent);
			Toast toast = Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.erroreIdIndirizzo),
					Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		String[] _idArr = new String[] { idIndirizzo };

		DannoSource danniDb = new DannoSource(getApplicationContext());
		ArrayList<Danno> danni;
		danniDb.open();
		danni = danniDb.getDannoByVia(_idArr);
		danniDb.close();
		SinistroSource sinistriDb = new SinistroSource(getApplicationContext());
		ArrayList<Sinistro> sinistri;
		sinistriDb.open();
		sinistri = sinistriDb.getSinistroByVia(_idArr);
		sinistriDb.close();

		IndirizzoSource indirizziDB = new IndirizzoSource(
				getApplicationContext());
		indirizziDB.open();
		Indirizzo via = indirizziDB
				.fetchIndirizzoById(_idArr);
		indirizziDB.close();

		TextView title = (TextView) findViewById(R.id.titleStreet);
		title.setText(via.getNome());

		TableLayout tab = (TableLayout) findViewById(R.id.table1);
		
		// TODO scrivere la classe per ordinare in base all'anno.
		//Collections.sort(sinistri)
		
		for (Sinistro sinistro : sinistri) {
			TableRow tbrow = new TableRow(this);
			LinearLayout year = new LinearLayout(this);
			LinearLayout nCrash = new LinearLayout(this);
			
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_street, menu);

		return true;
	}
}
