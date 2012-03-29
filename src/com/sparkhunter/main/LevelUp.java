package com.sparkhunter.main;

import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levelup);
		
		TextView name = (TextView)findViewById(R.id.name);
		name.setText((CharSequence) GetSpark.chosenSpark.getName());
		
		TextView info = (TextView)findViewById(R.id.info);
		info.setText((CharSequence) GetSpark.chosenSpark.getDescript());
		
		TextView level = (TextView)findViewById(R.id.level);
		level.setText((CharSequence) Integer.toString(GetSpark.chosenSpark.getLevel()));
		
		TextView exp = (TextView)findViewById(R.id.exp);
		exp.setText((CharSequence) Integer.toString(GetSpark.chosenSpark.getExp()));
		
		TextView attack = (TextView)findViewById(R.id.attack);
		attack.setText((CharSequence) Integer.toString(GetSpark.chosenSpark.getAttack()));
		
		TextView defence = (TextView)findViewById(R.id.defence);
		defence.setText((CharSequence) Integer.toString(GetSpark.chosenSpark.getDefence()));
		
		TextView speed = (TextView)findViewById(R.id.speed);
		speed.setText((CharSequence) Integer.toString(GetSpark.chosenSpark.getSpeed()));
		
		TextView HP = (TextView)findViewById(R.id.hp);
		HP.setText((CharSequence) Integer.toString(GetSpark.chosenSpark.getHP()));
		
	ImageView image = (ImageView) findViewById(R.id.IVdisplay);
	image.setImageResource(GetSpark.chosenSpark.mResId);
}
}
