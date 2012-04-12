package com.sparkhunter.res;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class GameDataManager {
	private SQLiteDatabase database;
	private SQLGameDataOpener databaseOpener;
    private static final String DATABASE_NAME = "spark_hunter.db";
    private static final String[] DATABASE_RES = {"spark_hunter", "/raw", "com.sparkhunter.main:"};
    private static final String DATABASE_PATH = "/data/data/com.sparkhunter.main/databases/";
	
	public GameDataManager(Context c){
		databaseOpener = new SQLGameDataOpener(c);
	}
}
