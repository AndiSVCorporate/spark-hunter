package com.sparkhunter.res;

import com.sun.xml.internal.ws.wsdl.parser.MexEntityResolver;

import android.content.Context;

public class Item extends Entity {
	//ABC for in-game items
	//subclasses exist for potions and what-not
	private int identifier; 		//unique identifier for the item
	private int itemClass; 			//item class, e.g. "01 (potion)"
	private int target; 			//target of item effect
	private int magnitude;			//magnitude of item effect (absolute/relative)
	private String effect;			//item effect
	private String name;			//name field for the item, e.g. "Potion of Doom"
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
	
	public int getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(int magnitude) {
		this.magnitude = magnitude;
	}

	public String getEffect() {
		return effect;
	}

	public String getName() {
		return name;
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
