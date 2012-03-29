package com.sparkhunter.main;

import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Waiting extends Activity {

	ImageView display;
	static Spark chosenSpark;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waiting);
		
		Button b = (Button)findViewById(R.id.Cancel);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
					Intent i = new Intent(Waiting.this, SparkHunterTitleScreen.class);
					startActivity(i);
			}
        	
        });

	}
}