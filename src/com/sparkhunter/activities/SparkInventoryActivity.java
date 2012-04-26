package com.sparkhunter.activities;

import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.res.EntityAdapter;
import com.sparkhunter.res.Inventory;
import com.sparkhunter.res.Player;
import com.sparkhunter.res.Spark;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class SparkInventoryActivity extends Activity {
	private Inventory sparks;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spark_inventory);
		
        sparks = Player.getInstance().getSparkInventory();
	    
	    //setup the gridview for all the items
		GridView sparkGridView = (GridView) findViewById(R.id.sparkinventorygrid);
		sparkGridView.setAdapter(new EntityAdapter(this, sparks)); //CHANGE
		
		sparkGridView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    	
		    	//do something with the spark
		    	sparks.getEntityList().elementAt(position).activate(SparkInventoryActivity.this, 0);
		    	Player.getInstance().setActiveSpark(
		    			(Spark) sparks.getEntityList().elementAt(position));
		    }
		});
    }
}
