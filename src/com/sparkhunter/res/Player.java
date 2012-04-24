package com.sparkhunter.res;

import android.content.Context;

public class Player {
	//critical to make player a singleton, for cross-activity usage
	private static Player singletonPlayer = new Player();
	
	//Im throwing these in here, dont' get mad!
	//I MAD.
	//I'm platinum mad.
	public String playerID;
	public String playerName;
	public String enemyID;
	
	private Inventory itemInventory = new Inventory();
	private Inventory sparkInventory = new Inventory();
	
	private Player() {}
	
	//So much cleaner now that that damn SQL's abstracted away
	public void initializeInventory(Context c) {
		GameDataManager manager = new GameDataManager(c);
		
		itemInventory = manager.getAllPlayerItems();
		sparkInventory = manager.getAllPlayerSparks();
		
		manager.close();
	}
	
	public void saveInventory(Context c){
		GameDataManager manager = new GameDataManager(c);
		
	}
	
	public static Player getInstance() {
		return singletonPlayer;
	}
	
	public Inventory getItemInventory() {
		return itemInventory;
	}
	
	public Inventory getSparkInventory() {
		return sparkInventory;
	}
}
