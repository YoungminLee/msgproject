package com.photograph.realhotplace;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import static com.photograph.realhotplace.Constants.IMAGES;

import com.example.realhotplace.R;
import com.photograph.realhotplace.Constants.Extra;
import com.photograph.realhotplace.ImageGridActivity;




public class PhotoGraphActivity extends TabActivity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		/*TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
	 
		  
		  Intent intent=new Intent(this,ImageGridActivity.class);
     		//intent.putExtra("Loaction", marker.getPosition());
     		intent.putExtra(Extra.IMAGES, IMAGES);
     		//startActivity(intent);
     		
		  TabSpec tabSpec1 = tabHost.newTabSpec("Photo");
		        //tabSpec1.setContent(intent); // Tab Content
		        //tabSpec1.setIndicator("HOME", res.getDrawable(R.drawable.ic_tab_home))
		        tabHost.addTab(tabSpec1);
		      
		  TabSpec tabSpec2 = tabHost.newTabSpec("Graph");
		       // tabSpec2.setContent(new Intent(this, GraphActivity.class)); // Tab Content
		        tabHost.addTab(tabSpec2);
		 
	       //tabs.set
		   tabHost.setCurrentTab(0);*/
		 
	       
	}
	
}

