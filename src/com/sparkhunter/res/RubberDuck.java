package com.sparkhunter.res;

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
		this.setResourceID(com.sparkhunter.main.R.drawable.duckedit);
	}
	
	@Override
	public void useItem(int target) {
		// TODO Auto-generated method stub
		Log.d("DEBUG", "quack.");
	}
}
