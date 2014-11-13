package msg.Cron;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Path;

import msg.HotPlace.FBData;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@Path("/make")
public class Cron {
	static int GET_MAX_OF_THE_NUMBER_OF_DATAS = 30;
	static int THE_NUMBER_OVERLAP = 5;
	String json = "";
	private static DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	
	
	@Path("/hotplace")
	public static void makehotplace(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
		
		List<FBData> hotlist = new LinkedList<FBData>();
		Query hotQuery = new Query("FB");
		hotQuery.addSort("timestamp", SortDirection.DESCENDING);
		PreparedQuery pq = datastore.prepare(hotQuery);
		int i = 0;
		for(Entity result : pq.asIterable())
		{
			if (i > GET_MAX_OF_THE_NUMBER_OF_DATAS)
				break;
			
			String timeStamp = (String) result.getProperty("timestamp");
			String author_uid = (String) result.getProperty("author_uid");
			String lat = (String) result.getProperty("latitude");
			String lng = (String) result.getProperty("longitude");
			FBData fbData = new FBData(timeStamp, author_uid, lat, lng);
			
			
			
			hotlist.add(fbData);
			
			i++;
		}
	}
	
	@Path("/coolplace")
	public static void makecoolplace(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
		
		List<FBData> coollist = new LinkedList<FBData>();
		Query coolQuery = new Query("FB");
		PreparedQuery pq = datastore.prepare(coolQuery);
		int i = 0;
		for(Entity result : pq.asIterable())
		{
			
			String timeStamp = (String) result.getProperty("timestamp");
			String author_uid = (String) result.getProperty("author_uid");
			String lat = (String) result.getProperty("latitude");
			String lng = (String) result.getProperty("longitude");
			FBData fbData = new FBData(timeStamp, author_uid, lat, lng);
			coollist.add(fbData);
			
			i++;
		}
	}
	
}