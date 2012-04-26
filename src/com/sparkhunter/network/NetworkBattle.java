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
    private boolean mTurnComplete; 
    String mAttackUsed;
	public NetworkBattle(BattleActivity activity, Spark your) {
		super(your, null);
		mActivity = activity;
		mPlayer = Player.getInstance();
		mID = mPlayer.getPlayerID();
		mEnemyID = mPlayer.getEnemyID();
		
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

	@Override
	public String attack(String aAttack, Spark attacker, String dAttack ,Spark defender){
		String retVal = "";
		if(mTurnComplete){
			retVal = super.attack(aAttack, attacker, dAttack, defender);
			mTurnComplete = false;
		}
		else{
			(new SendTurnTask()).execute(PackageStats("ATTACK",aAttack));
			mAttackUsed = aAttack;
			PollPlayer();
			mWait = new Waiting(mActivity);
			mWait.setOnDismissListener(new DialogEnd());
			mWait.show();
		}
		return retVal;
	}
	public void finishAttack(String hisAttack){
		mTurnComplete = true;
		mActivity.print(attack(mAttackUsed,mYourSpark,hisAttack,mHisSpark));
	}
	private String[] PackageStats(String ... args){
	
		String[] stats = {
				mID,
				mYourSpark.getName(),
				Integer.toString(mYourSpark.mCurHp),
				"0",
				Integer.toString(mYourSpark.getAttack()),
				"0",
				"0",
				"0"
				};
		String[] retVal =new String[stats.length + args.length];
		System.arraycopy(stats, 0, retVal, 0, stats.length);
		System.arraycopy(args, 0, retVal, stats.length, args.length);
		return retVal;
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
        		if(mResponses.length>3 && mResponses[2].contains("ATTACK")){
        			 finishAttack(mResponses[3]);
        		}
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
			{}
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
