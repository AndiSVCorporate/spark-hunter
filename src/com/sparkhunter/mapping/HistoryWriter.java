/**
 * 
 */
package com.sparkhunter.mapping;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sparkhunter.main.BattleField;

import android.content.Context;

/**
 * A class the records the battle location on the phone to be recreated on a
 * map.
 * 
 * @author Divyang
 * 
 */
public class HistoryWriter {
	private Context battleContext;
	private FileOutputStream fos;
	private final String FILE_NAME = "battle_field_locations";

	/**
	 * Constructor to initialize the Writer objects
	 * 
	 * @param con
	 *            Context of Activity
	 */
	public HistoryWriter(Context con) {
		battleContext = con;
	}

	/**
	 * Writes the Location of battles on to a file.
	 * 
	 * @param bf
	 *            An object of BattleField Class
	 * @throws IOException
	 *             Exception thrown When there is a problem Writing something to
	 *             file.
	 * @throws FileNotFoundException
	 *             Exception thrown when the File to be written to is not found.
	 */
	public void write(BattleField bf) throws FileNotFoundException, IOException {
		fos = battleContext.openFileOutput(FILE_NAME, Context.MODE_APPEND);
		String battledetail = bf.getBattle_Name()+":"+bf.getBattle_Lat()+":"+bf.getBattle_Lon()+"\n";
		fos.write(battledetail.getBytes());
		fos.close();
	}

}
