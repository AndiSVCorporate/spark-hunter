package com.sparkhunter.res;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class Ability{
	private int mDmg;
	private String mName;
	
	public Ability(String name, int damage){
		mName = name;
		mDmg = damage;
	}
	
	public String getName(){
		return mName;
	}
	
	public int getDamage(){
		return mDmg;
	}
	
	 public static List<CharSequence> extractChar(List<Ability> list){
		if(list == null)
			Log.d("DEBUG", "Error: Ability list is null");
		List<CharSequence> retVal = new ArrayList<CharSequence>();
		Iterator<Ability> i = list.iterator();
		while (i.hasNext()){
			retVal.add(i.next().getName());
		}
		return retVal;
		
	}

}