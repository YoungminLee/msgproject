package msg.MakeData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@Path("/make")
public class RandomMake {
	
	
	@GET    
	@Produces(MediaType.TEXT_HTML)
	@Path("/random")
	public String makehotrandom (){
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		
		Entity randomHot = new Entity("Hot");
		Entity randomCool = new Entity("Cool");
		Entity randomData = new Entity("Data");
		
		int aranid = (int)(Math.random()*100000000)+100000000;
		double aranlong = 126.6 + Math.random()*3;
		double aranlat = 34.4 + Math.random()*3.6;
		int arantime = (int)(Math.random()*10000000)+1320000000;
		
		int aranid2 = (int)(Math.random()*100000000)+100000000;
		double aranlong2 = 126.6 + Math.random()*3;
		double aranlat2 = 34.4 + Math.random()*3.6;
		int arantime2 = (int)(Math.random()*10000000)+1320000000;
		
		String ranlong = Double.toString(aranlong);
		String ranlat = Double.toString(aranlat);
		String ranid = String.valueOf(aranid);
		String rantime = String.valueOf(arantime);
		
		String ranlong2 = Double.toString(aranlong2);
		String ranlat2 = Double.toString(aranlat2);
		String ranid2 = String.valueOf(aranid2);
		String rantime2 = String.valueOf(arantime2);
		
		randomHot.setProperty("longitude", ranlong);
		randomHot.setProperty("latitude", ranlat);
		randomHot.setProperty("id", ranid);
		randomHot.setProperty("timestamp", rantime);
	 	dataStore.put(randomHot);
	 	
	 	randomCool.setProperty("longitude", ranlong2);
	 	randomCool.setProperty("latitude", ranlat2);
	 	randomCool.setProperty("id", ranid2);
	 	randomCool.setProperty("timestamp", rantime2);
	 	dataStore.put(randomCool);
	 	
	 	return "1�� �������� �������� ��";
	}
}