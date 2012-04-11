package com.sparkhunter.res;

import android.content.Context;

public interface Entity {
	//abstract class that encompasses items and sparks
	
	//corresponds to using an item or selecting a spark
	public abstract void activate(Context c, int target);
	
	//abstract setters, both items and sparks need to implement these
	public void setId(int newId);
	public void setType(String newType);
	public void setName(String newName);
	public void setLevel(int newLevel);
	public void setExperience(int newExperience);
	public void setMaxHp(int newMaxHp);
	public void setCurHp(int newCurHp);
	public void setSpeed(int newSpeed);
	public void setAttack(int newAttack);
	public void setDefense(int newDefense);
	public void setHpGain(int newHpGain);
	public void setSpeedGain(int newSpeedGain);
	public void setAttackGain(int newAttackGain);
	public void setDefenseGain(int newDefenseGain);
	public void setEffect(String newEffect);
}