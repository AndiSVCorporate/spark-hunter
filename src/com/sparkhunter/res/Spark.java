package com.sparkhunter.res;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class Spark extends Entity{
	//stats

	public int mCurHP;
	public int mExp;
	private int mMaxHP;
	private int mSpeed;
	private int mAttack;
	private int mDefence;
	private int mHPgain;
	private int mAtkgain;
	private int mSpdgain;
	private int mDefgain;
	private int mLevel;
	
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
		mLevel = 1;
		
		mMaxHP = 100;
		mCurHP = mMaxHP;
		mSpeed = 10;
		mAttack = 15;
		mDefence = 5;
		
		mHPgain = 10;
		mAtkgain = 5;
		mSpdgain = 3;
		mDefgain = 2;
		
		mExp = 0;
		
		mAblty.add(new Ability("Choose an Attack",0)); //TODO: Spinner-work around
		mAblty.add(new Ability("Herp",20));
		mAblty.add(new Ability("Derp",50));
	}
	//gets
	public String getName(){
		return mName;
	}
	
	public int getExp(){
		return mExp;
	}
	
	public void addExp(int Exp){
		 getExp();
		 mExp = mExp + Exp;
	}
	
	public String getDescript(){
		return mDescription;
	}
	
	public int getHP(){
		return mMaxHP;
	}
	
	public int getSpeed(){
		return mSpeed;
	}
	
	public int getAttack(){
		return mAttack;
	}
	
	public int getDefence(){
		return mDefence;
	}
	
	public int getLevel(){
		return mLevel;
	}
	
	public void gainExp(){
		getLevel();
		if (mLevel <= 5){
			addExp(50);
		}
		else {
			addExp(10);
		}
	}
	public void LevelUp(){
		getLevel();
		mLevel = mLevel + 1;
		getExp();
		mExp = mExp - 100;
		getHP();
		mMaxHP = mMaxHP + mHPgain;
		mCurHP = mMaxHP;
		getSpeed();
		mSpeed = mSpeed + mSpdgain;
		getAttack();
		mAttack = mAttack + mAtkgain;
		getDefence();
		mDefence = mDefence + mDefgain;
		
		if (mLevel == 16){
			mAblty.add(new Ability("Derpina",70));
		}
	
		if (mLevel == 25){
			mAblty.add(new Ability("Derpier",110));
		}
		
	}
	public List<Ability> getAbilities(){
		
		return mAblty;
	}

	@Override
	public void activate(Context c, int target) {
		// TODO Auto-generated method stub
		
	}
}