package com.googlemap.realhotplace;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class SampleData {

	private ArrayList<LatLng> list=new ArrayList<LatLng>();
	private ArrayList <LatLng> coollist = new ArrayList<LatLng>();
	
	
	public SampleData(){
		
		list.add(new LatLng(37.54629658, 126.9805412));
		list.add(new LatLng(37.49450811, 126.6791121));
		list.add(new LatLng(37.49450831, 126.9891123));
		list.add(new LatLng(37.49450821, 126.9891122));
		list.add(new LatLng(37.49459811, 126.9891129));
		list.add(new LatLng(37.47450811, 126.9291121));
		list.add(new LatLng(37.59459811, 126.9191129));
		list.add(new LatLng(37.49350811, 126.9991121));
		
		list.add(new LatLng(37.93390912, 128.2844832));
		list.add(new LatLng(37.91909122, 128.2744832));
		list.add(new LatLng(37.91209121, 128.2744830));
		list.add(new LatLng(37.91909155, 128.2744844));
		
		list.add(new LatLng(37.90909155, 128.2741844));
		list.add(new LatLng(37.91809155, 128.2743844));
		list.add(new LatLng(37.91909755, 128.2744844));
		
		list.add(new LatLng(36.33728811, 127.3987122));
		list.add(new LatLng(36.33728813, 127.3987122));
		list.add(new LatLng(36.33128812, 127.3987112));
		
		list.add(new LatLng(37.52116412, 129.0288976));
		list.add(new LatLng(35.56354723, 129.1647631));
		
		list.add(new LatLng(35.22546472, 129.0356731));
		list.add(new LatLng(35.23546472, 129.0252741));
		list.add(new LatLng(35.24546472, 129.0156741));
		list.add(new LatLng(35.73546472, 129.0052741));
		
		list.add(new LatLng(35.22544472, 129.0356791));
		list.add(new LatLng(37.6250677, 127.0298109));
		list.add(new LatLng(37.5977685, 127.0110698));
		list.add(new LatLng(37.4563739, 126.9123945));
		list.add(new LatLng(37.3380001, 126.9781408));
		
		coollist.add(new LatLng(37.91909755, 128.2744844));
		coollist.add(new LatLng(36.33728811, 127.3987122));
		coollist.add(new LatLng(36.33728813, 127.3987122));
		coollist.add(new LatLng(36.33128812, 127.3987112));
		
	}
	
	public ArrayList<LatLng> getHotList(){
				
		return list;
	}
	public ArrayList<LatLng> getCoolList(){
		return coollist;
	}
	
}
