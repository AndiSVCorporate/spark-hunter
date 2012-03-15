package com.sparkhunter.res;

import java.util.Vector;
import android.util.Log;

//TODO make static and connect to master game state
//TODO verify that state changes in InventoryScreen don't mess with this
public class Inventory {
	//TODO loads/store inventory locally
	private Vector<Entity> inventoryItems = new Vector<Entity>(); // actual item list
	
	public Inventory(){
	}
	
	public Vector<Entity> getItemList(){
		return inventoryItems;
	}
	
	//TODO refactor to something more generic
	public void addItem(Entity newItem){
		inventoryItems.add(newItem);
		return;
	}
	
	public boolean removeItem(int itemID){
		int i = 0;
		boolean found = false;
		
		//scan the inventory for the matching ID, then delete it.
		try{
			while(!found){
				//TODO make this work for sparks too, things'll get ugly otherwise
				if(((Item) inventoryItems.elementAt(i)).getIdentifier() == itemID){
					inventoryItems.remove(i);
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
