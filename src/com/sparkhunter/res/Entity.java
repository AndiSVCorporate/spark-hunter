package com.sparkhunter.res;

import android.content.Context;
import android.content.res.Resources;

public interface Entity {
	//abstract class that encompasses items and sparks
	
	//corresponds to using an item or selecting a spark
	public abstract void activate(Context c, int target);
	
	//abstract setters/getters, both items and sparks need to implement these
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
	public void setImageResource(String newResource, Context c) throws Resources.NotFoundException;
	public void setSoundResource(String newResource, Context c) throws Resources.NotFoundException;
	
	public int getId();
	public String getType();
	public String getName();
	public int getLevel();
	public int getExperience();
	public int getMaxHp();
	public int getCurHp();
	public int getSpeed();
	public int getAttack();
	public int getDefense();
	public int getHpGain();
	public int getSpeedGain();
	public int getAttackGain();
	public int getDefenseGain();
	public String getEffect();
	public String getImageResource();
	public String getSoundResource();
	public int getImageResId();
	public int getSoundResId();
}