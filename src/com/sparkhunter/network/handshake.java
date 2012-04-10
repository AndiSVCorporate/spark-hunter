package com.sparkhunter.network;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import android.os.AsyncTask;

class Handshake {

    private ScheduledFuture pollSched;
    private final ScheduledExecutorService scheduler =
   	     Executors.newScheduledThreadPool(1);
    
	public void startConnection(String selfID, String  otherID){
		
		pollSched = scheduler.scheduleAtFixedRate(pollRun, 5 , 5, SECONDS);

	}
	public void endConnection(){
		
	}
	
	
	
	
	private class HandshakeRunnable implements Runnable {
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			//(new HandshakeTask()).execute(mID);
			
			
		}
	}
	HandshakeRunnable pollRun;
	
	/*pollSched = scheduler.scheduleAtFixedRate(pollRun, 5 , 5, SECONDS);
    //kills it after x amount of time
	scheduler.schedule(new Runnable() {
         public void run() { pollSched.cancel(true); }
       }, 60, SECONDS);
	}*/
	
    class HandshakeTask extends AsyncTask {
	public String doInBackground(Object... args) {
            if(args != null && args[0] instanceof String) {
                    String id = (String) args[0];
                    String id2 = (String) args[1];
                    try {
						return ServerInterface.joinBattle(id,id2);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            return null;
    	}
    }
}
