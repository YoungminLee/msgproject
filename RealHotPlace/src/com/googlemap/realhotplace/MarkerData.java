package com.googlemap.realhotplace;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 *
 */
public class MarkerData implements Parcelable {

	private final LatLng latLng;
	private final String label;

	public static final MarkerData TwoToasters = new MarkerData(new LatLng(36.33728813d, 127.3987122d), "Two Toasters");

	public MarkerData(LatLng latLng, String label) {
		this.latLng = latLng;
		this.label = label;
	}

	/**
	 * @return the latLng
	 */
	public LatLng getLatLng() {
		return latLng;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeParcelable(latLng, 0);
		dest.writeString(label);
	}

}
