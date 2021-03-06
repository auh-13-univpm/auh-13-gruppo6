package com.univpm1.firenzestreests;

import java.util.ArrayList;


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
		ArrayList<Danno> danni = new ArrayList<Danno>();
		danniDb.open();
		//danni = danniDb.getDannoByVia(_idArr);
		danniDb.close();
		Danno d = new Danno();
		d.setIdVia(Integer.valueOf(idIndirizzo).intValue());
		d.setContusi(3);
		d.setLesioni(34);
		d.setMorti(1);
		danni.add(d);
		SinistroSource sinistriDb = new SinistroSource(thisContext);
		ArrayList<Sinistro> sinistri = new ArrayList<Sinistro>();
		sinistriDb.open();
		//sinistri = sinistriDb.getSinistroByVia(_idArr);
		sinistriDb.close();
		Sinistro s1 = new Sinistro();
		s1.setAnno(2009);
		s1.setNumero(5);
		Sinistro s2 = new Sinistro();
		s2.setAnno(2009);
		s2.setNumero(5);
		Sinistro s3 = new Sinistro();
		s3.setAnno(2009);
		s3.setNumero(5);
		sinistri.add(s1);
		sinistri.add(s2);
		sinistri.add(s3);		
		IndirizzoSource indirizziDB = new IndirizzoSource(thisContext);
		indirizziDB.open();
		//Indirizzo via = indirizziDB.fetchIndirizzoById(_idArr);
		indirizziDB.close();

		TextView title = (TextView) findViewById(R.id.titleStreet);
		title.setText("via dei tigli");//via.getNome());

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

		Danno currDanno = danni.get(0);
		((TextView) findViewById(R.id.valueMorti)).setText(currDanno.getMorti());
		((TextView) findViewById(R.id.valueLesioni)).setText(currDanno.getLesioni());
		((TextView) findViewById(R.id.valueContusi)).setText(currDanno.getContusi());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_street, menu);

		return true;
	}
}
