package msg.CoolPlace;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/coolplace") 
public class CoolPlace {           
	@GET    
	@Produces(MediaType.TEXT_HTML)     
	public String temp(){         
		return "CoolPlace ม๘วเม฿";     
	} 
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	@Context
	ServletContext context;
	
//	private Authenication auth = new Authenication();
		
//	@GET
//	@Path("/get")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getCoolPlace() throws EntityNotFoundException,
//			UnsupportedEncodingException {
//
//		if (auth.getAuthentication() == null)
//			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
//		
//		//List<String> cellList = getCoolCells();
//		//JsonObject data = getCools(FIRST, cellList);
//		//if(data == null)
//		//	return Response.status(HttpServletResponse.SC_NO_CONTENT).build();
//		//String outer = new String(data.toString().getBytes(), "EUC-KR");
//		return Response.status(HttpServletResponse.SC_OK).entity(outer).build();
//	}

}