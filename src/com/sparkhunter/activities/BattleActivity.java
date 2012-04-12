package com.sparkhunter.activities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.sparkhunter.main.BattleField;
import com.sparkhunter.main.R;
import com.sparkhunter.main.R.drawable;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.main.R.raw;
import com.sparkhunter.mapping.BattleHistoryView;
import com.sparkhunter.mapping.HistoryWriter;
import com.sparkhunter.network.ServerInterface;
import com.sparkhunter.res.Ability;
import com.sparkhunter.res.Battle;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;


public class BattleActivity extends Activity{
	private Activity mActivity;
	private Battle mBattle;
	ProgressBar mLeftBar;
    ProgressBar mRightBar;
    
    
    private boolean mEnd = false;
    static MediaPlayer bgm;
    static TextView mBattleLog;
    
    LocationManager sparklocman; 
    LocationListener sparkloclistener = new SparkLocationListener();
    HistoryWriter hw;
 	@Override
	public void onCreate(Bundle savedInstanceState){
		 hw = new HistoryWriter(this);
		 sparklocman = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	mActivity = this;
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.battlescreen);
        mLeftBar = (ProgressBar) findViewById(R.id.leftHP);
        mRightBar = (ProgressBar) findViewById(R.id.rightHP);
        
        // Adding Battle Location to Overlays
        sparklocman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,sparkloclistener);
        //sparklocman.removeUpdates(sparkloclistener);
        
        //BATTLE MOOSIC
        bgm = MediaPlayer.create(mActivity, R.raw.mlp_rainbowdash);
        bgm.start();
        
        mBattle = new Battle(GetSpark.chosenSpark,new Spark("Poke-man",R.drawable.item_diamond));
        
        //setup names
        TextView temp = (TextView) findViewById(R.id.leftName);
        temp.setText(mBattle.mYourSpark.getName());
        temp = (TextView) findViewById(R.id.leftlevel);
        temp.setText(Integer.toString(mBattle.mYourSpark.getLevel()));
        temp = (TextView) findViewById(R.id.rightName);
        temp.setText(mBattle.mHisSpark.getName());
        temp = (TextView) findViewById(R.id.rightlevel);
        temp.setText(Integer.toString(mBattle.mHisSpark.getLevel()));
        
        mLeftBar.setMax(GetSpark.chosenSpark.getHP());
        mRightBar.setMax(100);
        
        //setup images
        ImageView temp2 = (ImageView) findViewById(R.id.leftImage);
        temp2.setImageResource(mBattle.mYourSpark.mResId);
        temp2 = (ImageView) findViewById(R.id.rightImage);
        temp2.setImageResource(mBattle.mHisSpark.mResId);
        
        //setup log
        mBattleLog = (TextView) findViewById(R.id.battleLog);
        print("BATTLE START");
        
        initializeAttackMenu();
        Button b = (Button)findViewById(R.id.battleInventory);
        
        
        
        
        
        
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				bgm.stop();
				Intent i = new Intent(mActivity, InventoryScreen.class);
				startActivity(i);
			}
		});
        b = (Button)findViewById(R.id.battleRun);
        b.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View v) {
				if(mBattle.run()){
					print("Got away succesfully.");
					endBattle();
				}
				else{
					print("Run failed.");
					print(mBattle.attack("Poke",mBattle.mHisSpark,mBattle.mYourSpark, false));
					endBattle();
				}
			}
			
		});
        
        b = (Button)findViewById(R.id.battlehistory);
        b.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				bgm.stop();
				Intent i = new Intent(mActivity, BattleHistoryView.class);
				startActivity(i);
			}
		});
        
        refresh();
        
       
	}
    
    
	private void initializeAttackMenu(){
        Spinner s = (Spinner) findViewById(R.id.battleAttack);
        ArrayAdapter<CharSequence> adapter;
        adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, Ability.extractChar(mBattle.mYourSpark.getAbilities()));
       s.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parentView, View v, int pos,
				long id) {
			if(parentView.getSelectedItemPosition()>0)
			{
				print(mBattle.attack(((TextView) v).getText().toString(),mBattle.mYourSpark,mBattle.mHisSpark, true));
				refresh();
				parentView.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}

        });
        
        s.setAdapter(adapter);
	}
	public void print(String text){
		mBattleLog.append(text + "\n");
	}

	@Override
	public void onPause(){
		//bgm.prepare();
		//bgm.pause();
		super.onPause();
	}
	
	@Override
	public void onStop(){
		//bgm.release();
		super.onStop();
	}
	@Override	
	public void onResume(){
		super.onResume();
		//bgm.start();
	}
	@Override
	public void onBackPressed() {
		   return;
	}
	public void refresh(){
		mLeftBar.setProgress(mBattle.mYourSpark.mCurHp);
		mRightBar.setProgress(mBattle.mHisSpark.mCurHp);
		if(mBattle.mHisSpark.mCurHp<=0){
			print(mBattle.setWin());
			GetSpark.chosenSpark.gainExp();
			if (GetSpark.chosenSpark.getExp() >= 100){
				GetSpark.chosenSpark.LevelUp();
				Intent i = new Intent(BattleActivity.this, LevelUp.class);
				startActivity(i);
			}
			endBattle();
		}
		else if(mBattle.mYourSpark.mCurHp<=0){
			print(mBattle.setLose());
			endBattle();
		}
	}

	
	/**
	 * A class that gets the location of a battle so that it can be used to create a map of all battles
	 * @author Divyang
	 *
	 */
	public class SparkLocationListener implements LocationListener{
		//private static int marked;
		@Override
		/**
		 * A method that writes the location when a battle starts.
		 */
		public void onLocationChanged(Location loc) {
			double latitude = loc.getLatitude();
			double longitude = loc.getLongitude();
			String battle = "Battled "+mBattle.mHisSpark.getName()+" with "+mBattle.mYourSpark.getName();
			BattleField bf = new BattleField(battle, latitude, longitude);
			
			//Writing to a file using the HistoryWriter class.
			try{
				hw.write(bf);
			}
			catch (IOException except){
				except.printStackTrace();
			}
			sparklocman.removeUpdates(sparkloclistener);
			
		}

		
		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}	
	public void endBattle(){
		Spinner s = (Spinner) findViewById(R.id.battleAttack);
		s.setClickable(false);

        Button b = (Button)findViewById(R.id.battleRun);
        b.setText("Leave Battle");
        b.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View v) {
				mActivity.finish();
			}
			
		});

	}
	
	//these shall go somewhere else soon
    private class SendStatsTask extends AsyncTask {
		@Override
		protected Object doInBackground(Object... args) {
            if(args != null && args[0] instanceof String) {
                String id = (String) args[0];
                try {
					return ServerInterface.sendStats(id);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            return null;
		}
    }
    private class GetStatsTask extends AsyncTask {
		@Override
		protected Object doInBackground(Object... args) {
            if(args != null && args[0] instanceof String) {
                String id = (String) args[0];
                try {
					return ServerInterface.getStats();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            return null;
		}
    }
}
