package com.userfacebookdata.realhotplace;


import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.facebook.model.GraphObject;

public class FBDataManagement {
	
	String fbData;
	JSONArray fbSendList = new JSONArray();
	JSONObject newObject = new JSONObject();

	public FBDataManagement(String fbData){
		this.fbData = fbData;
	}
	
	public FBDataManagement (GraphObject data){
		try {
			setNewSendList(data);
						
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendFBData(){
		//SendFBData sendData=new SendFBData(fbSendList);
		//sendData.send();
		Log.d("SODACheck", newObject.toString());
		new SendFBData(newObject).send();
	}
	
	
	private void setNewSendList(GraphObject graphObject) throws JSONException{
		
		try{
			String fbDataString = graphObject.getProperty("data").toString();
			JSONArray fb_jsonArray = new JSONArray(fbDataString);
			
			JSONObject jsonObject = fb_jsonArray.getJSONObject(0);
			int timeStamp = jsonObject.getInt("timestamp");
			String author_uid = jsonObject.getString("author_uid");
			JSONObject coords = jsonObject.getJSONObject("coords");
			double lat = coords.getDouble("latitude");
			double lng = coords.getDouble("longitude");
			
			//JSONObject newObject = new JSONObject();
			newObject.put("timestamp", timeStamp);
			newObject.put("author_uid", author_uid);
			newObject.put("latitude", lat);
			newObject.put("longitude", lng);
			
			/*JSONObject location = jsonObject.getJSONObject("location");
			double lat = location.getDouble("latitude");
			double lng = location.getDouble("longitude");
//			for(int i=0; i<fb_jsonArray.length(); i++){
//				JSONObject jsonObject = fb_jsonArray.getJSONObject(i);
//				
//			
//				//Log.d("jsonobject", jsonObject.toString());
//				
//				//JSONArray locations = jsonObject.getJSONArray("location");
//				int timeStamp = jsonObject.getInt("timestamp");
//				String author_uid = jsonObject.getString("author_uid");
//				JSONObject coords = jsonObject.getJSONObject("coords");
//				double lat = coords.getDouble("latitude");
//				double lng = coords.getDouble("longitude");
//				
//				/*JSONObject location = jsonObject.getJSONObject("location");
//				double lat = location.getDouble("latitude");
//				double lng = location.getDouble("longitude");
//			
//				
//				JSONObject newObject = new JSONObject();
//				newObject.put("lat", Double.toString(lat));
//				newObject.put("lng", Double.toString(lng));*/
//				
//				JSONObject newObject = new JSONObject();
//				newObject.put("timestamp", timeStamp);
//				newObject.put("author_uid", author_uid);
//				newObject.put("latitude", lat);
//				newObject.put("longitude", lng);
//				
//				fbSendList.put(newObject);
//				
//				Log.d("senddata", newObject.toString());
//				
//            }
			
			
		}catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     
        }     
		
	}
	//graphObject to String
	public String getFBdataString(){
		return fbData;
	}

	
	//need set data??
	public void setFBdata(String data){
		this.fbData = data;
	}
	
	private void selectData(){
		
	}
	
	private void compareData(){
		
	}
	
}
