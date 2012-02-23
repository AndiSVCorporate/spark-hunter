package com.sparkhunter.main;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SparkHunterTitleScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
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
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, FbfriendsActivity.class);
				startActivity(i);
			}
        	
        });
        
        b = (Button)findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, InventoryScreen.class);
				startActivity(i);
			}
		});
        
        b = (Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, Map.class);
				startActivity(i);
			}
		});
        b = (Button)findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(SparkHunterTitleScreen.this, GetSpark.class);
				startActivity(i);
			}
		});
      
      
        
        
            }
}