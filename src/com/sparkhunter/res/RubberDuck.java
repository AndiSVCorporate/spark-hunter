package com.sparkhunter.res;

import com.sparkhunter.main.R;
import android.util.Log;

public class RubberDuck extends Item {
	
	public RubberDuck(){
		//TODO make these protected, for god's sake...
		this.setName("Mr. Quack");
		this.setEffect("quack.");
		this.setIdentifier(0);
		this.setItemClass(0);
		this.setMagnitude(-1);
		this.setTarget(0);
		this.setResourceID(R.drawable.duckedit);
	}
	
	@Override
	public void useItem(int target) {
		Log.d("DEBUG", "quack.");
	}
}
