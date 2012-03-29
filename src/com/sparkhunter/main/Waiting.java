package com.sparkhunter.main;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class Waiting extends Dialog {
	Dialog mDialog;
	public Waiting(Context context) {
		super(context);
		mDialog = this;
		setContentView(R.layout.waiting);
		Button b = (Button)findViewById(R.id.Cancel);
        b.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				mDialog.dismiss();
			}
        	
        });
	}
}