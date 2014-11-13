package msg.Http;

public class FBData {

	private String timeStamp;
	private String author_uid;
	private String lat;
	private String lng;
	
	public FBData( String timestamp,String author_uid, String lat, String lng) {
		this.author_uid = author_uid;
		this.timeStamp = timestamp;
		this.lat = lat;
		this.lng = lng;
		// TODO Auto-generated constructor stub
	}
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAuthor_uid() {
		return author_uid;
	}

	public void setAuthor_uid(String author_uid) {
		this.author_uid = author_uid;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return "FBData [timeStamp=" + timeStamp + ", author_uid=" + author_uid + ", lat="
				+ lat + ", lng=" + lng + "]";
	}
}