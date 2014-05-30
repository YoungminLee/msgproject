package com.userfacebookdata.realhotplace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class SendFBData {

	// url to Server
	private final String URL_SERVER = "http://msgservermsg.appspot.com/";
	// private final String URL_SERVER = "http://testtestmsg.appspot.com/";
	private final String URL_FB_SERVER = "rest/fbdata";
	// private final String URL_FB_SERVER = "post-json";

	private String fbdataurl = String.format("%s%s", URL_SERVER, URL_FB_SERVER);

	private JSONArray sendList = new JSONArray();

	private String sendData;

	private JSONObject sendDataOne;

	public SendFBData(String fb_data) {
		sendData = fb_data;

		// case 2
		// new SendPost().execute();
	}

	public SendFBData(JSONArray sendList) {
		this.sendList = sendList;

	}

	public SendFBData(JSONObject fb_data_one) {
		sendDataOne = fb_data_one;

		// case 2
		// new SendPost().execute();
	}

	public void send() {
		new SendPost().execute();

	}

	class SendPost extends AsyncTask<Void, Void, String> {
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
			Log.d("execute", "finish");
		}

		public String dummySend() {
			String url = "http://msg-project.appspot.com/jsonservlet";

			FBData fbData = new FBData();
			fbData.setTimeStamp("1238747");
			fbData.setAuthor_uid("123763");
			fbData.setLat("32.343877");
			fbData.setLng("128.348669");

			InputStream inputStream = null;
			String result = "";
			try {

				// 1. create HttpClient
				HttpClient httpclient = new DefaultHttpClient();

				// 2. make POST request to the given URL
				HttpPost httpPost = new HttpPost(URL_SERVER);		// 영민이 서버로 바꿈.

				String json = "";

				// 3. build jsonObject
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("timestamp", fbData.getTimeStamp());
				jsonObject.accumulate("author_uid", fbData.getAuthor_uid());
				jsonObject.accumulate("lat", fbData.getLat());
				jsonObject.accumulate("lng", fbData.getLng());

				// 4. convert JSONObject to JSON to String
				json = jsonObject.toString();

				// ** Alternative way to convert Person object to JSON string
				// usin Jackson Lib
				// ObjectMapper mapper = new ObjectMapper();
				// json = mapper.writeValueAsString(person);

				// 5. set json to StringEntity
				StringEntity se = new StringEntity(json);

				// 6. set httpPost Entity
				httpPost.setEntity(se);

				// 7. Set some headers to inform server about the type of the
				// content
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Content-type", "application/json");

				// 8. Execute POST request to the given URL
				HttpResponse httpResponse = httpclient.execute(httpPost);

				// 9. receive response as inputStream
				inputStream = httpResponse.getEntity().getContent();

				// 10. convert inputstream to string
				if (inputStream != null) {
					result = convertInputStreamToString(inputStream);

					Log.d("SODA", result);
				} else
					result = "Did not work!";

				return json;
			} catch (Exception e) {
				Log.d("InputStream", e.getLocalizedMessage());
			}
			return "ERROR";
		}

		// send part
		public String executeClient() throws ClientProtocolException, IOException {
			
			String result = "";
			
			result = dummySend();
			
			// Commented by SODA(14.05.26)
//				HttpPost httpPost = new HttpPost(fbdataurl);
//				StringEntity s_entity=new StringEntity(sendDataOne.toString());
//				Log.d("data", sendDataOne.toString());
////				s_entity.setContentType("application/json;");
////				s_entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;"));
//				httpPost.setEntity(s_entity);
//				httpPost.setHeader("Accept", "application/json");
//				httpPost.setHeader("Content-type", "application/json");
//				//send request
//				DefaultHttpClient httpClient = new DefaultHttpClient();
//				HttpResponse response = httpClient.execute(httpPost);
//				
//				InputStream inputStream = null;
//				inputStream = response.getEntity().getContent();
//				
//				
//				result = convertInputStreamToString(inputStream);				
			
			Log.d("response",result);
			Log.d("execute", "trying");
			//return EntityUtils.getContentCharSet(s_entity);
			return "dummy";

			
			
			//onCreate() 메소드 내에서 new SendPost().execute(); 를 넣으시면 됩니다.
		}
	}

	private static String convertInputStreamToString(InputStream inputStream)
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

}
