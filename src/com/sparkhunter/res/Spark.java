package com.sparkhunter.res;

public class Spark {
	//stats
	private int mMaxHP;
	public int mCurHP;
	private int mSpeed;
	
	private String mName;
	private String mDescription;
	public int mResId;
	
	private Ability[] mAblty;
	
	public Spark(String name, int resId)
	{
		mName = name;
		mDescription = "";
		mResId = resId;
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
		mAblty = new Ability[2];
		mAblty[0] = new Ability("Herp",20);
		mAblty[1] = new Ability("Derp",50);
	}
	//gets
	public String getName(){
		return mName;
	}
	
	public String getDescript(){
		return mDescription;
	}
}

class Ability{
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
}