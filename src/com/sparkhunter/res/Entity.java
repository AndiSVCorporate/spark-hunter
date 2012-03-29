package com.sparkhunter.res;

import android.content.Context;

public abstract class Entity {
	//abstract class that encompasses items and sparks
	
	//corresponds to using an item or selecting a spark
	public abstract void activate(Context c, int target);
}
