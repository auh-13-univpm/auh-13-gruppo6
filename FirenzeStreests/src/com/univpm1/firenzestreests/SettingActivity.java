package com.univpm1.firenzestreests;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import au.com.bytecode.opencsv.CSVReader;

public class SettingActivity extends Activity {

	// button to show progress dialog
	Button btnShowProgress;

	private ProgressDialog pDialog;
	public static final int PROGRESS_BAR_TYPE = 0;
	private static String CSV_URL = "http://opendata.comune.fi.it/materiali/mobilita_sicurezza/sinistri.csv";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		getIntent();

		// show progress bar button
		btnShowProgress = (Button) findViewById(R.id.btnProgressBar);

		btnShowProgress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				new DownloadFileFromURL().execute(CSV_URL);
			}
		});
	}
	public void clickTest(View view) throws FileNotFoundException{
		CSVReader reader = new CSVReader(new FileReader(Environment.getExternalStorageDirectory().toString()
				+"/FirenzeStreets/database/sinistri.csv"));
	    String [] nextLine;
	    try{
	    	while ((nextLine = reader.readNext()) != null) {
		        System.out.println(nextLine[0] + nextLine[1] + "etc...");
		    }
	    }catch(Exception e){
	    	
	    }  
	}
	/**
	 * Showing Dialog
	 * */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_BAR_TYPE:
			pDialog = new ProgressDialog(this);
			pDialog.setMessage("Download del database. Attendere prego...");
			pDialog.setIndeterminate(false);
			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
			pDialog.setCancelable(true);
			pDialog.show();
			return pDialog;
		default:
			return null;
		}
	}

	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(PROGRESS_BAR_TYPE);
		}

		@Override
		protected String doInBackground(String... f_url) {
			int count;
			try {
				URL url = new URL(f_url[0]);
				URLConnection conection = url.openConnection();
				conection.connect();
				int lenghtOfFile = conection.getContentLength();
				InputStream input = new BufferedInputStream(url.openStream(),
						8192);
				File dbDir = new File(Environment.getExternalStorageDirectory()
						+ "/FirenzeStreets/database");
				if (!dbDir.exists()) {
					dbDir.mkdirs();
				}
				OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().toString()
						+"/FirenzeStreets/database/sinistri.csv");
				byte data[] = new byte[1024];
				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}

				output.flush();

				output.close();
				input.close();

			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}

			return null;
		}

		
		protected void onProgressUpdate(String... progress) {
			pDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String file_url) {
			dismissDialog(PROGRESS_BAR_TYPE);
		}

	}
}
