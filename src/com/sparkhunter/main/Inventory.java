package com.sparkhunter.main;

import java.util.Vector;
import android.util.Log;
import com.sparkhunter.res.Item;

public class Inventory {
	//TODO loads/store inventory locally
	//TODO figure out how to interface this with the damn activity
	private Vector<Item> inventoryItems; // actual item list
	
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
