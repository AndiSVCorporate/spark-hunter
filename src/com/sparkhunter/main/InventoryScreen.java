package com.sparkhunter.main;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class InventoryScreen extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
        
        //setup the gridview for all the items
        GridView gridview = (GridView) findViewById(R.id.inventorygrid);
        gridview.setAdapter(new ItemAdapter(this));
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	//useItem calls go here, in general
                Toast.makeText(InventoryScreen.this, "quack.", Toast.LENGTH_SHORT).show();
                //TODO tie sound resource into Item class
                MediaPlayer itemSound = MediaPlayer.create(InventoryScreen.this, R.raw.squee);
        		itemSound.start();
            }
        });
	}
}

