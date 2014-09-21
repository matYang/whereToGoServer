package resource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.engine.header.Header;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ParentException;
import exception.ValidationException;

public class ParentResource extends ServerResource{
	
	private static final Logger logger = LoggerFactory.getLogger(ParentResource.class);
	
	private static final int MAX_POSTSIZE = 32 * 1024 * 1024;
	
	public void addCORSHeader(){
		Series<Header> responseHeaders = (Series<Header>) getResponse().getAttributes().get("org.restlet.http.headers");
        
		if (responseHeaders == null) { 
			responseHeaders = new Series(Header.class); 
			responseHeaders.add("Access-Control-Allow-Origin", "*");
			responseHeaders.add("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
			responseHeaders.add("Access-Control-Allow-Headers", "Content-Type");
			responseHeaders.add("Access-Control-Allow-Headers", "authCode");
			responseHeaders.add("Access-Control-Allow-Headers", "origin, x-requested-with, content-type");
		}
		
		if (responseHeaders != null){
            getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders); 
        }
	}
	
	public void checkEntity(Representation entity){
		if (entity != null && entity.getSize() > MAX_POSTSIZE){
			throw new ValidationException("Content-Size Too Large");
		}
	}
	
	public String getUserAgent(){
		Series<Header> requestHeaders = (Series<Header>) getRequest().getAttributes().get("org.restlet.http.headers");
		if (requestHeaders != null && requestHeaders.getFirstValue("User-agent", true) != null){
			return requestHeaders.getFirstValue("User-agent", true);
		}
		else{
			return "undetermined";
		}
	}
	
	public JSONObject getJSONObj(Representation entity){
		try {
			JSONObject jsonBooking = (new JsonRepresentation(entity)).getJsonObject();
			return jsonBooking;
		} catch (Exception e) {
			logger.warn("Get Json Failed", e);
			throw new ValidationException("Invalid JSON Data");
		}
	}
	
	public String decodeURI(String val){
		if (val == null) 
			return val;
		try {
			return URLDecoder.decode(val, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.warn("Decode Failed", e);
			throw new ValidationException("Unsupported Encoding Exception");
		}
	}
	
	public String getReqAttr(String fieldName){
		String attr = (String)this.getRequestAttributes().get(fieldName);
		return decodeURI(attr);
	}
	
	public String getQueryVal(String fieldName){
		String val = getQuery().getValues(fieldName);
		return decodeURI(val);
	}
	
	public String getPlainQueryVal(String fieldName){
		String val = getQuery().getValues(fieldName);
		return val;
	}
	
	
	public JsonRepresentation handleException(Exception e){
		JSONObject response = new JSONObject();
		if (e instanceof ParentException){
			ParentException realE = (ParentException)e;
			setStatus(realE.getResponseStatus());
			response.put("status", realE.getResponseStatus().getCode());
			response.put("message", realE.getMessage());
		}
		else{
			setStatus(Status.SERVER_ERROR_INTERNAL);
			response.put("status", 500);
			response.put("message", "Server Encountered Some Difficulties, Please Try Again Later");
		}
		
		this.addCORSHeader();
		return new JsonRepresentation(response);
	}
	
	
	
	/******************
	 * 
	 * Take the options
	 * 
	 ******************/
    //needed here since in CORS scenarios will try to send OPTIONS to /id before PUT or DELETE
    @Options
    public Representation takeOptions(Representation entity) {
    	addCORSHeader();
    	setStatus(Status.SUCCESS_OK);
        return new JsonRepresentation(new JSONObject());
    }

}
