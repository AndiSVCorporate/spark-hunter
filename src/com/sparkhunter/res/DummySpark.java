package com.sparkhunter.res;

import com.sparkhunter.main.R;

import android.content.Context;
import android.widget.Toast;

public class DummySpark extends Item {

	//ignore the fact that this is derived from item...
	public DummySpark() {
		this.setName("Dummy");
		this.setEffect("*shrug*");
		this.setIdentifier(0);
		this.setItemClass(0);
		this.setMagnitude(-1);
		this.setTarget(0);
		this.setResourceID(R.drawable.icon_dagger);
	}

	@Override
	public void useItem(Context c, int target) {
		//May not be using the right context...
		Toast.makeText(c, "I don't do squat.", Toast.LENGTH_SHORT).show();
	}

}
