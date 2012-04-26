package com.sparkhunter.activities;

import com.sparkhunter.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemDescriptionActivity extends Activity {

	public ItemDescriptionActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_information);
		
		//need some textviews here
		
		
		Button b = (Button)findViewById(R.id.okbutton);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(ItemDescriptionActivity.this, ItemInventoryActivity.class);
				ItemDescriptionActivity.this.finish();
				//startActivity(i);
			}
		});
	}

}
