package com.sparkhunter.res;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class GameDataManager {
	private Context currentContext;
	private SQLiteDatabase database;
	private SQLGameDataOpener databaseOpener;
    private static final String DATABASE_NAME = "spark_hunter.db";
    private static final String[] DATABASE_RES = {"spark_hunter", "/raw", "com.sparkhunter.main:"};
    private static final String DATABASE_PATH = "/data/data/com.sparkhunter.main/databases/";
	private static final String PLAYER_TABLE = "player_data";
	
	public GameDataManager(Context c){
		databaseOpener = new SQLGameDataOpener(c);
		currentContext = c;
		
		//use it get a handle on the main database
		database = databaseOpener.open();
	}
	
	//returns player's item inventory
	
	//split off some helper methods from this
	public Inventory getAllPlayerItems(){
		Cursor query = generateQuery(PLAYER_TABLE, "type == 'ITEM'");
		
		return readInventory(query);
	}
	
	public Inventory getAllPlayerSparks(){
		Cursor query = generateQuery(PLAYER_TABLE, "type == 'SPARK'");
		
		return readInventory(query);
	}
	
	public void saveInventory(Inventory inv){
		ContentValues entityValues = new ContentValues();
		Vector<Entity> entities = inv.getEntityList();
		Vector<Entity> mutableEntities = (Vector<Entity>) entities.clone();
		Entity entity; //entity used in building a given insert
		int quantity = 1;
		boolean insert = false;
		Integer currentEID;
		
		//drop the current player data table
		database.delete(PLAYER_TABLE, null, null);
		
		//iterate through the inventory, collapsing duplicate
		//entries into singular inserts
		//iterate over the entities list, but scan the mutable list to see if 
		//the given entity is in there
		for(int i = 0; i < entities.size(); i++){
			entity = entities.get(i);
			
			//scan mutable list to see what's what
			for(int j = 0; j < mutableEntities.size(); j++){
				if(mutableEntities.get(j).getId())
			}
		}
		
		
		
	}
	
	public void close(){
		database.close();
		databaseOpener.close();
	}
	
	private Cursor generateQuery(String table, String where){
		//read in the data for the Player's item and spark inventories
		//be greedy and select all columns
		//add a WHERE for this
		Cursor databaseQuery = null;
		
		try{
			databaseQuery = database.query(table, null, where, null, null, null, null);
				
			Log.d("SQL", "Game data cursor is " + Integer.toString(databaseQuery.getCount()) + " by "
				+ Integer.toString(databaseQuery.getColumnCount()));
				
			//probably not needed
			databaseQuery.moveToFirst();
		}
		catch(SQLiteException e){
			Log.d("DEBUG", "ERROR: SQL query failed.\n" + e.getMessage());
		}
		
		return databaseQuery;
	}
	
	private Inventory readInventory(Cursor query){
		Vector<Entity> newEntities = null;
		Inventory newInventory = new Inventory();
		
		try{
			for(int i = 0; i < query.getCount(); i++){
				//grab the entities mapped by this row and cram 'em somewhere
				newEntities = EntityCreationManager.getInstance().createEntity(
						query, currentContext);
				
				//add entities to new inventory
				for(int j = 0; j < newEntities.size(); j++){
					newInventory.addEntity(newEntities.elementAt(j));
				}
				
				query.moveToNext();
			}
		}
		catch(TypeNotPresentException e){
			Log.d("DEBUG", "Error, entity is not a spark or item");
			//do something with the game state, since the player's inventory is clearly in
			//an inconsistent state!
		}
		
		return newInventory;
	}
}
