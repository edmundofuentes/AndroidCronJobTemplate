/***
  Copyright (c) 2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Android Development_
    http://commonsware.com/Android
    
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  
  Forked by Edmundo Fuentes
    http://www.edmundofuentes.com
    Feb 2014
    
 */

package com.edmundofuentes.notifier;

import com.edmundofuentes.notifier.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class ScheduledServiceNotifier extends Activity {
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Check if a configuration file exists, if not, run SetupActivity
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
    if(settings.contains("code") && settings.contains("key") && settings.contains("host") )
    {
    	// Continue with AlarmSchedule
    	PollReceiver.scheduleAlarms(this);

        Toast.makeText(this, R.string.alarms_scheduled, Toast.LENGTH_LONG)
             .show();
        
    } else {
    	// Run Setup activity
    	Intent intent = new Intent(this, SetupActivity.class);
    	startActivity(intent);
    }
    // end this activity regardless of the outcome
    finish();
  }
}