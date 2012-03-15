package com.sparkhunter.res;

import com.sparkhunter.main.R;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RubberDuck extends Item {
	
	public RubberDuck(){
		//TODO make these protected, for god's sake...
		this.setName("Mr. Quack");
		this.setEffect("quack.");
		this.setIdentifier(0);
		this.setItemClass(0);
		this.setMagnitude(-1);
		this.setTarget(0);
		this.setResourceID(R.drawable.duckedit);
	}
	
	@Override
	public void useItem(Context c, int target) {
		Log.d("DEBUG", "quack.");
		Intent itemSoundIntent = new Intent(c, BackgroundMusic.class);
    	
    	//KLUDGE-TASTIC, find a better way to package the sound data
    	itemSoundIntent.setAction(Integer.toString(R.string.music_intent));
    	itemSoundIntent.putExtra(Integer.toString(R.string.music_id), R.raw.squee);
    	c.startService(itemSoundIntent);
    	
    	Toast.makeText(c, "quack.", Toast.LENGTH_SHORT).show();
	}
}
