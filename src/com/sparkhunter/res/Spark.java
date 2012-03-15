package com.sparkhunter.res;

import java.util.ArrayList;
import java.util.List;

public class Spark extends Entity{
	//stats
	private int mMaxHP;
	public int mCurHP;
	private int mSpeed;
	
	private String mName;
	private String mDescription;
	public int mResId;
	
	private List<Ability> mAblty;
	
	public Spark(String name, int resId)
	{
		mName = name;
		mDescription = "";
		mResId = resId;
		mAblty = new ArrayList<Ability>();
		setStats();
	}
	
	//sets
	public void setDescript(String descript){
		mDescription = descript;
	}
	
	//this just sets the stats to some test values for now
	public void setStats()
	{
		mMaxHP = 100;
		mCurHP = mMaxHP;
		mSpeed = 10;
		mAblty.add(new Ability("Choose an Attack",0)); //TODO: Spinner-work around
		mAblty.add(new Ability("Herp",20));
		mAblty.add(new Ability("Derp",50));
	}
	//gets
	public String getName(){
		return mName;
	}
	
	public String getDescript(){
		return mDescription;
	}
	
	public List<Ability> getAbilities(){
		
		return mAblty;
	}
}