/**
 * 
 */
package com.sparkhunter.mapping;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sparkhunter.main.BattleField;

import android.content.Context;

/**
 * A class that reads battle Locations from a file to recreate on a map.
 * 
 * @author Divyang
 * 
 */
public class HistoryReader {
	private Context battleContext;
	private FileInputStream fis;
	private final String FILE_NAME = "battle_field_locations";

	/**
	 * A constructor that initializes the objects of the History reader class
	 * 
	 * @param con
	 *            the context
	 */
	public HistoryReader(Context con) {
		battleContext = con;
	}

	/**
	 * A method to read details of a battle from the file and create a list of
	 * BattleField class
	 * 
	 * @return the list of BattleField class.
	 * @throws FileNotFoundException
	 *             When the file to read from is not found.
	 * @throws IOException
	 *             When there is a problem reading something from the file.
	 */

	public List<BattleField> read() throws FileNotFoundException, IOException {
		fis = battleContext.openFileInput(FILE_NAME);
		List<BattleField> bf = new ArrayList<BattleField>();
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		//Reading data from file and creating the list
		String str = br.readLine();
		while (str != null) {
			//using ":" as a delimiter to separate name, latitude and longitude
			String[] elements = str.split(":");
			String name = elements[0];
			double latitude = Double.parseDouble(elements[1].trim());
			double longitude = Double.parseDouble(elements[2].trim());
			bf.add(new BattleField(name, latitude, longitude));
			str = br.readLine();
		}
		fis.close();
		return bf;
	}

}
