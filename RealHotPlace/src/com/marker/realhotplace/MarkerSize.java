package com.marker.realhotplace;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


public class MarkerSize{
	private Drawable drawable;
	private Bitmap bitmap;
	
	public MarkerSize(Drawable draw){
		this.drawable=draw;
	}
	
	public MarkerSize(Bitmap bit){
		bitmap = bit;
	}
	
	public void setImgResource(Drawable draw){
		this.drawable=draw;
	}

	public Bitmap getHotBitMap(int popular){
		//size of bitmap
		int radius=(popular/5)*2;
		BitmapDrawable bd=(BitmapDrawable)drawable;
		Bitmap bit=bd.getBitmap();
		Bitmap s_bit=Bitmap.createScaledBitmap(bit,radius,radius,false);
       
        return s_bit;
	}
	
	public Bitmap setBitmapSize(int popular){
		//size of bitmap
		int radius=(popular/5)*2;
		Bitmap s_bit=Bitmap.createScaledBitmap(bitmap,radius,radius,false);
       
        return s_bit;
	}
}
