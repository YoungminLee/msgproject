package com.marker.realhotplace;


public class Type {

//	private String name;
//	private String country;
//	private String twitter;
	private String type;
	
	Type(String ty)
	{
		type = ty;
	}
	

	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Type [type =" + type;
	}
}
