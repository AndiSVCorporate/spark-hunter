/**
 * 
 */
package com.sparkhunter.mapping;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Divyang A class that converts the xml recieved into a city name.
 */
public class GoogleReverseGeocodeXmlHandler extends DefaultHandler {

	private boolean inLocname = false;
	private boolean finished = false;
	private StringBuilder sb;
	private String city;

	public String getCity() {
		return this.city;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (inLocname && !finished) {
			if ((ch[start] != '\n') && (ch[start] != ' ')) {
				sb.append(ch, start, length);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		super.endElement(uri, localName, name);
		if (!finished) {
			if (localName.equalsIgnoreCase("LocalityName")) {
				city = sb.toString();
				finished = true;
			}

			if (sb != null) {
				sb.setLength(0);
			}
		}

	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		sb = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attr) throws SAXException {
		super.startElement(uri, localName, name, attr);

		if (localName.equalsIgnoreCase("LocalityName")) {
			inLocname = true;
		}

	}
}
