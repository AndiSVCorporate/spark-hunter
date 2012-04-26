package com.sparkhunter.activities;

import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.res.Player;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelUp extends Activity {

	private Activity mActivity = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levelup);
		
		TextView name = (TextView)findViewById(R.id.name);
		name.setText((CharSequence) 
				Player.getInstance().getActiveSpark().getName());
		
		TextView info = (TextView)findViewById(R.id.info);
		info.setText((CharSequence) 
				Player.getInstance().getActiveSpark().getDescript());
		
		TextView level = (TextView)findViewById(R.id.level);
		level.setText((CharSequence) Integer.toString(
				Player.getInstance().getActiveSpark().getLevel()));
		
		TextView exp = (TextView)findViewById(R.id.exp);
		exp.setText((CharSequence) Integer.toString(
				Player.getInstance().getActiveSpark().getExperience()));
		
		TextView attack = (TextView)findViewById(R.id.attack);
		attack.setText((CharSequence) Integer.toString(
				Player.getInstance().getActiveSpark().getAttack()));
		
		TextView defence = (TextView)findViewById(R.id.defence);
		defence.setText((CharSequence) Integer.toString(
				Player.getInstance().getActiveSpark().getDefense()));
		
		TextView speed = (TextView)findViewById(R.id.speed);
		speed.setText((CharSequence) Integer.toString(
				Player.getInstance().getActiveSpark().getSpeed()));
		
		TextView HP = (TextView)findViewById(R.id.hp);
		HP.setText((CharSequence) Integer.toString(
				Player.getInstance().getActiveSpark().getCurHp()));
		
		Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//Intent i = new Intent(LevelUp.this, SparkHunterTitleScreen.class);			
				mActivity.finish();
				//startActivity(i);
			}
		});
		
	ImageView image = (ImageView) findViewById(R.id.IVdisplay);
	image.setImageResource(Player.getInstance().getActiveSpark().getImageResId());
}
}
