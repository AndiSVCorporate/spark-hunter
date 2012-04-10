package com.sparkhunter.res;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Player {
	//critical to make player a singleton, for cross-activity usage
	private static Player singletonPlayer = new Player();
	
	
	
	//Im throwing these in here, dont' get mad!
	//I MAD.
	public String playerID;
	public String enemyID;
	
	private SQLiteDatabase gameData;
	private Cursor dataQuery;
	private Inventory itemInventory = new Inventory();
	private Inventory sparkInventory = new Inventory();
	
	private static final String ITEM_TABLE = "player_data"; //TODO make this a R.strings reference
	
	private Player() {}
	
	public void initializeInventory(Context c) {
		int targetInventory = 0; //default to items
		/*
		the player only has rubber ducks for now, dealwithit.jpg
		for(int i = 0; i < 5; i++){
			itemInventory.addEntity(new RubberDuck());
		}
		
		the player only has Dingus(es) for now, dealwithit.jpg
		for(int i = 0; i < 3; i++){
			sparkInventory.addEntity(new DummySpark());
		}*/
		
		//create a database opener, and use it get a handle on the player database 
		SQLGameDataOpener openHelper = new SQLGameDataOpener(c);
		gameData = openHelper.getReadableDatabase();
		
		//read in the data for the Player's item and spark inventories
		//be greedy and select all columns
		dataQuery = gameData.query(ITEM_TABLE, null, null, null, null, null, null);
		
		Log.d("SQL", "Game data cursor is " + Integer.toString(dataQuery.getCount()) + " by "
				+ Integer.toString(dataQuery.getColumnCount()));
		
		//probably not needed
		dataQuery.moveToFirst();
		
		//need to figure out what's a spark, and what's an item, and add it to the 
		//correct inventory
		try{
			for(int i = 0; i < dataQuery.getCount(); i++){
				//entities to add to the inventory
				//find a way to use Entity type-casts for this
				Item itemToAdd = new Item();
				Spark sparkToAdd = new Spark();
				Entity entityToAdd = new Item(); //This is such a fucking kludge, to the point of being painful
				
				//first two fields are directly tied to which inventory the entity goes in
				if(dataQuery.getString(1).equals("ITEM")){
					targetInventory = 0;
				}
				else{
					if(dataQuery.getString(1).equals("SPARK")){
						targetInventory = 1;
					}
					else{
						//something's waaaaaaaay wrong, throw an exception
						throw new TypeNotPresentException("Not item or spark.", new Throwable());
					}
				}
				
				for(int j = 0; j < dataQuery.getColumnCount(); j++){
					//data += dataQuery.getString(j);
					entityToAdd.setId(dataQuery.getInt(i));
				}
				
				entityToAdd.setId(dataQuery.getInt(0));
				entityToAdd.setType(dataQuery.getString(1));
				entityToAdd.setName(dataQuery.getString(2));
				entityToAdd.setLevel(dataQuery.getInt(3));
				entityToAdd.setExperience(dataQuery.getInt(4));
				entityToAdd.setMaxHp(dataQuery.getInt(5));
				entityToAdd.setCurHp(dataQuery.getInt(6));
				entityToAdd.setSpeed(dataQuery.getInt(7));
				entityToAdd.setAttack(dataQuery.getInt(8));
				entityToAdd.setDefence(dataQuery.getInt(9));
				entityToAdd.setHpGain(dataQuery.getInt(10));
				entityToAdd.setSpeedGain(dataQuery.getInt(11));
				entityToAdd.setAttackGain(dataQuery.getInt(12));
				entityToAdd.setDefenceGain(dataQuery.getInt(13));
				entityToAdd.setEffect(dataQuery.getString(14));
				
				Log.d("SQL", "STRING GOES HERE");
				dataQuery.moveToNext();
			}
		}
		catch(TypeNotPresentException e){
			Log.d("DEBUG", "Error, entity is not a spark or item");
			//do something with the game state, since the player's inventory is clearly in
			//an inconsistent state!
		}
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
