package com.sparkhunter.network;


import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.sparkhunter.activities.BattleActivity;

import com.sparkhunter.main.Waiting;
import com.sparkhunter.res.Battle;
import com.sparkhunter.res.Player;
import com.sparkhunter.res.Spark;




public class NetworkBattle extends Battle {
	private Dialog mWait;
	BattleActivity mActivity; //need this to pop up waiting windows and what not
    private final ScheduledExecutorService scheduler =
   	     Executors.newScheduledThreadPool(1);
    private ScheduledFuture pollSched;
    
    String mEnemyID;
    String mID;
    Player mPlayer;
	public NetworkBattle(BattleActivity activity, Spark your) {
		super(your, null);
		mActivity = activity;
		mPlayer = Player.getInstance();
		mID = mPlayer.playerID;
		mEnemyID = mPlayer.enemyID;
		
		(new SendTurnTask()).execute(mID,mYourSpark.getName(),Integer.toString(mYourSpark.mCurHp));
		
		PollPlayer();
		mWait = new Waiting(mActivity);
		mWait.setOnDismissListener(new DialogEnd());
		mWait.show();
	}
	
	private class DialogEnd implements DialogInterface.OnDismissListener{

		@Override
		public void onDismiss(DialogInterface dialog) {
			
			//Stop polling
			Runnable r = new Runnable(){
				public void run() {pollSched.cancel(true);}};
			r.run();
			(new DeleteTurnTask()).execute(mEnemyID);
			mActivity.refresh();
		}
		
	}
	
	private void PollPlayer(){
		
		
		Runnable pollRun = new Runnable(){

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				(new RecvTurnTask()).execute(mEnemyID);
				
				
			}
			
		};
		
		pollSched = scheduler.scheduleAtFixedRate(pollRun, 5 , 5, SECONDS);
	    //kills it after x amount of time
		scheduler.schedule(new Runnable() {
	         public void run() { pollSched.cancel(true); }
	       }, 60, SECONDS);
	}
	
    private class RecvTurnTask extends PHPTask {

		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.getStats(args);
		}
		
        @Override
        protected void PostExecute(){
        	if(mResponses.length>1){
        		mHisSpark = new Spark(mResponses[0]);
        		mHisSpark.setCurHp(Integer.parseInt(mResponses[1]));
        		mWait.dismiss();
        	}
        }
    }
    private class SendTurnTask extends PHPTask {
		@Override
		protected String ServerCommand(String[] args){
				return ServerInterface.sendStats(args);
		}
		@Override
		protected void PostExecute(){
			if(mResponses[0] != null)
				Toast.makeText(mActivity.getApplicationContext(), mResponses[0], Toast.LENGTH_SHORT).show();
		}
    }
    private class DeleteTurnTask extends PHPTask {
		@Override
		protected String ServerCommand(String[] args){
				return ServerInterface.deleteMove(args);
		}
		@Override
		protected void PostExecute(){
			
		}
    }
}
