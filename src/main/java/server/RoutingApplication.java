package server;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import resource.SyncResource;

public final class RoutingApplication extends Application {
	
	private static final Logger logger = LoggerFactory.getLogger(RoutingApplication.class);
	
	public RoutingApplication(){
		super();
		logger.info("Creating new Restlet Application");
	}
	
	public RoutingApplication(Context context) {
		super(context);
	}
	
	@Override
	public synchronized Restlet createInboundRoot(){
		Router router = new Router(getContext());
		
		router.attach("/api/user/sync", SyncResource.class);
		
		return router;
	}
}
