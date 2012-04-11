package com.sparkhunter.res;

import java.util.Vector;
import android.util.Log;

public class Inventory {
	private Vector<Entity> inventoryEntities = new Vector<Entity>(); // actual item list
	
	public Inventory(){
	}
	
	public Vector<Entity> getItemList(){
		return inventoryEntities;
	}
	
	public void addEntity(Entity newEntity){
		inventoryEntities.add(newEntity);
		return;
	}
	
	public boolean removeEntity(int entityID){
		int i = 0;
		boolean found = false;
		
		//scan the inventory for the matching ID, then delete it.
		try{
			while(!found){
				if(((Item) inventoryEntities.elementAt(i)).getIdentifier() == entityID){
					inventoryEntities.remove(i);
					found = true;
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			//catch when someone tries to delete an item that doesn't exist.
			Log.d("INVENTORY", "ITEM NOT FOUND");
		}
		
		return found;
	}
}
