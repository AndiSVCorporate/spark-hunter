package com.sparkhunter.main;

//import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class Map extends MapActivity {
    /** Called when the activity is first created. */
    private static final String MAP_KEY = " 0aQZHC-JYT990u_AGNX1OXgS98N_MUnaAyxda4Q";
    
    private MapController mapController;
    private MapView mapView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        mapView = new MapView(this, MAP_KEY);
        mapController = mapView.getController();
        mapController.setZoom(22);
        setContentView(mapView);
        /* Using the locationmanager class */
        LocationListener loclistener=new myLocationListener();
        LocationManager locmanager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, loclistener);    
    }
    
    
    
    public class myLocationListener implements LocationListener
    {

    	@Override
    	public void onLocationChanged(Location loc)
    	{
    		
    		GeoPoint geo = new GeoPoint((int)(loc.getLatitude()*1E6),(int)(loc.getLongitude()*1E6));
    		mapController.setCenter(geo);
    		setContentView(mapView);
    		//String text = " Latitude = "+loc.getLatitude()+" Longitude = "+loc.getLongitude();
    		//TextView tv = new TextView(getApplicationContext());
    		//tv.setText(text);
    		//setContentView(tv);    		
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