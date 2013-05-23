package com.univpm1.firenzestreests.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class AddressToCoords {
	String latitude;
	String longitude;
	public AddressToCoords(String address, Context context){
		Geocoder geocoder = new Geocoder(context);  
		List<Address> addresses;
		try {
			addresses = geocoder.getFromLocationName(address+", Firenze, FI", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
		if(addresses.size() > 0) {
		    latitude= Double.valueOf(addresses.get(0).getLatitude()).toString();
		    longitude= Double.valueOf(addresses.get(0).getLongitude()).toString();
		}
	}
	public String getLatitude(){
		return latitude;
	}
	public String getLongitude(){
		return longitude;
	}
}
