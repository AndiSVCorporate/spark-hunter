package com.sparkhunter.mapping;

//import android.app.Activity;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import com.sparkhunter.main.R;



public class Map extends MapActivity{
	private static final int ZOOM_LEVEL = 18;

	/** Called when the activity is first created. */
	private static final String MAP_KEY = "0BpOKOGaNV--pQ-Km6inD4BSIY4viUJnBo6RViQ";

	private MapController mapController;
	private MapView mapView;
	private TextView debug;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Creating the map
		setContentView(R.layout.viewmap);
		mapView = (MapView) findViewById(R.id.mapview1);
		mapView.setBuiltInZoomControls(true);
		debug = (TextView) findViewById(R.id.textViewMap);
		
		mapController = mapView.getController();
		mapController.setZoom(ZOOM_LEVEL);

		/* Using the locationmanager class */
		LocationListener loclistener = new myLocationListener();
		LocationManager locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, loclistener);
	
	
/*		// Registering the overlays on starting the activity
		List<Overlay> mapOverlays = mapView.getOverlays();        
		Drawable drawable = this.getResources().getDrawable(R.drawable.icon_dagger);
		MapItemizedoverlay myOverlay = new MapItemizedoverlay(drawable, this);
		mapOverlays.add(myOverlay);
	*/  }

	/**
	 * A class that listens to the changes in location of the device
	 * 
	 * @author Divyang
	 * 
	 */

	public class myLocationListener implements LocationListener {

		/**
		 * A method to receive the location and center the map while displaying
		 * the latitude and longitude.
		 * 
		 * @param loc
		 *            The location of the device
		 */
		@Override
		public void onLocationChanged(Location loc) {

			GeoPoint geo = new GeoPoint((int) (loc.getLatitude() * 1E6),
					(int) (loc.getLongitude() * 1E6));
			mapController.setCenter(geo);
			String text = " Latitude = " + loc.getLatitude() + " Longitude = "
					+ loc.getLongitude();
			debug.setText(text);
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}