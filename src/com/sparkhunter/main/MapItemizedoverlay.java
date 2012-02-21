/**
 * 
 */
package com.sparkhunter.main;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author Divyang
 *
 */
public class MapItemizedoverlay extends ItemizedOverlay {

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	Context mapcontext;
	/**
	 * @param defaultMarker
	 */
	public MapItemizedoverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public MapItemizedoverlay(Drawable defaultMarker, Context context){
		super(boundCenterBottom(defaultMarker));
		mapcontext=context;
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int overlayIndex) {
		return mapOverlays.get(overlayIndex);
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		return mapOverlays.size();
	}
/**
 * A method to add Overlay Item to the Arraylist
 * @param OI	The Overlay Item to be added.
 */
	public void addOverlay(OverlayItem OI){
		mapOverlays.add(OI);
		populate();
	}
	
	protected boolean onTap(int index){
		OverlayItem Oitem = mapOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mapcontext);
		dialog.setTitle(Oitem.getTitle());
		dialog.setMessage(Oitem.getSnippet());
		dialog.show();
		return true;
	}
}
