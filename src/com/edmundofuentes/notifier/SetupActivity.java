package com.edmundofuentes.notifier;
import com.edmundofuentes.notifier.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

public class SetupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup, menu);
		return true;
	}
	
	/* Called when the user presses the save button */
	public void saveSettings(View view)
	{
		// Load strings from view 		
		EditText editText_code = (EditText) findViewById(R.id.editText_code);
		String device_code = editText_code.getText().toString();
		
		EditText editText_key = (EditText) findViewById(R.id.editText_key);
		String device_key = editText_key.getText().toString();
		
		EditText editText_host = (EditText) findViewById(R.id.editText_host);
		String device_host = editText_host.getText().toString();
		
		// Check that they are valid
		if( device_code.isEmpty() || device_key.isEmpty() || !URLUtil.isValidUrl(device_host))
		{
			// One of the parameters is not valid.
			Toast.makeText(this, R.string.save_error, Toast.LENGTH_LONG).show();
		} else {
			// Save to preference file
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = settings.edit();
			
			editor.putString("code", device_code);
			editor.putString("key", device_key);
			editor.putString("host", device_host);
			
			editor.commit();
			
			Toast.makeText(this, R.string.save_ok, Toast.LENGTH_SHORT).show();
			
			// Go back to the original Activity
			Intent intent = new Intent(this, ScheduledServiceNotifier.class);
			startActivity(intent);
			
			// end this activity
		    finish();
			
		}
	}

}
