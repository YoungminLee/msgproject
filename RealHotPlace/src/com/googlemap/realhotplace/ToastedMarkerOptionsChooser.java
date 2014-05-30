package com.googlemap.realhotplace;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.example.realhotplace.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.twotoasters.clusterkraf.ClusterPoint;
import com.twotoasters.clusterkraf.InputPoint;
import com.twotoasters.clusterkraf.MarkerOptionsChooser;

public class ToastedMarkerOptionsChooser extends MarkerOptionsChooser {

	private final WeakReference<Context> contextRef;
	private final InputPoint twoToasters;
	private final Paint clusterPaintLarge;
	private final Paint clusterPaintMedium;
	private final Paint clusterPaintSmall;
	private PlaceType iconType;

	public ToastedMarkerOptionsChooser(Context context, InputPoint twoToasters, PlaceType type) {
		this.contextRef = new WeakReference<Context>(context);
		this.twoToasters = twoToasters;

		Resources res = context.getResources();

		clusterPaintMedium = new Paint();
		clusterPaintMedium.setColor(Color.WHITE);
		clusterPaintMedium.setAlpha(255);
		clusterPaintMedium.setTextAlign(Paint.Align.CENTER);
		clusterPaintMedium.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC));
		clusterPaintSmall = new Paint(clusterPaintMedium);
		//clusterPaintSmall.setTextSize(res.getDimension(R.dimen.cluster_text_size_small));

		clusterPaintLarge = new Paint(clusterPaintMedium);
		//Bitmap bitmap1 = BitmapFactory.decodeResource(MainActivity.getResources(), R.drawable.hlevel1);
		iconType = type;
	}

	@Override
	public void choose(MarkerOptions markerOptions, ClusterPoint clusterPoint) {
		Context context = contextRef.get();
		if (context != null) {
			Resources res = context.getResources();
			boolean isCluster = clusterPoint.size() > 1;
			boolean hasTwoToasters = clusterPoint.containsInputPoint(twoToasters);
			BitmapDescriptor icon;
			String title;
			Bitmap bitmap;
			//R.drawable.h
			int clusterSize = clusterPoint.size();
			//icon = BitmapDescriptorFactory.fromBitmap(getClusterBitmap(res,R.drawable.hlevel5,clusterSize));
			if(iconType==PlaceType.HOTPLACE){
				bitmap = getClusterBitmap(res, R.drawable.red_circle, clusterSize);
			}else
				bitmap = getClusterBitmap(res, R.drawable.blue_circle, clusterSize);
			
			icon = BitmapDescriptorFactory.fromBitmap(bitmap);
			/*if (isCluster) {
				
				if (hasTwoToasters) {
					bitmap = getClusterBitmap(res, R.drawable.hlevel10, clusterSize);
					icon = BitmapDescriptorFactory.fromBitmap(bitmap);
					//title = res.getString(R.string.including_two_toasters, title);
				} else {
					bitmap = getClusterBitmap(res, R.drawable.hlevel10, clusterSize);
					icon = BitmapDescriptorFactory.fromBitmap(bitmap);
					//icon = BitmapDescriptorFactory.fromBitmap(getClusterBitmap(res, R.drawable.hlevel3, clusterSize));
					//title = res.getQuantityString(R.plurals.count_points, clusterSize, clusterSize);
				}
			} else {
				//MarkerData data = (MarkerData)clusterPoint.getPointAtOffset(0).getTag();
				//icon = BitmapDescriptorFactory.fromBitmap(bitmap);
				if (hasTwoToasters) {
					bitmap = getClusterBitmap(res, R.drawable.hlevel10, clusterSize);
					icon = BitmapDescriptorFactory.fromBitmap(bitmap);
					//title = data.getLabel();
				} else {
					bitmap = getClusterBitmap(res, R.drawable.hlevel10, clusterSize);
					icon = BitmapDescriptorFactory.fromBitmap(bitmap);
					//title = res.getString(R.string.point_number_x, data.getLabel());
				}
			}*/
			
			markerOptions.icon(icon);
			//markerOptions.title(title);
			markerOptions.anchor(0.5f, 1.0f);
		}
	}

	@SuppressLint("NewApi")
	private Bitmap getClusterBitmap(Resources res, int resourceId, int clusterSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			options.inMutable = true;
		}
		Bitmap bitmap = BitmapFactory.decodeResource(res, resourceId, options);
		if (bitmap.isMutable() == false) {
			bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		}
		
		if (clusterSize>20) clusterSize = 20;
		int square = clusterSize*1000;
		int radius = (int)Math.sqrt(square);
		
		Bitmap s_bitmap=Bitmap.createScaledBitmap(bitmap,radius,radius,false);
		

		return s_bitmap;
	}
}