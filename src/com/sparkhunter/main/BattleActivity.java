package com.sparkhunter.main;

import com.sparkhunter.res.Ability;
import com.sparkhunter.res.Battle;
import com.sparkhunter.res.Spark;

import android.app.Activity;
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
    static MediaPlayer music;
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
        music = MediaPlayer.create(mActivity, R.raw.mlp_rainbowdash);
        music.start();
        
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
        
        refresh();
	}
	private void initializeAttackMenu(){
        Spinner s = (Spinner) findViewById(R.id.attack);
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
		music.pause();
		super.onPause();
	}
	
	@Override
	public void onStop(){
		music.release();
		super.onStop();
	}
	@Override	
	public void onResume(){
		music.start();
		super.onResume();
	}
	public void refresh(){
		mLeftBar.setProgress(mBattle.mYourSpark.mCurHP);
		mRightBar.setProgress(mBattle.mHisSpark.mCurHP);
		if(mBattle.mHisSpark.mCurHP<=0){
			print(mBattle.setWin());
			Spinner s = (Spinner) findViewById(R.id.attack);
			s.setClickable(false);
		}
		if(mBattle.mYourSpark.mCurHP<=0){
			print(mBattle.setLose());
			Spinner s = (Spinner) findViewById(R.id.attack);
			s.setClickable(false);
		}
	}
}
