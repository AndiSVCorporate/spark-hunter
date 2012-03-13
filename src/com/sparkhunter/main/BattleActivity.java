package com.sparkhunter.main;

import com.sparkhunter.res.Battle;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
        mBattleLog.append("BATARU SUTATO!");
        
        //setup listeners
        Button b = (Button) findViewById(R.id.attack);
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//this is really crappy, hardcoded battle scene, please forgive me
				if(mBattle.mHisSpark.mCurHP<=0){
					mBattleLog.append(("You already killed him you monster!" + "\n"));
				}
				else {
				mBattleLog.append((mBattle.mYourSpark.getName() +" attacks and does 20 damage!" + "\n"));
				mBattle.mHisSpark.mCurHP -=20;
				if(mBattle.mHisSpark.mCurHP<=0){
					mBattleLog.append((mBattle.mHisSpark.getName() +" dies a horrible death!" + "\n"));
				}
				else
				{
					mBattleLog.append((mBattle.mHisSpark.getName() +" attacks and does 1 damage!" + "\n"));
					mBattle.mYourSpark.mCurHP -=1;
				}
				}
				mBattleLog.scrollBy(0, 20);
				refresh();
				
			}
		});
        
        refresh();
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
	}
}
