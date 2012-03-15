package com.sparkhunter.res;

import android.content.Context;

public abstract class Item {
	//ABC for in-game items
	//subclasses exist for potions and what-not
	private int identifier; 		//unique identifier for the item
	private int itemClass; 			//item class, e.g. "01 (potion)"
	private int target; 			//target of item effect
	private int magnitude;			//magnitude of item effect (absolute/relative)
	private String effect;			//item effect
	private String name;			//name field for the item, e.g. "Potion of Doom"
	private Integer resourceID; 	//resource tied to item (image, extend to sounds)
	
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

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
		return;
	}
	
	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}

	//need to setup some sort of game state interface for this stuff
	public abstract void useItem(Context c, int target);
	
	//resource tied to item
}
