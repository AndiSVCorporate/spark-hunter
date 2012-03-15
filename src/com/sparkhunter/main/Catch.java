package com.sparkhunter.main;

import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import com.sparkhunter.res.BackgroundMusic;
import com.sparkhunter.res.Map;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;
public class Catch extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catch1);
		
		
		Button b = (Button)findViewById(R.id.catch1);
	        b.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					if(GetSpark.chosenSpark==null){
						Toast.makeText(Catch.this, "Get a Spark first!", Toast.LENGTH_SHORT).show();
					}
					else {
					Intent i = new Intent(Catch.this, BattleActivity.class);					
					startActivity(i);
					}}
			});
				
	  b = (Button)findViewById(R.id.ignore);
	        b.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent i = new Intent(Catch.this, SparkHunterTitleScreen.class);					
					startActivity(i);
				}
			});
		
	}

}
