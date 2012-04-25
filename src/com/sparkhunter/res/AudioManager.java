package com.sparkhunter.res;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

//because I'm catastrophically sick of fucking with the Service class
public class AudioManager {
	private static AudioManager instance = new AudioManager();
	private MediaPlayer bgm = null;
	private SoundPool effect = null;
	
	private AudioManager(){}
	
	public static AudioManager getInstance(){
		return instance;
	}
	
	public void setBackground(Context c, int resId) throws IllegalStateException{
		//check if bgm exists at the moment
		if(bgm == null){
			//start a MediaPlayer with the specified music
			Log.d("DEBUG", "bgm is null.");
			bgm = createPlayer(c, resId);
			//bgm.prepareAsync();
			//Log.d("DEBUG", "bgm is being asynchronously prepared.");
			bgm.start();
		}
		else{
			try{
				//something's already doing something
				if(bgm.isPlaying()){
					//stop current song
					bgm.stop();
				}
				//release current MediaPlayer
				bgm.release();
					
				//and make new one for new song
				bgm = createPlayer(c, resId);
				//bgm.prepareAsync();
				bgm.start();
			}
			catch(IllegalStateException e){
				Log.d("DEBUG", "Error: Failed to change bgm.\n" + e.getMessage());
			}
		}
	}
	
	private MediaPlayer createPlayer(Context c, int resId){
		Log.d("DEBUG", "Creating new MediaPlayer.");
		//creates a custom MediaPlayer with needed callbacks
		MediaPlayer newPlayer = MediaPlayer.create(c, resId);
		
		//set looping, since all background music should loop
		newPlayer.setLooping(true);
		
		//set needed listeners
		newPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
			//starts the player once it's ready, needed since prepareAsync() is used.
			public void onPrepared(MediaPlayer player){
				try{
					player.start();
				}
				catch(IllegalStateException e){
					Log.d("DEBUG", "Error: Unable to start background music.\n" + e.getMessage());
				}
			}
		});
		
		return newPlayer;
	}
	
}
