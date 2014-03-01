package com.edmundofuentes.notifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Notifier
{
	// Dependency Injection
	private Context ctx;
	
	private static final String LOG = "Notifier";
	
	// Variables to be loaded from preferences
	private String code, key, host;
	
	public Notifier(Context ctx)
	{
		// Inject Context
		this.ctx = ctx;
		
		// Check preferences file
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.ctx);
		this.code = settings.getString("code", "");
		this.key = settings.getString("key", "");
		this.host = settings.getString("host", "");
	}
	
	/**
	 * Execute the main application procedure
	 */
	public int run()
	{
		Log.d(LOG, "Starting main procedure..");
		
		// Your business logic here
		
		
		return 0;
	}
}