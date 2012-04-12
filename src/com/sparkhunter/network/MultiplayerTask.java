package com.sparkhunter.network;

import java.util.StringTokenizer;

import android.os.AsyncTask;
import android.util.Log;

public class MultiplayerTask extends AsyncTask<String,String,String>{
	
	protected String[] mResponses;
	
	@Override
	protected String doInBackground(String ... args) {
        	return ServerCommand(args);
	}
	@Override
	protected void onPostExecute(String result) {
		
             StringTokenizer tk = new StringTokenizer(result, ",");
             mResponses = new String[tk.countTokens()];
             int i = 0;
             while(tk.hasMoreTokens()) 
            	 mResponses[i++] = tk.nextToken();
         
             if(mResponses.length>0)
            	 PostExecute();
	 }
	
	protected void PostExecute(){
		//default don't do anything
	}
	protected String ServerCommand(String[] args) {
		return null;
	}
}