/**
 * 
 */
package com.sparkhunter.main;

import java.io.IOException;

import com.sparkhunter.main.BattleActivity.SparkLocationListener;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Divyang
 *A class that allows a service to run in the background and spring surprise attacks on the user.
 */
public class AdventureService extends Service {

	private double lastLat ;
	private double lastLon;
	public static final double  MINIMUM_DISTANCE = 0.001449275; 
	private LocationManager sparklocman; 
    private LocationListener sparkloclistener = new SparkLocationListener();
	@Override
	
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		Toast.makeText(this, "Adventure Service Created", Toast.LENGTH_LONG).show();
		Log.v("ADV_SERV", "onCreate called");
		 sparklocman = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		 sparklocman.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,sparkloclistener);
	        
		 
	}
	
	@Override
	public void onDestroy(){
		Log.v("ADV_SERV", "OnDestroy called");
	}
	
	@Override
	public void onStart(Intent intent, int startid){
		Toast.makeText(this, "Adventure Service Started", Toast.LENGTH_LONG).show();
		Log.v("ADV_SERV", "onStart called");
		sparklocman = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		sparklocman.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,sparkloclistener);
		Toast.makeText(this, "Adventure Service Started", Toast.LENGTH_LONG).show();
		 
	}
	
	
	/*public AdventureService(Intent intent) {
		super("AdventureService");
		Log.v("SERVICE_CONSTRUCTOR", "WORKING");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Toast.makeText(getApplicationContext(), "Testing Adventure Service", Toast.LENGTH_LONG).show();
		Log.v("TEST_SERVICE", "WORKING");
		
	}*/
	
	public double getLastLon(){
		return lastLon;
	}
	public double getLastLat(){
		return lastLat;
	}
	
	public void setLastLon(double lon){
		lastLon=lon;
	}
	public void setLastLat(double lat){
		lastLat=lat;
	}

	/**
	 * A class that gets the location of a battle so that it can be used to create a map of all battles
	 * @author Divyang
	 *
	 */
	public class SparkLocationListener implements LocationListener{

		@Override
		/**
		 * A method that writes the location when a battle starts.
		 */
		public void onLocationChanged(Location loc) {
			double latitude = loc.getLatitude();
			double longitude = loc.getLongitude();
			//Toast.makeText(getApplicationContext(), " " + latitude + " " + longitude, Toast.LENGTH_LONG).show();
			if((getLastLon()==0) || (getLastLat()==0) || (Math.abs(getLastLon()-longitude)>MINIMUM_DISTANCE) || (Math.abs(getLastLat()-latitude)>MINIMUM_DISTANCE)){
				setLastLon(longitude);
				setLastLat(latitude);
				Toast.makeText(getApplicationContext(), " " + latitude + " " + longitude, Toast.LENGTH_LONG).show();
				
			}
			}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
			
			
		}

}
