package com.googlemap.realhotplace;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;
import com.twotoasters.clusterkraf.InputPoint;

public class SamplePointsProvider {

	//private static RandomPointsProvider instance;

	//private WeakReference<GenerateCallback> generateCallback;
	private ArrayList<InputPoint> points = new ArrayList<InputPoint>();
	
	boolean hasPoints() {
		return points != null;
	}

	ArrayList<InputPoint> getPoints() {
		return points;
	}

	public ArrayList<InputPoint> getHotSamplePoint(){
		
		ArrayList<LatLng> sampleLocation = new SampleData().getHotList();
		
		for(int i=0; i<sampleLocation.size(); i++){
			points.add(new InputPoint(sampleLocation.get(i)));
		}
		return points;
	}
	
	public ArrayList<InputPoint> getCoolSamplePoint(){
		
		ArrayList<LatLng> sampleLocation = new SampleData().getCoolList();
		
		for(int i=0; i<sampleLocation.size(); i++){
			points.add(new InputPoint(sampleLocation.get(i)));
		}
		return points;
	}

		
}

