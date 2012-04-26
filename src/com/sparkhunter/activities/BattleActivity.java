package com.sparkhunter.activities;

import java.io.IOException;

import com.sparkhunter.main.BattleField;
import com.sparkhunter.main.R;
import com.sparkhunter.mapping.BattleHistoryView;
import com.sparkhunter.mapping.Geodecoder;
import com.sparkhunter.mapping.HistoryWriter;
import com.sparkhunter.network.NetworkBattle;
import com.sparkhunter.res.Ability;
import com.sparkhunter.res.Battle;
import com.sparkhunter.res.GameAudioManager;
import com.sparkhunter.res.Player;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;

public class BattleActivity extends Activity {
	private BattleActivity mActivity;
	private Battle mBattle;
	ProgressBar mLeftBar;
	ProgressBar mRightBar;

	private boolean mEnd = false;
	static TextView mBattleLog;

	// members for location functionality
	private LocationManager sparklocman;
	private LocationListener sparkloclistener = new SparkLocationListener();
	private HistoryWriter hw;
	private Location currentLocation;
	private String current_city="";
	private double latitude, longitude;

	private Spark playerSpark;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;

		// For location updates
		hw = new HistoryWriter(this);
		sparklocman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// getReverseGeocode();

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.battlescreen);
		
		mLeftBar = (ProgressBar) findViewById(R.id.leftHP);
		mRightBar = (ProgressBar) findViewById(R.id.rightHP);
		
		playerSpark = Player.getInstance().getActiveSpark();

		// Adding Battle Location to Overlays
		sparklocman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
				0, sparkloclistener);
		// sparklocman.removeUpdates(sparkloclistener);

		//BATTLE MOOSIC
        GameAudioManager.getInstance().setBackground(getApplicationContext(), R.raw.mlp_rainbowdash);

        //Player's chosen Spark is grabbed here.
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
                if(extras.getBoolean("MP")==true)
                        mBattle = new NetworkBattle(mActivity, playerSpark);
        }
        else{
                //random spark generation should occur here
                mBattle = new Battle(playerSpark, new Spark("Poke-man",R.drawable.item_diamond));
        }


		// setup log
		mBattleLog = (TextView) findViewById(R.id.battleLog);
		print("BATTLE START");

		initializeAttackMenu();
		
		Button b = (Button) findViewById(R.id.battleInventory);
		b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
                
				Intent i = new Intent(mActivity, InventoryScreen.class);
				startActivity(i);
			}
		});
		
		b = (Button) findViewById(R.id.battleRun);
		b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");
				
				if (mBattle.run()) {
					print("Got away succesfully.");
					endBattle();
				} else {
					print("Run failed.");
					print(mBattle.attack("Poke", mBattle.mHisSpark,
							mBattle.mYourSpark, false));
					endBattle();
				}
			}

		});
		
		// Option to see the battle history
		b = (Button) findViewById(R.id.battlehistory);
		b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				GameAudioManager.getInstance().playEffect("click");

				Intent i = new Intent(mActivity, BattleHistoryView.class);
				startActivity(i);
			}
		});

		ProgressDialog dialog;
		dialog = ProgressDialog.show(mActivity, "Please wait", "Getting Location");
		long i=500000;
		while(i!=0){
			i--;
		}
		dialog.cancel();
		refresh();

	}

	private void initializeAttackMenu() {
		Spinner s = (Spinner) findViewById(R.id.battleAttack);
		ArrayAdapter<CharSequence> adapter;
		adapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item,
				Ability.extractChar(mBattle.mYourSpark.getAbilities()));
		s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View v,
					int pos, long id) {
				if (parentView.getSelectedItemPosition() > 0) {
					GameAudioManager.getInstance().playEffect("click");
					
					print(mBattle.attack(((TextView) v).getText().toString(),
							mBattle.mYourSpark, mBattle.mHisSpark, true));
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

	public void print(String text) {
		mBattleLog.append(text + "\n");
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onBackPressed() {
		return;
	}

	public void refresh() {
		// setup names
		TextView temp = (TextView) findViewById(R.id.leftName);
		temp.setText(mBattle.mYourSpark.getName());
		temp = (TextView) findViewById(R.id.leftlevel);
		temp.setText(Integer.toString(mBattle.mYourSpark.getLevel()));
		temp = (TextView) findViewById(R.id.rightName);
		temp.setText(mBattle.mHisSpark.getName());
		temp = (TextView) findViewById(R.id.rightlevel);
		temp.setText(Integer.toString(mBattle.mHisSpark.getLevel()));

		mLeftBar.setMax(playerSpark.getMaxHp());
		mRightBar.setMax(100);

		ImageView temp2 = (ImageView) findViewById(R.id.leftImage);
		temp2.setImageResource(mBattle.mYourSpark.getImageResId());
		temp2 = (ImageView) findViewById(R.id.rightImage);
		temp2.setImageResource(mBattle.mHisSpark.getImageResId());

		mLeftBar.setProgress(mBattle.mYourSpark.mCurHp);
		mRightBar.setProgress(mBattle.mHisSpark.mCurHp);
		if (mBattle.mHisSpark.mCurHp <= 0) {
			print(mBattle.setWin());
			playerSpark.gainExp();
			if (playerSpark.getExperience() >= 100) {
				playerSpark.LevelUp();
				Intent i = new Intent(BattleActivity.this, LevelUp.class);
				startActivity(i);
			}
			endBattle();
		} else if (mBattle.mYourSpark.mCurHp <= 0) {
			print(mBattle.setLose());
			endBattle();
		}
	}

	/**
	 * A class that gets the location of a battle so that it can be used to
	 * create a map of all battles
	 * 
	 * @author Divyang
	 * 
	 */
	public class SparkLocationListener implements LocationListener {
		// private static int marked;
		@Override
		/**
		 * A method that writes the location when a battle starts.
		 */
		public void onLocationChanged(Location loc) {
			latitude = loc.getLatitude();
			longitude = loc.getLongitude();
			currentLocation = loc;
			if (currentLocation != null) {
				RevGeoCodeLookupTask task = new RevGeoCodeLookupTask();
				task.applicationContext = mActivity;
				task.execute();
			}
			
			//while(current_city==""){
				
		//	}
			//Toast toast =Toast.makeText(mActivity, current_city, Toast.LENGTH_LONG);
			//toast.show();
			String battle = "Battled " + mBattle.mHisSpark.getName() + " with "
					+ mBattle.mYourSpark.getName();
/*			
			BattleField bf = new BattleField(battle, latitude, longitude, current_city);
		
			// Writing to a file using the HistoryWriter class.
			try {
				hw.write(bf);
			} catch (IOException except) {
				except.printStackTrace();
			}
*/			sparklocman.removeUpdates(sparkloclistener);

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

	/**
	 * @author Divyang A class to reverse the latitude and longitude location
	 *         inot the name of a city.
	 */
	public class RevGeoCodeLookupTask extends AsyncTask<Void, Void, String> {

		private ProgressDialog pdialog;
		protected Context applicationContext = mActivity;

		/**
		 * 
		 */
		public RevGeoCodeLookupTask() {
			
		}
		
		@Override
		protected void onPreExecute(){
			this.pdialog = ProgressDialog.show(applicationContext, "Please Wait", "Getting Location");
		}


		@Override
		protected String doInBackground(Void... arg0) {
			String city = "";
			if (currentLocation != null) {
				city = Geodecoder.reverseGeocode(currentLocation);
			}
			return city;
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			this.pdialog.cancel();
			current_city = result;
			
			/*Toast toast = Toast.makeText(applicationContext, "Locality: "
					+ result, Toast.LENGTH_SHORT);
			toast.show();
			*/String battle = "Battled " + mBattle.mHisSpark.getName() + " with "
					+ mBattle.mYourSpark.getName();
			BattleField bf = new BattleField(battle, latitude, longitude, current_city);
			
			// Writing to a file using the HistoryWriter class.
			try {
				hw.write(bf);
			} catch (IOException except) {
				except.printStackTrace();
			}
			
			//current_city = result;
		}

	}

	public void endBattle() {
		Spinner s = (Spinner) findViewById(R.id.battleAttack);
		s.setClickable(false);

		Button b = (Button) findViewById(R.id.battleRun);
		b.setText("Leave Battle");
		b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mActivity.finish();
			}

		});

	}

}
