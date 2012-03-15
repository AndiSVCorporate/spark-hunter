package com.sparkhunter.res;

public class Player {
	//critical to make player a singleton, for cross-activity usage
	private static Player singletonPlayer = new Player();
	
	private Inventory itemInventory = new Inventory();
	private Inventory sparkInventory = new Inventory();
	
	private Player() {}
	
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
