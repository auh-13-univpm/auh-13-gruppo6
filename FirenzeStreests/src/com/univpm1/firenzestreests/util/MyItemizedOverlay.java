package com.univpm1.firenzestreests.util;

import java.util.ArrayList;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import com.univpm1.firenzestreests.ShowStreetActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
	Context context;
	 public MyItemizedOverlay(Drawable pDefaultMarker, Context context) {
	  super(pDefaultMarker, new DefaultResourceProxyImpl(context));
	  this.context = context;
	  // TODO Auto-generated constructor stub
	 }
	 
	 public void addItem(GeoPoint p, String title, String snippet, int id){
	  OverlayItem newItem = new OverlayItem(Integer.valueOf(id).toString(), title, snippet, p);
	  overlayItemList.add(newItem);
	  populate(); 
	 }

	 @Override
	 protected OverlayItem createItem(int arg0) {
	  // TODO Auto-generated method stub
	  return overlayItemList.get(arg0);
	 }
	 @Override
	 protected boolean onTap(int index)
	 {
		Intent intent = new Intent(context, ShowStreetActivity.class);
		intent.putExtra("com.univpm1.firenzestreests.ID_INDIRIZZO", overlayItemList.get(index).getUid() );
		context.startActivity(intent);
		return true;
	 //Here I know what marker been clicked...
	 }
	 @Override
	 public int size() {
	  // TODO Auto-generated method stub
	  return overlayItemList.size();
	 }

	@Override
	public boolean onSnapToItem(int arg0, int arg1, Point arg2, IMapView arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
