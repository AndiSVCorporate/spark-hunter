package com.sparkhunter.res;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class BackgroundMusic extends IntentService {
	//TODO figure out how to make this asynchronous (but hopefully not multithreaded)
	//TODO set up a content provider for the sounds

	public BackgroundMusic(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

	}
	
	public IBinder onBind() {
		
	}

}
