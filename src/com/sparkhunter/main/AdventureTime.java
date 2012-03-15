package com.sparkhunter.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Random;

public class AdventureTime extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adventure);
		int randomInt = 0;
		while (randomInt != 7){
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(10);
			if (randomInt == 7){
				Intent i = new Intent(AdventureTime.this, Catch.class);
				startActivity(i);
			}
			else {
				try{
					Thread.sleep(300);
			}
				catch(InterruptedException ie){
				}
				
			
		
	}}}

}
