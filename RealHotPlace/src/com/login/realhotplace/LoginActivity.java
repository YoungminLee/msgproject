package com.login.realhotplace;

import com.example.realhotplace.R;
import com.googlemap.realhotplace.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;



public class LoginActivity extends FragmentActivity {
private LoginFragment loginFragment;
private static final String TAG = "LoginActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			loginFragment = new LoginFragment();
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, loginFragment).commit();
			
		} else {
			// Or set the fragment from restored state info
			loginFragment = (LoginFragment) getSupportFragmentManager()
					.findFragmentById(android.R.id.content);
		
			
		}
		
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
