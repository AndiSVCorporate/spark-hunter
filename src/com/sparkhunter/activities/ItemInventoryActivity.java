package com.sparkhunter.activities;

import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.res.EntityAdapter;
import com.sparkhunter.res.Inventory;
import com.sparkhunter.res.Item;
import com.sparkhunter.res.Player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ItemInventoryActivity extends Activity {
	private Inventory items;
	private static final int DESCRIPTION_DIALOG = 0;
	
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
            	Item selected = (Item)items.getItemList().elementAt(position);
            	String itemInformation = selected.toString();
            	//activate the items
            	selected.activate(ItemInventoryActivity.this, 0);
            	//showDialog() goes here (eventually move to OnItemLongClick thingy)
            	//showDialog(DESCRIPTION_DIALOG);
            	
            	//make a long toast with relevant info
            	Toast infoPopup = Toast.makeText(ItemInventoryActivity.this, itemInformation, Toast.LENGTH_LONG);
            	infoPopup.show();
            }
        });
    }
    
    /*
    public Dialog onCreateDialog(int dialogId){
    	AlertDialog.Builder dBuilder = new AlertDialog.Builder(ItemInventoryActivity.this);
    	String message
    }
    
    public void onPrepareDialog(int dialogId, Dialog dialog){
    	
    }
	*/
}
