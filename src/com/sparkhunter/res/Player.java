package com.sparkhunter.res;

import java.util.NoSuchElementException;

import android.content.Context;
import android.util.Log;

public class Player {
	//critical to make player a singleton, for cross-activity usage
	private static Player singletonPlayer = new Player();
	
	private String playerID;
	private String playerName;
	private String enemyID;
	
	private Inventory itemInventory = new Inventory();
	private Inventory sparkInventory = new Inventory();
	private Spark activeSpark; //Spark participating in battle
	
	private Player() {}
	
	//So much cleaner now that that damn SQL's abstracted away
	public void initializeInventory(Context c) {
		GameDataManager manager = new GameDataManager(c);
		
		itemInventory = manager.getAllPlayerItems();
		sparkInventory = manager.getAllPlayerSparks();
		
		manager.close();
		
		try{
			setActiveSpark((Spark) sparkInventory.getEntityList().firstElement());
		}
		catch(NoSuchElementException e){
			//spark inventory's empty, user needs to get a starter
			Log.d("DEBUG", "Player has no Sparks at the moment.");
		}
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

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getEnemyID() {
		return enemyID;
	}

	public void setEnemyID(String enemyID) {
		this.enemyID = enemyID;
	}

	public Spark getActiveSpark() {
		return activeSpark;
	}

	public void setActiveSpark(Spark activeSpark) {
		this.activeSpark = activeSpark;
	}
}
