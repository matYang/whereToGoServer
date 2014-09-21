package resource;

import model.Trip;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.DaoService;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.ValidationException;

public class SyncWithIdResource extends ParentResource {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWithIdResource.class);
	
	@Get 	    
	public Representation fetch() {
		JSONObject response = new JSONObject();
		
		try{
			String id = this.getReqAttr("id");
			if (id == null){
				return this.handleException(new ValidationException("Id Must Not Be Null In Path"));
			}
			
			Trip trip = DaoService.fetchTrip(id);
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(trip);
			response = new JSONObject(jsonStr);
			
		} catch (Exception e){
			logger.warn("Fetch Failed", e);
			return this.handleException(e);
		}
	    
		Representation result = new JsonRepresentation(response);
	    this.addCORSHeader();
	    return result;
	}	   
	
	@Put
	public Representation update(Representation entity){
		JSONObject response = new JSONObject();

		try{
			this.checkEntity(entity);
			
			String id = this.getReqAttr("id");
			JSONObject requestData = this.getJSONObj(entity);
			
			ObjectMapper mapper = new ObjectMapper();
			Trip trip = mapper.readValue(requestData.toString(), Trip.class);
			if (id == null || trip.getId() == null){
				return this.handleException(new ValidationException("Id Must Not Be Null In Both Request Body And Path"));
			}
			if (!trip.getId().equals(id)){
				return this.handleException(new ValidationException("Id In Reuqest Body Does not Match Id In Path"));
			}
			
			logger.info(trip.toString());
			
			trip = DaoService.storeTrip(trip);
			String jsonStr = mapper.writeValueAsString(trip);
			response = new JSONObject(jsonStr);
			
		} catch (Exception e){
			logger.warn("Update Failed", e);
			return this.handleException(e);
		}

		Representation result = new JsonRepresentation(response);
		this.addCORSHeader(); 
		return result;
	}
}
