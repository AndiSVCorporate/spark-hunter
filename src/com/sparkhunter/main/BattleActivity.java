package com.sparkhunter.main;

import com.sparkhunter.res.Ability;
import com.sparkhunter.res.Battle;
import com.sparkhunter.res.Map;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
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

public class BattleActivity extends Activity{
	private Activity mActivity;
	private Battle mBattle;
	ProgressBar mLeftBar;
    ProgressBar mRightBar;
    
    private boolean mEnd = false;
    static MediaPlayer bgm;
    static TextView mBattleLog;
    
	@Override
	public void onCreate(Bundle savedInstanceState){
		mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battlescreen);
        mLeftBar = (ProgressBar) findViewById(R.id.leftHP);
        mRightBar = (ProgressBar) findViewById(R.id.rightHP);
        
        mLeftBar.setMax(100);
        mRightBar.setMax(100);
        
        
        //BATTLE MOOSIC
        bgm = MediaPlayer.create(mActivity, R.raw.mlp_rainbowdash);
        bgm.start();
        
        mBattle = new Battle(GetSpark.chosenSpark,new Spark("Poke-man",R.drawable.item_diamond));
        
        //setup names
        TextView temp = (TextView) findViewById(R.id.leftName);
        temp.setText(mBattle.mYourSpark.getName());
        temp = (TextView) findViewById(R.id.rightName);
        temp.setText(mBattle.mHisSpark.getName());
        
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
		mLeftBar.setProgress(mBattle.mYourSpark.mCurHP);
		mRightBar.setProgress(mBattle.mHisSpark.mCurHP);
		if(mBattle.mHisSpark.mCurHP<=0){
			print(mBattle.setWin());
			endBattle();
		}
		else if(mBattle.mYourSpark.mCurHP<=0){
			print(mBattle.setLose());
			endBattle();
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
}
