package com.login.realhotplace;

import com.userfacebookdata.realhotplace.FBDataManagement;

import java.util.Arrays;

import com.example.realhotplace.R;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.googlemap.realhotplace.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginFragment extends Fragment {
	
	private static final String TAG = "LoginFragment";
	
	private UiLifecycleHelper uiHelper;
	
	private ImageButton startBtn;
	
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, final SessionState state,
				final Exception exception) {
			Log.d("facebook", "onsessionchange");
			onSessionStateChange(session, state, exception);
		}
	};

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
			Log.d("facebook", "login");
			
			String fqlQuery = "SELECT author_uid, coords, timestamp FROM checkin WHERE author_uid IN (SELECT author_uid FROM location_post WHERE author_uid IN(SELECT uid2 FROM friend WHERE uid1=me()))";
		    Bundle params = new Bundle();
		    params.putString("q", fqlQuery);
		    
		    Request request = new Request(session, 
		        "/fql", 
		        params, 
		        HttpMethod.GET, 
		        new Request.Callback(){ 
		            public void onCompleted(Response response) {
		            	Log.d(TAG, response.toString());
		            GraphObject fb_data  = response.getGraphObject();
		            //FBDataManagement fm = new FBDataManagement(fb_data);
		            //fm.sendFBData();
		           
		        }
		    });
		    
		    Request.executeBatchAsync(request);
		    
		    startBtn.setEnabled(true);
		    startBtn.setBackgroundResource(R.drawable.start);
			
		  }else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
			startBtn.setEnabled(false);
			startBtn.setBackgroundResource(R.drawable.start);
			startBtn.setBackgroundResource(R.drawable.start_disable);
			
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("facebook", "oncreate");
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
		
		
		

	}

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("facebook", "oncreateview");
		View view = inflater.inflate(R.layout.login_activity, container, false);
		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);
		authButton.setFragment(this);
		
		authButton.setReadPermissions(Arrays.asList("user_about_me",
				"user_checkins", "user_friends", "user_location",
				"user_activities", "friends_location",
				"friends_checkins", "friends_activities"));
		
		Log.d("facebook", "read permission");
		startBtn =(ImageButton) view.findViewById(R.id.start);
		startBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				startActivity(new Intent(getActivity(),MainActivity.class));
				getActivity().finish();
			}
		});
		return view;
	}
	
	

	@Override
	public void onResume() {
		Log.d("facebook", "resume");
		super.onResume();
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }
	    
	    

	    uiHelper.onResume();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("facebook", "onactivityresult");
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
		
	}
	
	@Override
	public void onPause() {
		Log.d("facebook", "pause");
		super.onPause();
		uiHelper.onPause();
		
	}

	@Override
	public void onDestroy() {
		Log.d("facebook", "destroy");
		super.onDestroy();
		uiHelper.onDestroy();
		
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d("facebook", "onsessionstate");
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
		
	}
	
	

}