package msg.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class FBServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("deprecation")
	// This will store all received articles
	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	List<FBData> fbdata = new LinkedList<FBData>();
	List<String[]> arrlist = new ArrayList<String[]>();
	String[] temp;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{
	    
		// 1. get received JSON data from request
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
		String json = "";
		if(br != null){
			json = br.readLine();
		}
		String[] json2 = json.split("]");
		String[] array = json2[0].split(",");
		int i= array.length;
		for(int j=0; j<i; j++){
			temp=array[j].split("=");
			arrlist.add(temp);
		}
		
		for(int j=0; j<arrlist.size(); j++){
			
				Entity entity = new Entity("FB");
				entity.setProperty("author_uid", arrlist.get(j)[3]);
				entity.setProperty("latitude", arrlist.get(j)[5]);
				entity.setProperty("longitude",arrlist.get(j)[7]);
				entity.setProperty("timestamp", arrlist.get(j)[1]);
				
				datastore.put(entity);	
			
		}
		
//		for(int j=0; j<arrlist.size(); j++){
//			if(j>=1){
//				int z = 0;
//				for(int k=0; k<j; k++){
//					if(0==arrlist.get(k)[1].compareTo(arrlist.get(j)[1])){
//						z++;
//						break;
//					}
//				}
//				if(z==0){
//					Entity entity = new Entity("FB");
//					entity.setProperty("author_uid", arrlist.get(j)[3]);
//					entity.setProperty("latitude", arrlist.get(j)[5]);
//					entity.setProperty("longitude",arrlist.get(j)[7]);
//					entity.setProperty("timestamp", arrlist.get(j)[1]);
//						
//					datastore.put(entity);
//				}
//					
//			}
//			else{
//				Entity entity = new Entity("FB");
//				entity.setProperty("author_uid", arrlist.get(j)[3]);
//				entity.setProperty("latitude", arrlist.get(j)[5]);
//				entity.setProperty("longitude",arrlist.get(j)[7]);
//				entity.setProperty("timestamp", arrlist.get(j)[1]);
//				
//				datastore.put(entity);	
//			}
//		}
//		
		response.setContentType("application/json");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//2. initiate jackson mapper
		ObjectMapper mapper1 = new ObjectMapper();
		

		// 4. Set response type to JSON
		resp.setContentType("application/json");		    
    	

		// 6. Send List<Article> as JSON to client
    	mapper1.writeValue(resp.getOutputStream(), fbdata);
	}
	
}