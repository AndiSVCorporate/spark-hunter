package com.sparkhunter.res;

import com.sparkhunter.main.R;



public class Battle {
	public Spark mYourSpark;
	public Spark mHisSpark;
	public boolean mVictory;
	public boolean mLose;
	static public boolean ah;
	public Battle(Spark your, Spark his)
	{
		mVictory = false;
		mLose = false;
		mYourSpark = your;
		if(mHisSpark == null){
			mHisSpark = new Spark("Placeholder",R.drawable.item_diamond);
		}
		else{
			mHisSpark = his;
		}
		
	}
	public String attack(String sName, Spark attacker, Spark defender, boolean counter)
	{
		String retVal = null;
		//TODO: this is hardcoded until there is a lookup table for abilites
		int dmg = 0;
		if(sName == "Herp")
			dmg = 20;
		else if(sName == "Derp")
			dmg = 5;
		else if(sName == "Poke")
			dmg = 7;
			
		retVal = attacker.getName() +" uses " + sName +" and does " + dmg +" damage!";
		//TODO: Future complicated battle calculations go here
		defender.mCurHp-=dmg;
		if(defender.mCurHp>0){
			if(counter==true) //stops infinite loop of counters
				retVal = retVal + "\n" + attack("Poke",defender,attacker,false); // TODO:
		}
		else
			retVal = retVal + "\n" + defender.getName() +" dies a tragic death.";
		
		
		return retVal;
		
	}

	public boolean run(){
		boolean retVal = true;
		
		return retVal;
		
	}

	public String setWin(){
		mVictory = true;
		return "You win!";
		
	}
	public String setLose(){
		mVictory = true;
		return "You fail!";
		
	}
}
