package com.sparkhunter.activities;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.sparkhunter.activities.FbfriendsActivity.FriendListener;
import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.main.R.raw;
import com.sparkhunter.main.R.string;
import com.sparkhunter.mapping.Map;
import com.sparkhunter.network.PHPTask;
import com.sparkhunter.network.ServerInterface;
import com.sparkhunter.res.AdventureService;
import com.sparkhunter.res.GameAudioManager;
import com.sparkhunter.res.FacebookUtils;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class SparkHunterTitleScreen extends Activity {	
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
        
        //Load default sound effects
        GameAudioManager.getInstance().registerDefaultEffects(getApplicationContext());
        
        FacebookUtils.getMe(new MeListener());
        
        //if all the button instances are named 'b', how the hell does this actually work?
        
        Button b = (Button)findViewById(R.id.Battle);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
				
				if(GetSpark.chosenSpark==null){
					Toast.makeText(SparkHunterTitleScreen.this, "Get a Spark first!", Toast.LENGTH_SHORT).show();
				}
				else {
					Intent i = new Intent(SparkHunterTitleScreen.this, BattleActivity.class);
					startActivity(i);
				}
			}
        	
        });
        
        
        GameAudioManager.getInstance().setBackground(getApplicationContext(), R.raw.mlp_pinkiepie);
        
        b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
				
				if(GetSpark.chosenSpark==null){
					Toast.makeText(SparkHunterTitleScreen.this, "Get a Spark first!", Toast.LENGTH_SHORT).show();
				}
				else {
					Intent i = new Intent(SparkHunterTitleScreen.this, Multiplayer.class);
					startActivity(i);
				}
			}
        	
        });
        
        b = (Button)findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
				
				Intent i = new Intent(SparkHunterTitleScreen.this, InventoryScreen.class);
				startActivity(i);
			}
		});
        
        b = (Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
				
				Intent i = new Intent(SparkHunterTitleScreen.this, Map.class);
				startActivity(i);
			}
		});
        
        b = (Button)findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
				
				Intent i = new Intent(SparkHunterTitleScreen.this, GetSpark.class);
				startActivity(i);
			}
		});
      }
    
    //called when this activity is navigated back to
    @Override
    public void onRestart(){
    	super.onResume();
    	
    	GameAudioManager am = GameAudioManager.getInstance();
    	
    	//start the background music again if it changed
    	if(am.getBgmResource() != R.raw.mlp_pinkiepie)
    		am.setBackground(getApplicationContext(), R.raw.mlp_pinkiepie);
    }
    private class MeListener implements RequestListener {

 		@Override
 		public void onComplete(final String response, final Object state) {
 				//parse the friend data
 				try {
 					JSONObject json = Util.parseJson(response);
 					Player.getInstance().playerName = (String) json.get("name");
 					Player.getInstance().playerID = (String) json.get("id");

				} catch (JSONException e) {
					e.printStackTrace();
				} catch (FacebookError e) {
					e.printStackTrace();
				}
 		}
 		
		@Override
		public void onIOException(IOException e, Object state) {
		}

		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
		}

		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
		}

		@Override
		public void onFacebookError(FacebookError e, Object state) {
		}
    }
}
