package resource;

import java.util.ArrayList;

import model.City;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.DaoService;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.ValidationException;

public class RecommendResource extends ParentResource {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendResource.class);
	
	@Get 	    
	public Representation recommend() {
	    JSONArray response = new JSONArray();

	    try{
	    	String cityName = this.getQueryVal("city");
	    	//trip id used for exclusions
	    	String tripId = this.getQueryVal("trip");
			if (cityName == null){
				return this.handleException(new ValidationException("City Name Must Not Be Null"));
			}
			
			ObjectMapper mapper = new ObjectMapper();
			
			ArrayList<City> cities = DaoService.getCityHistory(cityName, tripId);
			for (City city : cities){
				String jsonStr = mapper.writeValueAsString(city);
				response.put(new JSONObject(jsonStr));
			}
			
		} catch (Exception e){
			logger.warn("Fetch Failed", e);
			return this.handleException(e);
		}
	    
		Representation result = new JsonRepresentation(response);
	    this.addCORSHeader();
	    return result;
	}	   
}
