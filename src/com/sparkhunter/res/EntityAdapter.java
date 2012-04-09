package com.sparkhunter.res;

import java.util.Vector;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class EntityAdapter extends BaseAdapter {
	//essentially reads the inventory and displays it.
    private Context itemContext;
    private Integer[] thumbIds;
    private Inventory dispInventory;

    public EntityAdapter(Context c, Inventory inv) {
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