package com.univpm1.firenzestreests;

import java.util.List;

import com.univpm1.firenzestreests.dao.SinistroSource;
import com.univpm1.firenzestreests.entities.Indirizzo;
import com.univpm1.firenzestreests.entities.Sinistro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ShowStreetActivity extends Activity {

	protected Indirizzo _refID = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_street);
		String idIndirizzo = null;
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			idIndirizzo = extras.getString("com.univpm1.firenzestreests.ID_INDIRIZZO");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_street, menu);
		
		Intent arg;
		List<Sinistro> refCrash;
		try {
			arg = this.getIntent();
			_refID = (Indirizzo) arg.getSerializableExtra("Indirizzo");
			
			refCrash = null;// TODO:  new SinistroSource(getApplicationContext()).
			
		} catch (Exception e) {
			/* Non è stato passato un Intent contenente 
			 * il dato "Indirizzo" necessario per la compilazione della pagina.
			 * Redirect verso MainActivity. 
			 */
			arg = new Intent(this, MainActivity.class);
			arg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(arg);
		}
		
		/*for (Sinistro currentCrash : refCrash) {
			
		}*/

		return true;
	}
}
