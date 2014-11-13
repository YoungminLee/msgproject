package msg.MakeData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


@Path("/kookmin")
public class Kookmin {
	
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//	Calendar cal = Calendar.getInstance();
//	String today = formatter.format(cal.getTime());
//	Timestamp ts = Timestamp.valueOf(today);
	@GET    
	@Produces(MediaType.TEXT_HTML)
	@Path("/data")
	public String make(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity entity = new Entity("FB");
		entity.setProperty("author_uid", "100001486310905");
		entity.setProperty("timestamp", "1325432643");
		entity.setProperty("longitude", "126.997382");
		entity.setProperty("latitude", "37.611111");
		datastore.put(entity);
		
		Entity entity2 = new Entity("FB");
		entity2.setProperty("author_uid", "100001486310905");
		entity2.setProperty("timestamp", "1324985453");
		entity2.setProperty("longitude", "126.995451");
		entity2.setProperty("latitude", "37.611179");
		datastore.put(entity2);
		
		Entity entity3 = new Entity("FB");
		entity3.setProperty("author_uid", "100001486310905");
		entity3.setProperty("timestamp", "1325436421");
		entity3.setProperty("longitude", "126.996588");
		entity3.setProperty("latitude", "37.611587");
		datastore.put(entity3);
		
		return "국민대 생성중";
	}

	
	
	
}
