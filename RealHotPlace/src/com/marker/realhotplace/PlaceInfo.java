package com.marker.realhotplace;

public class PlaceInfo {
	double latitude;
	double longitude;
	int population;

	
	public PlaceInfo(double lat, double lng, int pop){
		this.latitude=lat;
		this.longitude=lng;
		this.population=pop;
	}
	//if do not need, erase 
	public PlaceInfo() {
		// TODO Auto-generated constructor stub
		//init0 check please!
		this.latitude=0.0;
		this.longitude=0.0;
		this.population=0;
	}
	public void setLatitude(double lat){
		this.latitude=lat;
	}
	
	public void setLongitude(double lng){
		this.longitude=lng;
	}
	public void setPopultion(int pop){
		this.population=pop;
	}
	public void setPlaceInfo(double lat, double lng, int pop){
		this.latitude=lat;
		this.longitude=lng;
		this.population=pop;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	public int getPopultion(){
		return population;
	}
	/*public PlaceInfo getPlaceInfo(){
		return new PlaceInfo(latitude, longitude, population);
	}need to set initial latlng*/
	
	

}
