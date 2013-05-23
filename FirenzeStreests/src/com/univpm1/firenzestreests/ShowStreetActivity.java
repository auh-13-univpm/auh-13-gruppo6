package com.univpm1.firenzestreests;

import java.util.ArrayList;
import java.util.List;

import com.univpm1.firenzestreests.dao.DannoSource;
import com.univpm1.firenzestreests.dao.SinistroSource;
import com.univpm1.firenzestreests.entities.Danno;
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class ShowStreetActivity extends Activity {


	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_street);
		String idIndirizzo = null;
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			idIndirizzo = extras.getString("com.univpm1.firenzestreests.ID_INDIRIZZO");
		}
		if(idIndirizzo == null){
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(intent);
			Toast toast = Toast.makeText(getApplicationContext(),getResources().getString(R.string.erroreIdIndirizzo) , Toast.LENGTH_SHORT);
			toast.show();
		}
		DannoSource danniDb = new DannoSource(getApplicationContext());
		ArrayList<Danno> danni;
		danniDb.open();
		danni = danniDb.getDannoByVia(new String[]{idIndirizzo});
		danniDb.close();
		SinistroSource sinistriDb = new SinistroSource(getApplicationContext());
		ArrayList<Sinistro> sinistri;
		sinistriDb.open();
		sinistri = sinistriDb.getSinistroByVia(new String[]{idIndirizzo});
		sinistriDb.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_street, menu);

		return true;
	}
}
