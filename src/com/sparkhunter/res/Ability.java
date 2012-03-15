package com.sparkhunter.res;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		List<CharSequence> retVal = new ArrayList<CharSequence>();
		Iterator<Ability> i = list.iterator();
		while (i.hasNext()){
			retVal.add(i.next().getName());
		}
		return retVal;
		
	}

}