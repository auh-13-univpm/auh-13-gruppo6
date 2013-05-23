package com.univpm1.firenzestreests;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends Activity {

	// button to show progress dialog
    Button btnShowProgress;
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
 
    // File url to download
    private static String file_url = "http://sr-vm091-opend.comune.fi.it:2020/sparql?query=PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2Fsr-vm091-opend.comune.fi.it%3A2020%2Fresource%2F%3E%0D%0APREFIX+d2r%3A+%3Chttp%3A%2F%2Fsites.wiwiss.fu-berlin.de%2Fsuhl%2Fbizer%2Fd2r-server%2Fconfig.rdf%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+map%3A+%3Cfile%3A%2Fusr%2Flocal%2Flib%2Fd2r-server-0.7%2Fmapping3.n3%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+vocab%3A+%3Chttp%3A%2F%2Fsr-vm091-opend.comune.fi.it%3A2020%2Fresource%2Fvocab%2F%3E%0D%0APREFIX+data%3A+%3Chttp%3A%2F%2Fsr-vm091-opend.comune.fi.it%3A2020%2Fresource%2Fdata%2F%3E%0D%0APREFIX+pa%3A+%3Chttp%3A%2F%2Foldsites.comune.fi.it%3A2020%2Fresource%2Fvocab%2F%3E%0D%0ASELECT+DISTINCT+%3Finstance%0D%0AWHERE+{+%3Finstance+a+%3Chttp%3A%2F%2Fsr-vm091-opend.comune.fi.it%3A2020%2Fresource%2Fdata%2Fsinistri%3E+}%0D%0AORDER+BY+%3Finstance&output=json";
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		// show progress bar button
        btnShowProgress = (Button) findViewById(R.id.button1);
 
        
        
	}
	
	/**
	 * Showing Dialog
	 * */
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case progress_bar_type:
	        pDialog = new ProgressDialog(this);
	        pDialog.setMessage("Download file. Attendere prego...");
	        pDialog.setIndeterminate(false);
	        pDialog.setMax(100);
	        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        pDialog.setCancelable(true);
	        pDialog.show();
	        return pDialog;
	    default:
	        return null;
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	public void onClick(View view){

                // starting new Async Task
                new DownloadFile().execute(file_url);
	}
	
	
	class DownloadFile  extends AsyncTask<String, String, String>{

		
		 /**
	     * Before starting background thread
	     * Show Progress Bar Dialog
	     * */
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        showDialog(progress_bar_type);
	    }
	    
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		

	}
}
