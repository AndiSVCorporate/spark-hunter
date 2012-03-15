package com.sparkhunter.main;

import java.util.Vector;

import com.sparkhunter.res.Entity;
import com.sparkhunter.res.Inventory;
import com.sparkhunter.res.Item;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class InventoryScreen extends Activity {
	private Inventory items;
	private Inventory sparks;
	
	//TODO GET RID OF ALL THE ITEM CASTS

	@Override
    public void onCreate(Bundle savedInstanceState) {
		//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
        
        //tie into player class, and grab inventories
        items = Player.getInstance().getItemInventory();
        sparks = Player.getInstance().getSparkInventory();
        
        Log.d("DEBUG", "inventories linked.");
        
        //setup the gridview for all the items
        GridView itemGridView = (GridView) findViewById(R.id.iteminventorygrid);
        itemGridView.setAdapter(new ItemAdapter(this, items)); //CHANGE
        
        itemGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	//useItem calls go here, in general
            	//need a way to ID which item is which, except not really
            	
            	((Item) items.getItemList().elementAt(position)).useItem(InventoryScreen.this, 0);
            }
        });
        
        //setup the gridview for all the items
		GridView sparkGridView = (GridView) findViewById(R.id.sparkinventorygrid);
		sparkGridView.setAdapter(new ItemAdapter(this, sparks)); //CHANGE
		
		sparkGridView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    	//useItem calls go here, in general
		    	//need a way to ID which item is which, except not really
		    	
		    	//sparks.getItemList().elementAt(position).useItem(InventoryScreen.this, 0);
		    }
		});
	}
	
	private class ItemAdapter extends BaseAdapter {
		//essentially reads the inventory and displays it.
	    private Context itemContext;
	    private Integer[] thumbIds;
	    private Inventory dispInventory;

	    public ItemAdapter(Context c, Inventory inv) {
	        itemContext = c;
	        dispInventory = inv;
	        
	        Vector<Entity> itemList = dispInventory.getItemList();
	        thumbIds = new Integer[itemList.size()];

	        //read in the inventory
	        for(int i = 0; i < itemList.size(); i++){
	        	thumbIds[i] = ((Item) itemList.elementAt(i)).getResourceID();
	        }
	    }

	    public int getCount() {
	        return thumbIds.length;
	    }

	    public Item getItem(int position) {
	        return (Item) dispInventory.getItemList().elementAt(position);
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    //create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(itemContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(10, 10, 10, 10);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(thumbIds[position]);
	        return imageView;
	    }
	}
}

