package com.sparkhunter.res;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackgroundMusic extends IntentService {
	//TODO figure out how to make this asynchronous (but hopefully not multithreaded)
	//prepareAsync() with MediaPlayer ought to do the trick

	public BackgroundMusic(String name) {
		super(name);
		Log.d("DEBUG", "constructor called, for some reason");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

	}
	
	public IBinder onBind() {
		return null;
		
	}

}
