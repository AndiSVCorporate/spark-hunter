package com.sparkhunter.res;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.sparkhunter.main.R;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//algorithms for initializeFromAssets(), checkDatabase(),
//openDatabase(), and close() courtesy of ReignDesign's Juan-Manuel Fluxa

public class SQLGameDataOpener extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
    private static final String PLAYER_TABLE_NAME = "player_data";
    private static final String GAME_TABLE_NAME = "game_data";
    private static final String COLUMN_DEFINITIONS = " (" +
    			"_id" + " INTEGER PRIMARY KEY, " +
                "e_id" + " INTEGER, " +
                "type" + " TEXT, " +
                "name" + " TEXT, " +
                "level" + " INTEGER, " +
                "exp" + " INTEGER, " +
                "max_hp" + " INTEGER, " +
                "cur_hp" + " INTEGER, " +
                "speed" + " INTEGER, " +
                "attack" + " INTEGER " +
                "defense" + " INTEGER " +
                "hp_gain" + " INTEGER " +
                "speed_gain" + " INTEGER, " +
                "attack_gain" + " INTEGER, " +
                "defense_gain" + " INTEGER, " +
                "effect" + " TEXT, " +
                "image_res" + " TEXT, " +
                "sound_res" + " TEXT);";
    
    private static final String GAME_TABLE_CREATE =
            "CREATE TABLE " + GAME_TABLE_NAME + COLUMN_DEFINITIONS;
    private static final String PLAYER_TABLE_CREATE =
    		"CREATE TABLE " + PLAYER_TABLE_NAME + COLUMN_DEFINITIONS;
    private static final String DATABASE_NAME = "spark_hunter.db";
    private static final String[] DATABASE_RES = {"spark_hunter", "/raw", "com.sparkhunter.main:"};
    private static final String DATABASE_PATH = "/data/data/com.sparkhunter.main/";
    
    private Context openerContext;
    private SQLiteDatabase database;
    
	public SQLGameDataOpener(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		openerContext = context;
	}
	
	public boolean checkDatabase(){
		//check to see if the database exists in the default path
		SQLiteDatabase db;
		
		try{
			db = SQLiteDatabase.openDatabase(DATABASE_PATH+DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
		}
		catch (SQLiteException e){
			//not gone well
			return false;
		}
		db.close();
		
		return true;
	}
	
	public void initializeFromAssets(){
		//initialize the database from the starter one found in /assets
		InputStream rawDb = null;
		OutputStream copyDb = null;
		byte[] buffer = new byte[1024]; //doing this hardstyle, yo
		int length;
		
		try{
			rawDb = openerContext.getAssets().open(DATABASE_NAME);
			copyDb = new FileOutputStream(DATABASE_PATH+DATABASE_NAME);
			
			while ((length = rawDb.read(buffer)) > 0){
				copyDb.write(buffer, 0, length);
			}
			
			//close streams
			copyDb.flush();
			copyDb.close();
			rawDb.close();
		}
		catch (Exception e){
			//catch either an IOException or a FileNotFoundException
			e.printStackTrace();
		}
	}
	
	public SQLiteDatabase open() throws SQLException{
		//open the database, or flip out trying
		if(!checkDatabase())
			initializeFromAssets();
		
		//network update logic should go in this vicinity
		
		database = SQLiteDatabase.openDatabase(DATABASE_PATH+DATABASE_NAME, 
				null, SQLiteDatabase.OPEN_READWRITE);
		return database;
	}
	
	@Override
	public synchronized void close(){
		//god help you if you're trying to actually use this stuff multi-threaded
		if(database != null){
			database.close();
		}
		
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		//something's gone way wrong if this has been called
		arg0.execSQL(PLAYER_TABLE_CREATE);
		arg0.execSQL(GAME_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
