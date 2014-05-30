package com.marker.realhotplace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.twotoasters.clusterkraf.InputPoint;
import com.googlemap.realhotplace.MainActivity;

public class GetCoolPlace {

	// url to Server
	private final String URL_SERVER = "http://msgservermsg.appspot.com/";
	// private final String URL_SERVER = "http://testtestmsg.appspot.com/";
	private final String URL_FB_SERVER = "rest/coolplace";
	// private final String URL_FB_SERVER = "post-json";

	private String fbdataurl = String.format("%s%s", URL_SERVER, URL_FB_SERVER);

	private JSONArray sendList = new JSONArray();

	//private ArrayList<InputPoint> points = new ArrayList<InputPoint>();
	
	private Context mContext =null;
	

	/*public GetCoolPlace() {
		new SendPost().execute();
	}*/
	
	public GetCoolPlace(Context context) {
		this.mContext = context;
		new SendPost().execute();
		
	}

	class SendPost extends AsyncTask<Void, Void, String> {
		String result = "";
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		protected String doInBackground(Void... unused) {
			String content = null;
			try {

				content = executeClient();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return content;
		}

		protected void onPostExecute(String result) {
			// execute after tasks(method)
			JSONArray jsonArray;

	        try {
	        	
	        	jsonArray = new JSONArray(result);
	        	ArrayList<InputPoint> points = new ArrayList<InputPoint>();
	        	
	        	for(int i =0 ; i<jsonArray.length(); i++){
	        		JSONObject object = jsonArray.getJSONObject(i);
	        		Log.d("SODA", object.toString());
		        	String lat = object.getString("lat");
		        	String lng = object.getString("lng");
		        	points.add(new InputPoint(new LatLng(Double.parseDouble(lat),Double.parseDouble(lng))));
		        	
	        	}
	        	
	        	((MainActivity)mContext).setHotPoints(points);
	        	((MainActivity)mContext).initMap();


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public String getCoolplace() {
			String url = "http://msgservermsg.appspot.com/jsonservlet";		// ������ ������ �ٲ�.

			Log.d("SODAi", "GET COOL PLACE");
			InputStream inputStream = null;
	
			try {
		         // 1. create HttpClient
		         HttpClient httpclient = new DefaultHttpClient();
		         
		         // 2. make POST request to the given URL
		          HttpPost httpPost = new HttpPost(url);
		          
		          String json = "";
		          
		          Type cool = new Type("COOL");
		          
		          
		          // 3. build jsonObject
		          JSONObject jsonObject = new JSONObject();
		          jsonObject.accumulate("type", cool.getType());
		          
		          // 4. convert JSONObject to JSON to String
		          json = jsonObject.toString();

		          
		          // 5. set json to StringEntity
		          StringEntity se = new StringEntity(json);
		          
		          // 6. set httpPost Entity
		          httpPost.setEntity(se);
		          
		          // 7. Set some headers to inform server about the type of the content   
		          httpPost.setHeader("Accept", "application/json");
		          httpPost.setHeader("Content-type", "application/json");
		          
		         // 8. Execute POST request to the given URL
		         HttpResponse httpResponse = httpclient.execute(httpPost);
		         
		         // 9. receive response as inputStream
		         inputStream = httpResponse.getEntity().getContent();
		          
		         Log.d("SODAi", inputStream.toString());
		         
		          
		         // 10. convert inputstream to string
		         if(inputStream != null)
		         {
		            result = convertInputStreamToString(inputStream);
		            
		            Log.d("SODAi", result);
		         }
		         else
		            result = "Did not work!";
		         
		         return result;
		         
			} catch (Exception e) {
				Log.d("InputStream", e.getLocalizedMessage());
				return "ERROR";
			}
			
			
		}

		// send part
		public String executeClient() throws ClientProtocolException,
				IOException {

			String result = "";

			result = getCoolplace();

			Log.d("response", result);
			Log.d("execute", "trying");
			// return EntityUtils.getContentCharSet(s_entity);
			return result;

			// onCreate() �޼ҵ� ������ new SendPost().execute(); �� �����ø� �˴ϴ�.
		}
	}

	private String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	
	/*public ArrayList<InputPoint> getHotList() {
		
		return points;
	}*/

}
