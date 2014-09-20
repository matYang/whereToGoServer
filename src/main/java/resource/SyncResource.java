package resource;

import model.User;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.DaoService;

public class SyncResource extends ParentResource{
	
	private static final Logger logger = LoggerFactory.getLogger(SyncResource.class);
	
	@Get 	    
	public Representation getUserData() {
	    Representation result = new JsonRepresentation(new JSONObject());
	    
	    DaoService.set("test", "gogogo");
	    
	    this.addCORSHeader();
	    return result;
	}	   
	
	@Post
	public Representation saveUserData(Representation entity){

		JSONObject response = new JSONObject();

		try{
			this.checkEntity(entity);
			JSONObject requestData = this.getJSONObj(entity);
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(requestData.toString(), User.class);
			
			logger.info(user.toString());
			
		} catch (Exception e){
			logger.warn("Sync Failed", e);
			this.addCORSHeader();
			return this.handleException(e);
		}

		Representation result = new JsonRepresentation(response);
		this.addCORSHeader(); 
		return result;
	}
}
