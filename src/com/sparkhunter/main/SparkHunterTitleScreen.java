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

			public void onClick(View v1) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(SparkHunterTitleScreen.this, FbfriendsActivity.class);
				startActivity(i1);
			}
        	
        });
        
        Button c = (Button)findViewById(R.id.button2);
        c.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v2) {
				// TODO Auto-generated method stub
				Intent i2 = new Intent(SparkHunterTitleScreen.this, InventoryScreen.class);
				startActivity(i2);
			}
		});
        
      // TODO Causes crash
      /*  Button d = (Button)findViewById(R.id.button3);
        c.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v3) {
				// TODO Auto-generated method stub
				Intent i3 = new Intent(SparkHunterTitleScreen.this, Map.class);
				startActivity(i3);
			}
		});*/
      
        
        
            }
}