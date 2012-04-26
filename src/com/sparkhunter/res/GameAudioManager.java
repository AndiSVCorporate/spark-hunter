package com.sparkhunter.res;

import java.util.HashMap;
import java.util.Map;

import com.sparkhunter.main.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

//because I'm catastrophically sick of fucking with the Service class
public class GameAudioManager {
	private static GameAudioManager instance = new GameAudioManager();
	private MediaPlayer bgm = null;
	private SoundPool effects = new SoundPool(MAX_SOUND_EFFECTS, AudioManager.STREAM_MUSIC, 0);
	private int bgmResource;
	private Map<String, Integer> effectsMap = new HashMap<String, Integer>(); //maps effect names to sound IDs
	private static final int MAX_SOUND_EFFECTS = 100; //completely arbitrary
	
	private GameAudioManager(){}
	
	public static GameAudioManager getInstance(){
		return instance;
	}
	
	//Background music related methods
	
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
	
	//Sound effect related methods
	
	public void registerEffect(Context c, String name, int resId){
		//register a new sound effect and store the name and sound ID (which is not the resId!)
		int soundId = effects.load(c, resId, 1);
		
		effectsMap.put(name, new Integer(soundId));
	}
	
	
	public void registerDefaultEffects(Context c){
		//registers default sound effects, should be called at least once!
		registerEffect(c, "click", R.raw.click);
		registerEffect(c, "quack", R.raw.squee);
	}
	
	public void playEffect(String name){
		int soundId;
		
		try{
			soundId = effectsMap.get(name).intValue();
			
			//play specified effect at full volume and normal speed once
			effects.play(soundId, (float)1.0, (float)1.0, 1, 0, (float)1.0);
		}
		catch(NullPointerException e){
			Log.d("DEBUG", "Error: " + name + " does not map to a valid sound effect.");
		}
	}
	
	//Internal helper methods
	
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
