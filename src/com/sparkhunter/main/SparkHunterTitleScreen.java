package com.sparkhunter.main;


import com.sparkhunter.res.BackgroundMusic;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class SparkHunterTitleScreen extends Activity {
	private Intent menuSoundIntent = null;
	private MediaPlayer bgm = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //menu music starts
    	//menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
    	//menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.mlp_pinkiepie);
    	//SparkHunterTitleScreen.this.startService(menuSoundIntent);
        bgm = MediaPlayer.create(SparkHunterTitleScreen.this, R.raw.mlp_pinkiepie);
        bgm.start();
        menuSoundIntent = new Intent(getApplicationContext(), BackgroundMusic.class);
        
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, FbfriendsActivity.class);
				
				bgm.stop();
		    	menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
				
				startActivity(i);
			}
        	
        });
        
        Button c = (Button)findViewById(R.id.button2);
        c.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, InventoryScreen.class);
				
				bgm.stop();
				menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
		    	
				startActivity(i);
			}
		});
        
        Button d = (Button)findViewById(R.id.button3);
        d.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, Map.class);
				
				bgm.stop();
				menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
				
				startActivity(i);
			}
		});
        Button e = (Button)findViewById(R.id.button4);
        e.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, GetSpark.class);
				
				bgm.stop();
				menuSoundIntent.setAction(Integer.toString(R.string.music_intent));
		    	menuSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.click);
		    	SparkHunterTitleScreen.this.startService(menuSoundIntent);
				
				startActivity(i);
			}
		});
      }
}