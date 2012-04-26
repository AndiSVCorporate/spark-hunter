/**
 * 
 */
package com.sparkhunter.mapping;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.location.Location;

/**
 * @author Divyang
 * Contacts the google server to obtain a XML that contains the address information.
 */
public class Geodecoder {

	private static final String MAP_KEY = "0BpOKOGaNV--pQ-Km6inD4BSIY4viUJnBo6RViQ";

	public static String reverseGeocode(Location loc) {
		String city = "";
		HttpURLConnection connect = null;
		URL servadd = null;

		try {
			servadd = new URL("http://maps.google.com/maps/geo?q="
					+ Double.toString(loc.getLatitude()) + ","
					+ Double.toString(loc.getLongitude())
					+ "&output=xml&oe=utf8&sensor=true&key=" + MAP_KEY);
			connect = null;

			// Initial connection
			connect = (HttpURLConnection) servadd.openConnection();
			connect.setRequestMethod("GET");
			connect.setDoOutput(true);
			connect.setReadTimeout(10000);
			connect.connect();

			try {
				InputStreamReader isr = new InputStreamReader(
						connect.getInputStream());
				InputSource source = new InputSource(isr);
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				XMLReader xr = parser.getXMLReader();
				GoogleReverseGeocodeXmlHandler handler = new GoogleReverseGeocodeXmlHandler();

				xr.setContentHandler(handler);
				xr.parse(source);
				city = handler.getCity();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return city;

	}
}
