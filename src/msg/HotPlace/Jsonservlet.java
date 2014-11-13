package msg.HotPlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;

public class Jsonservlet extends HttpServlet{
	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{
//		List<FBData> objectList2 = new LinkedList<FBData>();
		
		// 1. get received JSON data from request
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String string = "";
		if(br != null){
			string = br.readLine();
		}
		
		// 2. initiate jackson mapper
    	ObjectMapper mapper = new ObjectMapper();
    	
    	// 3. Convert received JSON to Article
    	Type type = mapper.readValue(string, Type.class);

    	if (type.getType() != null && type.getType().equals("HOT"))
    	{
    		List<LatLng> objectList = new LinkedList<LatLng>();
    		// 4. Set response type to JSON
			response.setContentType("application/json");		    
	    	
			Query query = new Query("FB");
//			query.addFilter("timestamp", FilterOperator.GREATER_THAN, 1325000000);
//			query.addSort("timestamp", SortDirection.DESCENDING);
	
			PreparedQuery pq = datastore.prepare(query);
//			JsonArray jsonArray = new JsonArray();
			for(Entity result : pq.asList(FetchOptions.Builder.withLimit(10))){
				LatLng temp = new LatLng((String)result.getProperty("longitude"),(String)result.getProperty("latitude"));
//				temp.setAuthor_uid((String) result.getProperty("author_id"));
//				temp.setLat((String) result.getProperty("latitude"));
//				temp.setLng((String) result.getProperty("longitude"));
//				temp.setTimeStamp((String) result.getProperty("timestamp"));
				
				objectList.add(temp);
			}
//			JsonObject data = new JsonObject();
//			data.put("data", jsonArray);
//			String sending = new String(data.toString().getBytes(), "EUC-KR");
						
			// 6. Send List<Article> as JSON to client
			mapper.writeValue(response.getOutputStream(), objectList);
//	    	mapper.writeValue(response.getOutputStream(), objectList2);
    	}
    	
    	else if (type.getType() != null && type.getType().equals("COOL"))
    	{
    		List<LatLng> objectList = new LinkedList<LatLng>();
    		// 4. Set response type to JSON
    					response.setContentType("application/json");		    
    			    	
//    			    	// 5. Add article to List<Article>
//    					if(persons.size() > 20)
//    						persons.remove(0);i
//    					
//    					persons.add(person);
    					
    					//String dummy = "[{\"" + "latitude\"" + ": 129.182323, \"" + "longitude\"" + ": 32.65464}]";
//    					if(objectList.size() > 20)
//    						objectList.remove(0);
    					
//    					LatLng object1 = new LatLng("128.182323", "37.65464");
//    					LatLng object2 = new LatLng("127.782323", "37.45464");
//    					LatLng object3 = new LatLng("128.082323", "37.25464");
//    					LatLng object4 = new LatLng("128.582323", "37.85464");
//    					LatLng object5 = new LatLng("127.982323", "36.95464");
//    					LatLng object6 = new LatLng("128.282323", "38.25464");
//    					
//    					objectList.add(object1);
//    					objectList.add(object2);
//    					objectList.add(object3);
//    					objectList.add(object4);
//    					objectList.add(object5);
//    					objectList.add(object6);
    					Query query = new Query("Cool");
//    					query.addFilter("timestamp", FilterOperator.GREATER_THAN, 1325000000);
//    					query.addSort("timestamp", SortDirection.DESCENDING);
    			
    					PreparedQuery pq = datastore.prepare(query);
//    					JsonArray jsonArray = new JsonArray();
    					for(Entity result : pq.asList(FetchOptions.Builder.withLimit(10))){
    						LatLng temp = new LatLng((String)result.getProperty("longitude"),(String)result.getProperty("latitude"));
//    						temp.setAuthor_uid((String) result.getProperty("author_id"));
//    						temp.setLat((String) result.getProperty("latitude"));
//    						temp.setLng((String) result.getProperty("longitude"));
//    						temp.setTimeStamp((String) result.getProperty("timestamp"));
    						
    						objectList.add(temp);
    					}
//    					JsonObject data = new JsonObject();
//    					data.put("data", jsonArray);
//    					String sending = new String(data.toString().getBytes(), "EUC-KR");
    								
    					// 6. Send List<Article> as JSON to client
    					mapper.writeValue(response.getOutputStream(), objectList);
//    			    	mapper.writeValue(response.getOutputStream(), objectList2);
    	}
    	
    	else
    	{
    		// 4. Set response type to JSON
			response.setContentType("application/json");		    
	    	
	    	
	
			String dummy = "[{latitude: 1129.182323, longitude: 32.65464},{latitude: 129.182323, longitude: 32.65464},{latitude: 129.182323, longitude: 32.65464}]";
			
			
			// 6. Send List<Article> as JSON to client
	    	mapper.writeValue(response.getOutputStream(), dummy);
    		
    		
    	}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	
		//2. initiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();
		

		// 4. Set response type to JSON
		resp.setContentType("application/json");		    
    	
		String dummy = "[{latitude: 2129.182323, longitude: 32.65464},{latitude: 129.182323, longitude: 32.65464},{latitude: 129.182323, longitude: 32.65464}]";


		// 6. Send List<Article> as JSON to client
    	mapper.writeValue(resp.getOutputStream(), dummy);
	}
}