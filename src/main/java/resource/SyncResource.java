package resource;

import model.Trip;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.DaoService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SyncResource extends ParentResource{
	
	private static final Logger logger = LoggerFactory.getLogger(SyncResource.class);	   
	
	@Post
	public Representation create(Representation entity){
		JSONObject response = new JSONObject();

		try{
			this.checkEntity(entity);
			JSONObject requestData = this.getJSONObj(entity);
			
			ObjectMapper mapper = new ObjectMapper();
			Trip trip = mapper.readValue(requestData.toString(), Trip.class);
			
			logger.info(trip.toString());
			
			trip = DaoService.storeTrip(trip);
			String jsonStr = mapper.writeValueAsString(trip);
			response = new JSONObject(jsonStr);
			
		} catch (Exception e){
			logger.warn("Create Failed", e);
			return this.handleException(e);
		}

		Representation result = new JsonRepresentation(response);
		this.addCORSHeader(); 
		return result;
	}
}
