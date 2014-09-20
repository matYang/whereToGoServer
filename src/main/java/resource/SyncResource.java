package resource;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import service.DaoService;

public class SyncResource extends ParentResource{
	
	@Get 	    
	public Representation getUserById() {
	    Representation result = new JsonRepresentation(new JSONObject());
	    
	    DaoService.set("test", "gogogo");
	    
	    this.addCORSHeader();
	    return result;
	}	    
}
