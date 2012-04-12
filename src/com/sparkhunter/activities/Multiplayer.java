package com.sparkhunter.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.res.Entity;
import com.sparkhunter.res.Inventory;
import com.sparkhunter.res.Item;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Multiplayer extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayertab);
        
        TabHost tabHost = getTabHost();
        
        TabSpec Multiplayer = tabHost.newTabSpec("multiplayer");
        Multiplayer.setIndicator("multiplayer", getResources().getDrawable(R.drawable.multiplayertab));
        Intent Multiplayerintent = new Intent().setClass(this, MultiplayerListActivity.class);
        Multiplayer.setContent(Multiplayerintent);
        
        TabSpec friends = tabHost.newTabSpec("friends");
        friends.setIndicator("friends", getResources().getDrawable(R.drawable.friends_tab));
        Intent friendsintent = new Intent().setClass(this, FbfriendsActivity.class);
        friends.setContent(friendsintent);
        
        tabHost.addTab(Multiplayer);
        tabHost.addTab(friends);
        
	}}		
