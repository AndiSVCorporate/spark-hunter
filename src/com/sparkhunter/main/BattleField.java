package com.sparkhunter.main;

/**
 * A class that contains information about battle locations.
 * 
 * @author Divyang
 * 
 */
public class BattleField {
	String battle_Name;
	double battle_Lat;
	double battle_Lon;
	String battle_city;

	/**
	 * A Constructor to initialize the BattleField objects.
	 * 
	 * @param battle
	 *            Contains the name of Sparks battling.
	 * @param lat
	 *            The Latitude of the battle.
	 * @param lon
	 *            The Longitude of the battle.
	 */
	public BattleField(String battle, double lat, double lon, String city) {
		battle_Name = battle;
		battle_Lon = lon;
		battle_Lat = lat;
		battle_city=city;
	}

	/**
	 * A getter method to get the name of Sparks battling.
	 * 
	 * @return The name of Sparks involved in the battle.
	 */
	public String getBattle_Name() {
		return battle_Name;
	}

	/**
	 * A getter method to get the latitude of the battle Location
	 * 
	 * @return The Latitude of the battle location.
	 */
	public double getBattle_Lat() {
		return battle_Lat;
	}

	/**
	 * A getter method to get the Longitude of the battle Location.
	 * 
	 * @return The Longitude of the battle Location.
	 */
	public double getBattle_Lon() {
		return battle_Lon;
	}
	
	public String getCity(){
		return battle_city;
	}

}
