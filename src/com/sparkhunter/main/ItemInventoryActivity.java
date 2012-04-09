package com.sparkhunter.main;

import com.sparkhunter.res.EntityAdapter;
import com.sparkhunter.res.Inventory;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemInventoryActivity extends Activity {
	private Inventory items;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_inventory);
        
        items = Player.getInstance().getItemInventory();

        if(items != null)
        	Log.d("DEBUG", "items not null");
        
        //setup the gridview for all the items
        GridView itemGridView = (GridView) findViewById(R.id.iteminventorygrid);
        
        if(itemGridView == null)
        	Log.d("DEBUG", "WARNING: itemGridView null");
        else
        	Log.d("DEBUG", "itemGridView -not- null");
        
        itemGridView.setAdapter(new EntityAdapter(this, items));
        
        itemGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	
            	//activate the items
            	items.getItemList().elementAt(position).activate(ItemInventoryActivity.this, 0);
            }
        });
    }

}
