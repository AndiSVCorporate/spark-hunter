package com.sparkhunter.res;



public class Battle {
	public Spark mYourSpark;
	public Spark mHisSpark;
	public boolean mVictory;
	public boolean mLose;
	public Battle(Spark your, Spark his)
	{
		mVictory = false;
		mLose = false;
		mYourSpark = your;
		mHisSpark = his;
		
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
		defender.mCurHP-=dmg;
		if(defender.mCurHP>0){
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
