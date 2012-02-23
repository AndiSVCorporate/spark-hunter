package com.sparkhunter.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends Activity {
	public static Spark target;
	private String sname;
	private String sdescription;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		sname = target.getName();
		sdescription = target.getDescript();
		
		TextView name = (TextView)findViewById(R.id.name);
		name.setText((CharSequence) sname);
		
		TextView info = (TextView)findViewById(R.id.info);
		info.setText((CharSequence) sdescription);

	}

}
