package com.sparkhunter.activities;

import com.sparkhunter.main.R;
import com.sparkhunter.main.R.drawable;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GetSpark extends Activity implements OnClickListener {

	private ImageView display;
	private Spark chosenSpark;
	public static Spark target;
	
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
		
		//default spark
		chosenSpark = new Spark("Dingus",R.drawable.duckedit);
		chosenSpark.setDescript("Starting water type Spark, Don't Duck With em!");
				
	}

	@Override
	public void onClick(View v) {

		String sname;
		String sdescription;
		switch (v.getId()){
		case R.id.IVimage1:
			display.setImageResource(R.drawable.duckedit);
			chosenSpark = new Spark("Dingus",R.drawable.duckedit);
			chosenSpark.setDescript("Starting water type Spark, Don't Duck With em!");
			Info.target = chosenSpark;
			target = chosenSpark;
			break;
		case R.id.IVimage2:
			display.setImageResource(R.drawable.androidmarker);
			chosenSpark = new Spark("Biggy",R.drawable.androidmarker);
			chosenSpark.setDescript("Starting fire type Spark, Careful for his hidden gun!");
			Info.target = chosenSpark;
			target = chosenSpark;
			break;
		case R.id.IVimage3:
			display.setImageResource(R.drawable.item_square);
			chosenSpark = new Spark("SquarePants",R.drawable.item_square);
			chosenSpark.setDescript("Starting grass type Spark, Makes a mean burger!");
			Info.target = chosenSpark;
			target = chosenSpark;
			break;
		case R.id.GetSpark:
			Info.target = chosenSpark;
			Intent i = new Intent(GetSpark.this, Info.class);
			startActivity(i);
			Info.target = chosenSpark;
			Info.getSparkActivity = this;
			
			target = chosenSpark;
			break;
		}
		sname = target.getName();
		sdescription = target.getDescript();
		TextView name = (TextView)findViewById(R.id.name);
		name.setText((CharSequence) sname);
		
		TextView info = (TextView)findViewById(R.id.info);
		info.setText((CharSequence) sdescription);
	}

}
