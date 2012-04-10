/**
 * 
 */
package com.sparkhunter.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.sparkhunter.main.BattleField;
import com.sparkhunter.main.HistoryReader;
import com.sparkhunter.main.MapItemizedoverlay;
import com.sparkhunter.main.R;
import com.sparkhunter.main.R.drawable;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;

/**
 * @author Divyang
 * 
 */
public class BattleHistoryView extends MapActivity {
	private static final int ZOOM_LEVEL = 18;
	private static final int SCALE_FACTOR=(int) (1e6);
	private static final String MAP_KEY = "0BpOKOGaNV--pQ-Km6inD4BSIY4viUJnBo6RViQ";
	private HistoryReader hisreader;
	private MapController mapController;
	private MapView mapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		hisreader = new HistoryReader(this);
		setContentView(R.layout.viewmap);
		mapView = (MapView) findViewById(R.id.mapview1);
		mapView.setBuiltInZoomControls(true);
		// debug = (TextView) findViewById(R.id.textViewMap);
		mapController = mapView.getController();
		mapController.setZoom(ZOOM_LEVEL);

		// Registering the overlays on starting the activity
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.icon_dagger);

		List<BattleField> bf = new ArrayList<BattleField>();
		
		//Reading All battle locations from the file 
		try {
			bf = hisreader.read();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		MapItemizedoverlay myOverlay = new MapItemizedoverlay(drawable, this);
		int lat;
		int lon;
		GeoPoint gp=null;
		OverlayItem oi = null;
		
		for(BattleField battle: bf){
			lat = (int)(battle.getBattle_Lat()*SCALE_FACTOR);
			lon = (int)(battle.getBattle_Lon()*SCALE_FACTOR);
			gp = new GeoPoint(lat,lon);
			oi=new OverlayItem(gp,"Battle", battle.getBattle_Name()+" Latitude:" + battle.getBattle_Lat()+ " Longitude:"+ battle.getBattle_Lon());
			myOverlay.addOverlay(oi);
		}
		mapOverlays.add(myOverlay);
		mapController.animateTo(gp);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
