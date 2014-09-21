package resource;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.DaoService;

public class RecommendResource extends ParentResource {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendResource.class);
	
	@Get 	    
	public Representation recommend() {
	    Representation result = new JsonRepresentation(new JSONObject());
	    
	    DaoService.set("test", "gogogo");
	    
	    this.addCORSHeader();
	    return result;
	}	   
}
