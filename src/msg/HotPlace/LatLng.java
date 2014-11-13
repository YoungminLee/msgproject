package msg.HotPlace;

public class LatLng {

	private String lat;
	private String lng;
	
	public LatLng(String lg, String lt)
	{
		lat = lt;
		lng = lg;
	}
	
	public String getLat()
	{
		return lat;
	}
	
	public String getLng()
	{
		return lng;
	}
	
	@Override
	public String toString() {
		return "LatLng [lat=" + lat + "lng=" + lng + "]";
	}
}