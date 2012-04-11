package com.sparkhunter.res;

import com.sparkhunter.main.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLGameDataOpener extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1; //what does this do?
    private static final String PLAYER_TABLE_NAME = "player_data";
    private static final String GAME_TABLE_NAME = "game_data";
    private static final String COLUMN_DEFINITIONS = " (" +
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
                "effect" + " TEXT);";
    private static final String GAME_TABLE_CREATE =
            "CREATE TABLE " + GAME_TABLE_NAME + COLUMN_DEFINITIONS;
    private static final String PLAYER_TABLE_CREATE =
    		"CREATE TABLE " + PLAYER_TABLE_NAME + COLUMN_DEFINITIONS;
    
	public SQLGameDataOpener(Context context) {
		super(context, "spark_hunter.db", null, DATABASE_VERSION);
		// probably need to use a Resources object to resolve R.strings
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		//TODO validate that these create usable tables
		//TODO make these yank from a remote database to create the initial table
		arg0.execSQL(PLAYER_TABLE_CREATE);
		arg0.execSQL(GAME_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
