package com.sparkhunter.res;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

//because I'm catastrophically sick of fucking with the Service class
public class AudioManager {
	private static AudioManager instance = new AudioManager();
	private MediaPlayer bgm = null;
	private SoundPool effect = null;
	
	private AudioManager(){}
	
	public AudioManager getInstance(){
		return instance;
	}
	
	public void setBackground(Context c, int resId) throws IllegalStateException{
		//check if bgm exists at the moment
		if(bgm == null){
			//start a MediaPlayer with the specified music
			bgm = createPlayer(c, resId);
			bgm.prepareAsync();
		}
		else{
			//something's already doing something
			if(bgm.isPlaying()){
				//stop current song
				bgm.stop();
			}
			//release current MediaPlayer
			bgm.release();
				
			//and make new one for new song
			bgm = createPlayer(c, resId);
			bgm.prepareAsync();
		}
	}
	
	private MediaPlayer createPlayer(Context c, int resId){
		//creates a custom MediaPlayer with needed callbacks
		MediaPlayer newPlayer = MediaPlayer.create(c, resId);
		
		//set looping, since all background music should loop
		newPlayer.setLooping(true);
		
		//set needed listeners
		newPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
			//starts the player once it's ready, needed since prepareAsync() is used.
			public void onPrepared(MediaPlayer player){
				player.start();
			}
		});
		
		return newPlayer;
	}
	
}
