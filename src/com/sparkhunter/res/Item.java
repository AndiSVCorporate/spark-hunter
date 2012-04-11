package com.sparkhunter.res;

import com.sun.xml.internal.ws.wsdl.parser.MexEntityResolver;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;

public class Item implements Entity {
	//ABC for in-game items
	//subclasses exist for potions and what-not
	private int identifier; 		//unique identifier for the item
	private int itemClass; 			//item class, e.g. "01 (potion)"
	private int target; 			//target of item effect
	private Integer resourceID; 	//resource tied to item (image, extend to sounds)
	private int mId;
	private String mType;
	private String mName;
	private int mLevel;
	private int mMaxHp;
	private int mSpeed;
	private int mCurHp;
	private int mAttack;
	private int mDefense;
	private int mSpeedGain;
	private int mHpGain;
	private int mAttackGain;
	private int mDefenseGain;
	private String mEffect;
	private int mExp;
	private int mImageResId;
	private int mSoundResId;
	private String mImageResource;
	private String mSoundResource;
	
	//needed to resolve resources
	private static final String PACKAGE_NAME = "com.sparkhunter.main:";
	private static final String I_RES_TYPE = "drawable/";
	private static final String S_RES_TYPE = "raw/";
	
	//getters and setters
	public int getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(int newID) {
		identifier = newID;
		return;
	}
	
	public int getItemClass(){
		return itemClass;
	}
	
	public void setItemClass(int newItemClass){
		itemClass = newItemClass;
		return;
	}
	
	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getEffect() {
		return mEffect;
	}

	public String getName() {
		return mName;
	}
	
	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}

	//need to setup some sort of game state interface for this stuff
	//all effect resolution logic should go in here
	public void activate(Context c, int target){
		
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
}
