package com.sparkhunter.res;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class Spark extends Entity{
	//stats

	//public? 
	public int mCurHp;
	public int mExp;
	private int mMaxHp;
	private int mSpeed;
	private int mAttack;
	private int mDefense;
	private int mHpGain;
	private int mAttackGain;
	private int mSpeedGain;
	private int mDefenseGain;
	private int mLevel;
	private String mEffect;
	private int mId;
	private String mType;
	
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
	
	public Spark() {
		// TODO Auto-generated constructor stub
	}

	//sets
	public void setDescript(String descript){
		mDescription = descript;
	}
	
	//this just sets the stats to some test values for now
	public void setStats()
	{
		mLevel = 1;
		
		mMaxHp = 100;
		mCurHp = mMaxHp;
		mSpeed = 10;
		mAttack = 15;
		mDefense = 5;
		
		mHpGain = 10;
		mAttackGain = 5;
		mSpeedGain = 3;
		mDefenseGain = 2;
		
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
		return mMaxHp;
	}
	
	public int getSpeed(){
		return mSpeed;
	}
	
	public int getAttack(){
		return mAttack;
	}
	
	public int getDefence(){
		return mDefense;
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
	
	//where is this getting called from?
	public void LevelUp(){
		getLevel();
		mLevel = mLevel + 1;
		getExp();
		mExp = mExp - 100;
		getHP();
		mMaxHp = mMaxHp + mHpGain;
		mCurHp = mMaxHp;
		getSpeed();
		mSpeed = mSpeed + mSpeedGain;
		getAttack();
		mAttack = mAttack + mAttackGain;
		getDefence();
		mDefense = mDefense + mDefenseGain;
		
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

	@Override
	public void setId(int newId) {
		mId = newId;
	}

	@Override
	public void setType(String newType) {
		mType = newType;
	}

	@Override
	public void setName(String newName) {
		mName = newName;	
	}

	@Override
	public void setLevel(int newLevel) {
		mLevel = newLevel;
	}

	@Override
	public void setMaxHp(int newMaxHp) {
		mMaxHp = newMaxHp;
	}

	@Override
	public void setCurHp(int newCurHp) {
		mCurHp = newCurHp;
	}

	@Override
	public void setSpeed(int newSpeed) {
		mSpeed = newSpeed;
	}

	@Override
	public void setAttack(int newAttack) {
		mAttack = newAttack;
	}

	@Override
	public void setDefense(int newDefense) {
		mDefense = newDefense;
	}

	@Override
	public void setHpGain(int newHpGain) {
		mHpGain = newHpGain;
	}

	@Override
	public void setSpeedGain(int newSpeedGain) {
		mSpeedGain = newSpeedGain;
	}

	@Override
	public void setAttackGain(int newAttackGain) {
		mAttackGain = newAttackGain;
	}

	@Override
	public void setDefenceGain(int newDefenceGain) {
		mDefenseGain = newDefenceGain;
	}

	@Override
	public void setEffect(String newEffect) {
		mEffect = newEffect;
	}

	@Override
	public void setExperience(int newExperience) {
		mExp = newExperience;
	}
}