package com.sparkhunter.activities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.sparkhunter.main.R;
import com.sparkhunter.main.Waiting;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.network.MultiplayerTask;
import com.sparkhunter.network.ServerInterface;
import com.sparkhunter.res.FacebookUtils;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static java.util.concurrent.TimeUnit.*;


//TODO: no mortal eyes should have to witness my abomination @author Travis
//Shows the network game screen, allows players to create and join each other's games
public class MultiplayerListActivity extends Activity {

	public Activity mActivity;
    public String mName = null;//TODO: Put this in an initialize method on login and store it
    public String mID = null;
    public String mIDenemy = null;
    public ListView mBattleList;
    
    private boolean battleFound;
    
    private Dialog mWait;
    
    private ScheduledFuture pollSched;
    
    private final ScheduledExecutorService scheduler =
    	     Executors.newScheduledThreadPool(1);
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlelist);
		mActivity = this;
		mBattleList = (ListView) findViewById(R.id.battleListList);
		boolean battleFound = false;
        Button b = (Button)findViewById(R.id.battleListStartMatch);
        b.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {

				(new AddBattleTask()).execute(mID,mName);
				PollPlayer();
				mWait = new Waiting(mActivity);
				mWait.setOnDismissListener(new DialogEnd());
				mWait.show();
				
			}
		});
        b = (Button)findViewById(R.id.battleListGetMatch);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		        (new GetListTask()).execute();
			}
		});
        
        if (FacebookUtils.isSessionValid()) {
        	FacebookUtils.getMe(new FBListener());
        }
        
	}
	
	
	private void StartBattle(){
		//get rid of the battle on the list
		
		//we need this for passing battle data back and forth
		Player ash = Player.getInstance();
		ash.playerID =mID;
		ash.enemyID = mIDenemy;
		
		(new DeleteBattleTask()).execute(mID);
		battleFound = false;
		
		Intent i = new Intent(mActivity, BattleActivity.class);
		startActivity(i);
	}
	
	
	
	//Clean up after Dialog Ends
	private class DialogEnd implements DialogInterface.OnDismissListener{

		@Override
		public void onDismiss(DialogInterface dialog) {
			
			//Stop polling
			Runnable r = new Runnable(){
				public void run() {pollSched.cancel(true);}};
			r.run();
			
			//Delete the Match
			(new DeleteBattleTask()).execute(mID);
			if(battleFound){
				StartBattle();
				
			}
		}
		
	}
	public class FBListener implements RequestListener {

		@Override
		public void onComplete(String response, Object state) {
			try {
				JSONObject json = Util.parseJson(response);
				mName=json.getString("name");
				mID=json.getString("id");
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (FacebookError e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onIOException(IOException e, Object state) {
		}

		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
		}

		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
		}

		@Override
		public void onFacebookError(FacebookError e, Object state) {
		}
		
	}
	/*
	public class PollRunImpl implements Runnable{
		AsyncTask task;
		String id;
		public PollRunImpl(AsyncTask task, String id){
			this.task=task;
			this.id = id;
		}
		@Override
		public void run() {
			task.execute(id);
		}
		
	}
	
	//
	private void Poll(AsyncTask task, String id){
		Runnable pollRun = new PollRunImpl(task,id);
		
		pollSched = scheduler.scheduleAtFixedRate(pollRun, 5 , 5, SECONDS);
	    //kills it after x amount of time
		scheduler.schedule(new Runnable() {
	         public void run() { pollSched.cancel(true); }
	       }, 60, SECONDS);
	}*/
	
	
	
	private class BattleClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos,
			long id) {
			//When clicked, try to join the battle
			(new JoinBattleTask()).execute(((TextView) view).getText().toString(),mID);
		
		}
	
	}
	//Copy pasted code, threads were giving issues
	private void PollHost(){
		Runnable pollRun = new Runnable(){

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				(new PollHostTask()).execute(mIDenemy);
				
			}
			
		};
		
		pollSched = scheduler.scheduleAtFixedRate(pollRun, 5 , 5, SECONDS);
	    //kills it after x amount of time
		scheduler.schedule(new Runnable() {
	         public void run() { pollSched.cancel(true); }
	       }, 60, SECONDS);
	}
	private void PollPlayer(){
		Runnable pollRun = new Runnable(){

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				(new PollPlayerTask()).execute(mID);

				
			}
			
		};
		
		pollSched = scheduler.scheduleAtFixedRate(pollRun, 5 , 5, SECONDS);
	    //kills it after x amount of time
		scheduler.schedule(new Runnable() {
	         public void run() { pollSched.cancel(true); }
	       }, 60, SECONDS);
	}
	
    private class GetListTask extends MultiplayerTask {

		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.getList();
		}

		@Override
        protected void PostExecute() {
            ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1, mResponses);
            mBattleList.setAdapter(newAdapter);
            mBattleList.setOnItemClickListener(new BattleClickListener());
		}

    }
    
    //Use to spawn thread
    private class AddBattleTask extends MultiplayerTask {

		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.addBattle(args[0],args[1]);
		}

		@Override
		protected void PostExecute(){
			
		}

    }
    private class JoinBattleTask extends MultiplayerTask {

        protected void onPostExecute(String objResult) {
                if(objResult != null && objResult instanceof String) {                          
                        String result = (String) objResult;
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        //Get the hosts ID
                        mIDenemy=result;
                        //And poll him to make sure he is ready
        				//PollHost();
        				//mWait = new Waiting(mActivity);
        				//mWait.setOnDismissListener(new DialogEnd());
        				//mWait.show();
                    	battleFound = true;
                    	StartBattle();
                }
        }

		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.joinBattle(args[0],args[1]);
		}

    }
    //Handshaking, Host is looking for players
    private class PollPlayerTask extends MultiplayerTask {

		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.pollPlayers(args[0]);
		}
		
        @Override
        protected void PostExecute(){
        	if(mResponses.length>1)
        	{
           		mIDenemy = mResponses[1];
           		mWait.dismiss();
           		battleFound = true;
        	}
        }
    }
    //Handshaking, Player is waiting for the host to say the battle is ready
	public class PollHostTask extends MultiplayerTask {
		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.checkBattle(args[0]);
		}

        @Override
        protected void PostExecute(){
            //if(mResponses.length>1){
        		//mWait.dismiss();
        		//battleFound = true;
        	//}
        	Toast.makeText(getApplicationContext(), mResponses[0], Toast.LENGTH_SHORT).show();
        }
	}
    private class DeleteBattleTask extends MultiplayerTask {
		@Override
		protected String ServerCommand(String[] args){
				return ServerInterface.deleteBattle(args[0]);
		}
    }
    
    //Handshaking, Host is telling player that battle is starting
    
    private class StartBattleTask extends MultiplayerTask {
		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.startBattle(args[0]);
		}
    }
}
