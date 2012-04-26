package com.sparkhunter.res;

import com.sparkhunter.main.R;



public class Battle {
	protected Spark mYourSpark;
	protected Spark mHisSpark;
	protected boolean mVictory;
	protected boolean mLose;
	
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
	public String attack(String yAttack, Spark yours, String hAttack, Spark his)
	{
		String retVal = null;
		Spark first;
		Spark second;
		String fAttack;
		String sAttack;
		if(goesFirst(yours,his)){
			first = yours;
			fAttack = yAttack;
			second = his;
			sAttack = hAttack;
		}else{
			first = his;
			fAttack = hAttack;
			second = yours;
			sAttack = yAttack;
		}
		retVal = getAttackString(first.getName(),fAttack,damageCalc(fAttack, first));
		second.mCurHp -= damageCalc(fAttack, first);
		if(second.mCurHp>0){
				retVal = retVal + "\n" + getAttackString(second.getName(),sAttack,damageCalc(sAttack, second)); // TODO:
				first.mCurHp -= damageCalc(sAttack, second);
		}
		else
			retVal = retVal + "\n" + second.getName() +" dies a tragic death.";
		
		return retVal;
		
	}
	
	private String getAttackString(String name, String attack, int dmg){
		return 	name +" uses " + attack +" and does " + dmg +" damage!";

	}
	private int damageCalc(String attack, Spark spark){
		int dmg = 0;
		if(attack.equals("Herp"))
			dmg = 20;
		else if(attack.equals("Derp"))
			dmg = 5;
		else if(attack.equals("Poke"))
			dmg = 7;
		return dmg;
	}
	private boolean goesFirst(Spark y, Spark h){
		if(y.getSpeed()==h.getSpeed()){
			return Player.getInstance().isHost(); //host auto wins ties until synced random is implemented
		}
		else if(y.getSpeed()>h.getSpeed())
		{
			return true;
		}
		else{
			return false;
		}
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
	
	public Spark getmYourSpark() {
		return mYourSpark;
	}
	public void setmYourSpark(Spark mYourSpark) {
		this.mYourSpark = mYourSpark;
	}
	public Spark getmHisSpark() {
		return mHisSpark;
	}
	public void setmHisSpark(Spark mHisSpark) {
		this.mHisSpark = mHisSpark;
	}
	public boolean ismVictory() {
		return mVictory;
	}
	public void setmVictory(boolean mVictory) {
		this.mVictory = mVictory;
	}
	public boolean ismLose() {
		return mLose;
	}
	public void setmLose(boolean mLose) {
		this.mLose = mLose;
	}
	public void initializePlayerData() {
		// TODO Auto-generated method stub
		
	}
}
