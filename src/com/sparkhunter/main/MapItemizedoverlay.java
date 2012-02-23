/**
 * 
 */
package com.sparkhunter.main;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * @author Divyang
 * 
 */
public class MapItemizedoverlay extends ItemizedOverlay  {

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	Context mapcontext;
	GestureDetector.SimpleOnGestureListener gd= new GestureDetector.SimpleOnGestureListener();
	/**
	 * @param defaultMarker
	 */
	public MapItemizedoverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public MapItemizedoverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mapcontext = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int overlayIndex) {
		return mapOverlays.get(overlayIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		return mapOverlays.size();
	}

	/**
	 * A method to add Overlay Item to the Arraylist
	 * 
	 * @param OI
	 *            The Overlay Item to be added.
	 */
	public void addOverlay(OverlayItem OI) {
		mapOverlays.add(OI);
		populate();
		
	}
	@Override
	protected boolean onTap(int index) {
		OverlayItem Oitem = mapOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mapcontext);
		dialog.setTitle(Oitem.getTitle());
		dialog.setMessage(Oitem.getSnippet());
		dialog.show();
		return true;
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		GeoPoint gp=mapView.getProjection().fromPixels((int) event.getX(),(int) event.getY());
					OverlayItem overlayitem = new OverlayItem(gp, "Test Head", "Test Body");
		        addOverlay(overlayitem);
		        return super.onTouchEvent(event, mapView);
    
		}
}
