package com.sparkhunter.res;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.media.MediaPlayer;

public class BackgroundMusic extends Service implements MediaPlayer.OnPreparedListener {
	//TODO figure out how to make this asynchronous (but hopefully not multithreaded)
	//prepareAsync() with MediaPlayer ought to do the trick
	MediaPlayer bgMusic = null;

	public BackgroundMusic() {
		super();
		Log.d("DEBUG", "constructor called, for some reason");
	}

	@Override
	public void onPrepared(MediaPlayer player) {
		player.start();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		//uhhh... dunno what do
	}
	
	@Override
	public int onStartCommand(Intent bgmSelector, int flags, int startID) {
		//magic things go here
		
		return 0;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
