package com.sparkhunter.res;

import java.util.Vector;

import android.R;
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
		int quantity = 0;
		/*
		the player only has rubber ducks for now, dealwithit.jpg
		for(int i = 0; i < 5; i++){
			itemInventory.addEntity(new RubberDuck());
		}
		
		the player only has Dingus(es) for now, dealwithit.jpg
		for(int i = 0; i < 3; i++){
			sparkInventory.addEntity(new DummySpark());
		}*/
		
		//bullshit to test resource resolver
		Log.d("DEBUG", c.getResources().getResourceName(com.sparkhunter.main.R.drawable.item_circle));
		
		//create a database opener, and use it get a handle on the player database 
		SQLGameDataOpener openHelper = new SQLGameDataOpener(c);
		gameData = openHelper.open();
		
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
				Vector<Entity> entities = new Vector<Entity>();
				
				//grab the entities mapped by this row and cram 'em somewhere
				entities = EntityCreationManager.getInstance().createEntity(dataQuery, c);
				
				//TODO add all needed getters to Entity interface
				for(int j = 0; j < entities.size(); j++){
					Log.d("DEBUG", "entity is " + entities.get(0).getType());
					
					if(entities.get(0).getType().equals("ITEM")){
						itemInventory.addEntity(entities.get(j));
					}
					else{
						//something's off with this logic, everything ends up here!
						sparkInventory.addEntity(entities.get(j));
					}
				}
				
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
