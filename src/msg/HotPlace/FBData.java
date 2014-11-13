package msg.HotPlace;

public class FBData {

	   private String timestamp;
	   private String author_uid;
	   private String latitude;
	   private String longitude;
	   
	   public FBData(String timeStamp, String author_uid, String lat, String lng) {
		// TODO Auto-generated constructor stub
		   this.timestamp = timeStamp;
		   this.author_uid = author_uid;
		   this.latitude = lat;
		   this.longitude = lng;
	}

	public String getTimeStamp() {
	      return timestamp;
	   }

	   public void setTimeStamp(String timeStamp) {
	      this.timestamp = timeStamp;
	   }

	   public String getAuthor_uid() {
	      return author_uid;
	   }

	   public void setAuthor_uid(String author_uid) {
	      this.author_uid = author_uid;
	   }

	   public String getLat() {
	      return latitude;
	   }

	   public void setLat(String lat) {
	      this.latitude = lat;
	   }

	   public String getLng() {
	      return longitude;
	   }

	   public void setLng(String lng) {
	      this.longitude = lng;
	   }
	   
	   @Override
	   public String toString() {
	      return "FBData [timeStamp=" + timestamp + ", author_uid=" + author_uid + ", lat="
	            + latitude + ", lng=" + longitude + "]";
	   }
	}
