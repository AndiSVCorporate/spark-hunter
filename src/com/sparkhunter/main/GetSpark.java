package com.sparkhunter.main;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GetSpark extends Activity implements OnClickListener {

	ImageView display;
	Spark chosenSpark;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getspark);
		
		display = (ImageView) findViewById(R.id.IVdisplay);
		ImageView image1 = (ImageView) findViewById(R.id.IVimage1);
		ImageView image2 = (ImageView) findViewById(R.id.IVimage2);
		ImageView image3 = (ImageView) findViewById(R.id.IVimage3);	
		Button choosespark = (Button) findViewById(R.id.GetSpark);
		
		image1.setOnClickListener(this);
		image2.setOnClickListener(this);
		image3.setOnClickListener(this);
		choosespark.setOnClickListener(this);
				
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.IVimage1:
			display.setImageResource(R.drawable.duckedit);
			chosenSpark = new Spark("Dingus");
			chosenSpark.setDescript("Starting water type Spark, Don't Duck With em!");
			break;
		case R.id.IVimage2:
			display.setImageResource(R.drawable.androidmarker);
			chosenSpark = new Spark("Biggy");
			chosenSpark.setDescript("Starting fire type Spark, Carefull for his hidden gun!");
			break;
		case R.id.IVimage3:
			display.setImageResource(R.drawable.item_square);
			chosenSpark = new Spark("SquarePants");
			chosenSpark.setDescript("Starting grass type Spark, Makes a mean burger!");
			break;
		case R.id.GetSpark:
			Info.target = chosenSpark;
			Intent i = new Intent(GetSpark.this, Info.class);
			startActivity(i);
			break;
		}
	}

}
