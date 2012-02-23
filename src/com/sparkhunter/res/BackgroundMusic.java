package com.sparkhunter.res;

import com.sparkhunter.main.R;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.media.MediaPlayer;

public class BackgroundMusic extends Service implements MediaPlayer.OnPreparedListener {
	//TODO figure out how to make this asynchronous (but hopefully not multithreaded)
	//prepareAsync() with MediaPlayer ought to do the trick
	public MediaPlayer bgMusic;

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
		Log.d("DEBUG", "onCreate called");
	}
	
	@Override
	public int onStartCommand(Intent bgmSelector, int flags, int startID) {
		//magic things go here
		//yank selection out of intent, then prep a synchronous mediaplayer
		//needs to be asynchronous, in a big way...
		int songId = bgmSelector.getIntExtra(Integer.toString(R.string.music_id), 0);
		
		if(songId == R.raw.squee)
			Log.d("DEBUG", "it is squee");
		
		bgMusic = MediaPlayer.create(BackgroundMusic.this, songId);
		bgMusic.start();
		
		return 0;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
