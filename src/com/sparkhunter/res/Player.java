package com.sparkhunter.res;

public class Player {
	//critical to make player a singleton, for cross-activity usage
	private static Player singletonPlayer = new Player();
	
	private Inventory itemInventory = new Inventory();
	private Inventory sparkInventory = new Inventory();
	
	private Player() {}
	
	public void initializeInventory() {
		//the player only has rubber ducks for now, dealwithit.jpg
		for(int i = 0; i < 5; i++){
			itemInventory.addEntity(new RubberDuck());
		}
		
		//the player only has Dingus(es) for now, dealwithit.jpg
		for(int i = 0; i < 3; i++){
			sparkInventory.addEntity(new DummySpark());
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
