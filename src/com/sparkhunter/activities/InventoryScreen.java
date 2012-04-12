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

public class InventoryScreen extends TabActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Reusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, ItemInventoryActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("items").setIndicator("Items",
                          res.getDrawable(R.drawable.item_tab))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, SparkInventoryActivity.class);
        spec = tabHost.newTabSpec("sparks").setIndicator("Sparks",
                          res.getDrawable(R.drawable.spark_tab))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(1);
	}
}

