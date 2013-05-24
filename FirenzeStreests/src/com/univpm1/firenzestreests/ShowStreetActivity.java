package com.univpm1.firenzestreests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osmdroid.views.MapView.LayoutParams;

import com.univpm1.firenzestreests.dao.DannoSource;
import com.univpm1.firenzestreests.dao.IndirizzoSource;
import com.univpm1.firenzestreests.dao.SinistroSource;
import com.univpm1.firenzestreests.entities.Danno;
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
		Context thisContext = this.getApplicationContext();
		if (extras != null) {
			idIndirizzo = extras
					.getString("com.univpm1.firenzestreests.ID_INDIRIZZO");
		}
		if (idIndirizzo == null) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(intent);
			Toast toast = Toast.makeText(thisContext,
					getResources().getString(R.string.erroreIdIndirizzo),
					Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		String[] _idArr = new String[] { idIndirizzo };

		DannoSource danniDb = new DannoSource(thisContext);
		ArrayList<Danno> danni;
		danniDb.open();
		danni = danniDb.getDannoByVia(_idArr);
		danniDb.close();
		SinistroSource sinistriDb = new SinistroSource(thisContext);
		ArrayList<Sinistro> sinistri;
		sinistriDb.open();
		sinistri = sinistriDb.getSinistroByVia(_idArr);
		sinistriDb.close();

		IndirizzoSource indirizziDB = new IndirizzoSource(thisContext);
		indirizziDB.open();
		Indirizzo via = indirizziDB.fetchIndirizzoById(_idArr);
		indirizziDB.close();

		TextView title = (TextView) findViewById(R.id.titleStreet);
		title.setText(via.getNome());

		TableLayout tab = (TableLayout) findViewById(R.id.table1);

		// TODO scrivere la classe per ordinare in base all'anno.
		// Collections.sort(sinistri)

		for (Sinistro sinistro : sinistri) {
			TableRow tbrow = new TableRow(thisContext);

			LinearLayout yearTD = new LinearLayout(thisContext);
			LinearLayout nCrashTD = new LinearLayout(thisContext);
			TextView yearsTxt = new TextView(thisContext);
			TextView nCrashTxt = new TextView(thisContext);
			//===== Create Style
	
			
			
			//===== Append Data
			yearsTxt.setText(sinistro.getAnno());
			nCrashTxt.setText(sinistro.getNumero());
			yearTD.addView(yearsTxt);
			nCrashTD.addView(nCrashTxt);
			tab.addView(tbrow);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_street, menu);

		return true;
	}
}
