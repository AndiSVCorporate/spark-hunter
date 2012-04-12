package com.sparkhunter.activities;


import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.main.R.raw;
import com.sparkhunter.main.R.string;
import com.sparkhunter.mapping.Map;
import com.sparkhunter.res.AdventureService;
import com.sparkhunter.res.BackgroundMusic;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SparkHunterTitleScreen extends Activity {
	private Intent menuSoundIntent = null;
	private MediaPlayer bgm = null;
	private Activity mActivity = this;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /* Background Service*/
        Intent  i = new Intent(getApplicationContext(), AdventureService.class);
        startService(i);
        Log.v("Service_CLASS", "CALLING SERVICE");

        //Initial player state settings go here
        Player.getInstance().initializeInventory(SparkHunterTitleScreen.this);
        
        Button b = (Button)findViewById(R.id.Battle);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				if(GetSpark.chosenSpark==null){
					Toast.makeText(SparkHunterTitleScreen.this, "Get a Spark first!", Toast.LENGTH_SHORT).show();
				}
				else {
					Intent i = new Intent(SparkHunterTitleScreen.this, BattleActivity.class);
					startActivity(i);
				}
			}
        	
        });
        b = (Button)findViewById(R.id.button1);
        //menu music starts
    	//menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
    	//menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.mlp_pinkiepie);
    	//SparkHunterTitleScreen.this.startService(menuSoundIntent);
        bgm = MediaPlayer.create(SparkHunterTitleScreen.this, R.raw.mlp_pinkiepie);
        bgm.start();
        menuSoundIntent = new Intent(getApplicationContext(), BackgroundMusic.class);
        
        b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				if(GetSpark.chosenSpark==null){
					Toast.makeText(SparkHunterTitleScreen.this, "Get a Spark first!", Toast.LENGTH_SHORT).show();
				}
				else {
					Intent i = new Intent(SparkHunterTitleScreen.this, MultiplayerListActivity.class);
					bgm.stop();
			    	menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
			    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
			    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
					
					startActivity(i);
				}
			}
        	
        });
        
        b = (Button)findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, InventoryScreen.class);
				
				bgm.stop();
				menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
		    	getApplicationContext();
				startActivity(i);
			}
		});
        
        b = (Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, Map.class);
				
				bgm.stop();
				menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
				
				startActivity(i);
			}
		});
        b = (Button)findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, GetSpark.class);
				
				bgm.stop();
				menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
		    	//mActivity.finish();
				startActivity(i);
			}
		});
    
      }
    
}