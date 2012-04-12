package com.sparkhunter.activities;

import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info extends Activity {
	public static Spark target;
	public static Activity getSparkActivity = new Activity();
	private String sname;
	private String sdescription;
	private Activity mActivity=this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		/*sname = target.getName();
		sdescription = target.getDescript();
		
		TextView name = (TextView)findViewById(R.id.name);
		name.setText((CharSequence) sname);
		
		TextView info = (TextView)findViewById(R.id.info);
		info.setText((CharSequence) sdescription);

		*/
		Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Info.this, GetSpark.class);
				mActivity.finish();
				//startActivity(i);
			}
		});
		
        b = (Button)findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Info.this, SparkHunterTitleScreen.class);
				mActivity.finish();
				getSparkActivity.finish();
				//startActivity(i);
			}
		});
		
		
		
		
	}

}