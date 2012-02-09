package com.sparkhunter.main;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class SparkHunterTitleScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, FbfriendsActivity.class);
				startActivity(i);
			}
        	
        });
        
        Button c = (Button)findViewById(R.id.button2);
        c.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, InventoryScreen.class);
				startActivity(i);
			}
		});
        
        Button d = (Button)findViewById(R.id.button3);
        d.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SparkHunterTitleScreen.this, Map.class);
				startActivity(i);
			}
		});
      
        
        
            }
}