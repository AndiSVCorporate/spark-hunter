package com.sparkhunter.main;

import java.util.Vector;
import com.sparkhunter.res.Item;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ItemAdapter extends BaseAdapter {
	//essentially reads the inventory and displays it.
    private Context itemContext;
    private Inventory playerInventory;
    private Integer[] thumbIds;

    public ItemAdapter(Context c) {
        itemContext = c;
        playerInventory = new Inventory();
        Vector<Item> itemList = playerInventory.getItemList();
        thumbIds = new Integer[itemList.size()];

        //read in the inventory
        for(int i = 0; i < itemList.size(); i++){
        	thumbIds[i] = itemList.elementAt(i).getResourceID();
        }
    }

    public int getCount() {
        return thumbIds.length;
    }

    public Item getItem(int position) {
        return playerInventory.getItemList().elementAt(position);
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
    
    //
}