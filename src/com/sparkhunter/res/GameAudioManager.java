package com.sparkhunter.res;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

//because I'm catastrophically sick of fucking with the Service class
public class GameAudioManager {
	private static GameAudioManager instance = new GameAudioManager();
	private MediaPlayer bgm = null;
	private SoundPool effects = null;
	private int bgmResource;
	private static final int MAX_SOUND_EFFECTS = 100;
	
	private GameAudioManager(){
		//create the sound effects pool
		effects = new SoundPool(MAX_SOUND_EFFECTS, AudioManager.STREAM_MUSIC, 0);
	}
	
	public static GameAudioManager getInstance(){
		return instance;
	}
	
	public void setBackground(Context c, int resId){
		bgmResource = resId;
		
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
	
	public void pauseBackground(){
		//pauses background music
		try{
			bgm.pause();
		}
		catch(IllegalStateException e){
			Log.d("DEBUG", "Error: Unable to pause bgm.");
		}
	}
	
	public void playBackground(){
		//starts/resumes background music
		try{
			bgm.start();
		}
		catch(IllegalStateException e){
			Log.d("DEBUG", "Error: unable to start/resume bgm.");
		}
	}
	
	public int getBgmResource(){
		//return resId of music currently playing
		return bgmResource;
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
