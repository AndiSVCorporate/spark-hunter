package com.sparkhunter.main;

import java.util.Vector;
import android.util.Log;
import com.sparkhunter.res.Item;
import com.sparkhunter.res.RubberDuck;

//TODO make static and connect to master game state
public class Inventory {
	//TODO loads/store inventory locally
	private Vector<Item> inventoryItems = new Vector<Item>(); // actual item list
	
	public Inventory(){
		//the player only has rubber ducks for now, dealwithit.jpg
		for(int i = 0; i < 6; i++){
			inventoryItems.add(new RubberDuck());
		}
	}
	
	public Vector<Item> getItemList(){
		return inventoryItems;
	}
	
	public void addItem(Item newItem){
		inventoryItems.add(newItem);
		return;
	}
	
	public boolean removeItem(int itemID){
		int i = 0;
		boolean found = false;
		
		//scan the inventory for the matching ID, then delete it.
		try{
			while(!found){
				if(inventoryItems.elementAt(i).getIdentifier() == itemID){
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
