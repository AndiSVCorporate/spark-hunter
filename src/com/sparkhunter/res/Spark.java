package com.sparkhunter.res;

import java.util.ArrayList;
import java.util.List;

import com.sparkhunter.activities.Info;
import com.sparkhunter.main.R;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import android.widget.Toast;

public class Spark implements Entity{
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
	private int mImageResId;
	private int mSoundResId;
	private String mImageResource;
	private String mSoundResource;
	
	private List<Ability> mAblty;
	
	//needed to resolve resources
	private static final String PACKAGE_NAME = "com.sparkhunter.main:";
	private static final String I_RES_TYPE = "drawable/";
	private static final String S_RES_TYPE = "raw/";
	
	public Spark(String name, int resId)
	{
		mName = name;
		mDescription = "";
		mImageResId = resId;
		mAblty = new ArrayList<Ability>();
		setStats();
	}
	
	//TODO: hardcoded lookup by name until sql works fully
	public Spark(String name)
	{
		mName = name;
		mDescription = "";
		if(mName.equals("Dingus")){
			mImageResId = R.drawable.duckedit;
		}
		else if(mName.equals("Biggy")){
			mImageResId = R.drawable.androidmarker;
		}
		else if(mName.equals("SquarePants")){
			mImageResId = R.drawable.item_square;
		}
		mAblty = new ArrayList<Ability>();
		setStats();
	}
	
	public Spark() {
		mAblty = new ArrayList<Ability>();
		
		//default abilities for everyone!
		mAblty.add(new Ability("Choose an Attack",0)); //TODO: Spinner-work around
		mAblty.add(new Ability("Herp",20));
		mAblty.add(new Ability("Derp",50));
	}
	
	public void setAbilities(String ... args){
		mAblty = new ArrayList<Ability>();
		mAblty.add(new Ability("Choose an Attack",0)); //TODO: Spinner-work around
		int i = 0;
		while(i<args.length){
			mAblty.add(new Ability(args[i],Integer.parseInt(args[i+1])));
			i+=2;
		}
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
	
	public void addExp(int Exp){
		 getExperience();
		 mExp = mExp + Exp;
	}
	
	public String getDescript(){
		return mDescription;
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
		getExperience();
		mExp = mExp - 100;
		getCurHp();
		mMaxHp = mMaxHp + mHpGain;
		mCurHp = mMaxHp;
		getSpeed();
		mSpeed = mSpeed + mSpeedGain;
		getAttack();
		mAttack = mAttack + mAttackGain;
		getDefense();
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
		// TODO tie this to individual spark sound effects
		Log.d("DEBUG", "quack.");
		//GameAudioManager.getInstance().playEffect("quack");
    	
    	//Toast.makeText(c, "quack.", Toast.LENGTH_SHORT).show();
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
	public void setDefenseGain(int newDefenceGain) {
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
	
	@Override
	public int getId() {
		return mId;
	}

	@Override
	public String getType() {
		return mType;
	}

	@Override
	public int getLevel() {
		return mLevel;
	}

	@Override
	public int getExperience() {
		return mExp;
	}

	@Override
	public int getMaxHp() {
		return mMaxHp;
	}

	@Override
	public int getCurHp() {
		return mCurHp;
	}

	@Override
	public int getSpeed() {
		return mSpeed;
	}

	@Override
	public int getAttack() {
		return mAttack;
	}

	@Override
	public int getDefense() {
		return mDefense;
	}

	@Override
	public int getHpGain() {
		return mHpGain;
	}

	@Override
	public int getSpeedGain() {
		return mSpeedGain;
	}

	@Override
	public int getAttackGain() {
		return mAttackGain;
	}

	@Override
	public int getDefenseGain() {
		return mDefenseGain;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public String getEffect() {
		return mEffect;
	}
	
	@Override
	public void setImageResource(String newResource, Context c) throws NotFoundException {
		//store the name, and resolve into an actual identifier
		mImageResource = newResource;
		mImageResId = c.getResources().getIdentifier(newResource, I_RES_TYPE, PACKAGE_NAME);
		
		//throw a fit if it's not found
		if(mImageResId == 0)
			throw new Resources.NotFoundException(mImageResource);
	}

	@Override
	public void setSoundResource(String newResource, Context c) throws NotFoundException {
		//store the name, and resolve into an actual identifier
		mSoundResource = newResource;
		mSoundResId = c.getResources().getIdentifier(newResource, S_RES_TYPE, PACKAGE_NAME);
		
		//throw a fit if it's not found
		if(mSoundResId == 0)
			throw new Resources.NotFoundException(mSoundResource);
	}

	@Override
	public String getImageResource() {
		return mImageResource;
	}

	@Override
	public String getSoundResource() {
		return mSoundResource;
	}

	@Override
	public int getImageResId() {
		return mImageResId;
	}

	@Override
	public int getSoundResId() {
		return mSoundResId;
	}
	
	@Override
	public String toString(){
		String information;
		information = mName + ":\n";
		
		//check field values, and state them if relevant (not -1)
		if(mSpeed != 0)
			information += "Speed: " + mSpeed + "\n";
		if(mMaxHp != 0)
			information += "Max HP: " + mMaxHp + "\n";
		if(mCurHp != 0)
			information += "Current HP: " + mCurHp + "\n";
		if(mAttack != 0)
			information += "Attack: " + mAttack + "\n";
		if(mDefense != 0)
			information += "Defense: " + mDefense + "\n";
		if(mSpeedGain != 0)
			information += "Speed Gain: " + mSpeedGain + "\n";
		if(mHpGain != 0)
			information += "HP Gain: " + mHpGain + "\n";
		if(mAttackGain != 0)
			information += "Attack Gain: " + mAttackGain + "\n";
		if(mDefenseGain != 0)
			information += "Defense Gain: " + mDefenseGain + "\n";
		if(mExp != 0)
			information += "Experience: " + mExp + "\n";
		
		return information;
	}
	
	@Override
	public boolean equals(Object o){
		boolean retValue = false;
		
		//verify it's actually an Item
		if(o.getClass() != Spark.class){
			retValue = false;
		}
		else{
			//IDs are unique to each spark
			if(((Spark)o).getId() == this.getId()){
				retValue = true;
			}
			else{
				retValue = false;
			}
		}
		
		return retValue;
	}
}