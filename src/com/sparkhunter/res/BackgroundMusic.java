package com.sparkhunter.res;

import com.sparkhunter.main.R;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.media.MediaPlayer;

public class BackgroundMusic extends Service implements MediaPlayer.OnPreparedListener {
	//TODO figure out how to make this asynchronous (but hopefully not multithreaded)
	//prepareAsync() with MediaPlayer ought to do the trick
	
	//This is only for background music, a sound pool manager will be used for sound effects
	private MediaPlayer bgMusic;

	public BackgroundMusic() {
		super();
	}

	@Override
	public void onPrepared(MediaPlayer player) {
		Log.d("DEBUG", "Starting playback...");
		player.start();
	}
	
	@Override
	public void onCreate() {
		Log.d("DEBUG", "BackgroundMusic onCreate() called");
	}
	
	@Override
	public int onStartCommand(Intent bgmSelector, int flags, int startID) {
		//state logic needs to go here!
		//check if a mediaplayer is actually playing
		//stop if so, and play new thing
		//stuff should loop at end!
		int songId = bgmSelector.getIntExtra(Integer.toString(R.string.music_id), 0);
		
		if(songId == R.raw.squee)
			Log.d("DEBUG", "it is squee");
		
		bgMusic = MediaPlayer.create(BackgroundMusic.this, songId);
		bgMusic.start();
		
		return 0;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
