package com.sparkhunter.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class InventoryScreen extends Activity {

	public void onCreate(Bundle savedInstanceState) {
    	//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
        GridView gridview = (GridView) findViewById(R.id.inventory);
        gridview.setAdapter(new GridImage(this));
	}
	public class GridImage extends BaseAdapter {
	    private Context mContext;

	    public GridImage(Context context) {
	    	 mContext = context;
		}


		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mItems.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

	        ImageView imageView;
	        if (arg1 == null) 
	        {
	        	imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) arg1;
	        }

	        imageView.setImageResource(mItems[arg0]);
	        return imageView;
	    }

	    //random placeholders
	    private Integer[] mItems = {
	            R.drawable.item_circle, R.drawable.item_square,
	            R.drawable.item_square, R.drawable.item_circle,
	            R.drawable.item_circle, R.drawable.item_square,
	            R.drawable.item_circle, R.drawable.item_circle,
	            R.drawable.item_square, R.drawable.item_diamond,
	            R.drawable.item_circle, R.drawable.item_square,
	            R.drawable.item_diamond, R.drawable.item_circle,
	            R.drawable.item_square, R.drawable.item_circle,
	            R.drawable.item_diamond, R.drawable.item_diamond,
	            R.drawable.item_circle, R.drawable.item_circle,
	            R.drawable.item_square, R.drawable.item_square,
	            R.drawable.item_diamond, R.drawable.item_circle,
	            R.drawable.item_circle, R.drawable.item_diamond,
	            R.drawable.item_square, R.drawable.item_diamond,
	            R.drawable.item_circle, R.drawable.item_circle
	    };

		
	}

	public InventoryScreen() {
		// TODO figure out how to make this grab focus, also everything else
	}

}