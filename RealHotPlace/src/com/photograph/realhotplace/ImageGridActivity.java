/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.photograph.realhotplace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.realhotplace.R;
import com.google.android.gms.maps.model.LatLng;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.example.universalimageloader.Constants.Extra;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.photograph.realhotplace.SampleUrl.Extra;

//import com.photograph.realhotplace.Constants.Extra;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImageGridActivity extends AbsListViewBaseActivity {

	String[] imageUrls;

	DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_grid);
		Bundle bundle = getIntent().getExtras();
		LatLng location = (LatLng) bundle.get("location");
		String s_latitude = String.format("%.6f", location.latitude);
		String s_longitude = String.format("%.6f", location.longitude);

		Log.d("location", "" + location.latitude + " " + location.longitude);

		new InstagramAsyncTask().execute(s_latitude, s_longitude);
		// Bundle bundle = getIntent().getExtras();
		// imageUrls = bundle.getStringArray(Extra.IMAGES);

		// set image url
		// imageUrls = new SampleUrl().getUrlList();
		/*
		 * imageUrls = new GetInstagramData().urlList; myAsyncTask = new
		 * MyAsyncTask(); myAsyncTask.execute("37.610869", "126.997289");
		 */

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				// .cacheOnDisk(true)
				.considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
				.build();

		listView = (GridView) findViewById(R.id.gridview);

	}

	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			View view = convertView;
			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.item_grid_image,
						parent, false);
				holder = new ViewHolder();
				assert view != null;
				holder.imageView = (ImageView) view.findViewById(R.id.image);
				holder.progressBar = (ProgressBar) view
						.findViewById(R.id.progress);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			imageLoader.displayImage(imageUrls[position], holder.imageView,
					options, new SimpleImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							holder.progressBar.setProgress(0);
							holder.progressBar.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							holder.progressBar.setVisibility(View.GONE);
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							holder.progressBar.setVisibility(View.GONE);
						}
					}, new ImageLoadingProgressListener() {
						@Override
						public void onProgressUpdate(String imageUri,
								View view, int current, int total) {
							holder.progressBar.setProgress(Math.round(100.0f
									* current / total));
						}
					});

			return view;
		}

		class ViewHolder {
			ImageView imageView;
			ProgressBar progressBar;
		}

	}

	// AsyncTask클래스는 항상 Subclassing 해서 사용 해야 함.
	// 사용 자료형은
	// background 작업에 사용할 data의 자료형: String 형
	// background 작업 진행 표시를 위해 사용할 인자: Integer형
	// background 작업의 결과를 표현할 자료형: Long
	// 인자를 사용하지 않은 경우 Void Type 으로 지정.
	class InstagramAsyncTask extends AsyncTask<String, Void, String> {

		public static final String CLIENT_ID =

		"1a4701bd0e73433baadacc191c3ec601";
		public static final String CLIENT_SECRET =

		"99db35b21e734fcfbaebd0d5909b5369";
		public static final String CALLBACK_URL = "http://112.108.40.91";
		public static final String REQUEST_URL =

		"https://api.instagram.com/v1/media/search?";

		@Override
		protected String doInBackground(String... params) {

			ArrayList<String> urlList = new ArrayList<String>();
			// String[] imageURLStringArray;
			String lat = params[0];
			String lng = params[1];

			Log.d("SODA", "lat : " + lat);
			Log.d("SODA", "lng : " + lng);

			// * Sample *
			// https://api.instagram.com/v1/media/search?client_id=15810c75992c48eb8ae84d0d9a9695f0&lat=37.610869&lng=126.997289
			String urlString = REQUEST_URL + "client_id=" + CLIENT_ID +

			"&" + "lat=" + lat + "&" + "lng=" + lng + "&" + "count=15";
			try {

				Log.d("SODA", "urlString : " + urlString);

				URL url = new URL(urlString);
				Log.d("SODA", "url : " + url.toString());
				InputStream inputStream;

				inputStream = url.openConnection().getInputStream();
				Log.d("SODA", "inputStream : " +

				inputStream.toString());
				String response = streamToString(inputStream);
				Log.d("SODA", "response : " + response);
				JSONObject jsonObject = (JSONObject) new

				JSONTokener(response).nextValue();
				Log.d("SODA", "jsonObject : " +

				jsonObject.toString());
				JSONArray jsonArray = jsonObject.getJSONArray

				("data");

				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject imageJsonObject =

					jsonArray.getJSONObject(i).getJSONObject("images")
							.getJSONObject("low_resolution");
					// Use for loop to traverse// through the JsonArray.

					Log.d("SODA", "imageJsonObject : " +

					imageJsonObject);
					String imageUrlString =

					imageJsonObject.getString("url");
					Log.d("SODA", "imageUrlString : " +

					imageUrlString);
					urlList.add(imageUrlString);

					Log.d("SODA", "image : " +

					imageJsonObject.toString());
				}

				imageUrls = (String[])

				urlList.toArray(new String[urlList.size()]);

				Log.d("SODA", imageUrls.toString());

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sum = "";

			return sum;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (result != null) {
				((GridView) listView).setAdapter(new ImageAdapter());// postexecute에
																		// 넣기
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						startImagePagerActivity(position);
					}
				});
				Log.d("ASYNC", "result = " + result);
			}

		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		private String streamToString(InputStream is) throws IOException {
			String str = "";

			if (is != null) {
				StringBuilder sb = new StringBuilder();
				String line;

				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(is));

					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}

					reader.close();
				} finally {
					is.close();
				}

				str = sb.toString();
			}

			return str;
		}
	}
}