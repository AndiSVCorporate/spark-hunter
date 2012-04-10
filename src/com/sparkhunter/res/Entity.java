package com.sparkhunter.res;

import android.content.Context;

public abstract class Entity {
	//abstract class that encompasses items and sparks
	
	//corresponds to using an item or selecting a spark
	public abstract void activate(Context c, int target);
	
	//abstract setters, both items and sparks need to implement these
	public abstract void setId(int newId);
	public abstract void setType(String newType);
	public abstract void setName(String newName);
	public abstract void setLevel(int newLevel);
	public abstract void setExperience(int newExperience);
	public abstract void setMaxHp(int newMaxHp);
	public abstract void setCurHp(int newCurHp);
	public abstract void setSpeed(int newSpeed);
	public abstract void setAttack(int newAttack);
	public abstract void setDefense(int newDefense);
	public abstract void setHpGain(int newHpGain);
	public abstract void setSpeedGain(int newSpeedGain);
	public abstract void setAttackGain(int newAttackGain);
	public abstract void setDefenceGain(int newDefenceGain);
	public abstract void setEffect(String newEffect);

	public void setDefence(int int1) {
		// TODO Auto-generated method stub
		
	}
}