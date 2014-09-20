package server;

import org.apache.log4j.PropertyConfigurator;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

	private static final Logger logger = LoggerFactory.getLogger(ServerMain.class);
	
	private static int portNumber = 8015;

	private static ServerMain me;

	private Component component;

	public void init(String[] arguments) {

	}

	/**
	 * Start the Thread, accept incoming connections
	 * 
	 * Use this entry point to start with embedded HTTP Server
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		component = new Component();

		// Add a new HTTP server listening on port

		Server server = component.getServers().add(Protocol.HTTP, portNumber);
		server.getContext().getParameters().add("maxThreads", "256");

		// Attach the sample application
		RoutingApplication routingApplication = new RoutingApplication();

		component.getDefaultHost().attach(routingApplication);

		// Start the component.
		logger.info("ready to start");
		component.start();

	}

	/**
	 * Stops RESTlet application
	 */
	public void stop() {
		component.getDefaultHost().detach(component.getApplication());
	}

	public static ServerMain getInstance() {
		if (me == null) {
			me = new ServerMain();
		}
		return me;
	}


	public static void main(String... args) throws Exception {
		PropertyConfigurator.configure("src/main/resources/log4j.properties"); 
		try {
			ServerMain.getInstance().init(args);
			ServerMain.getInstance().start();
		} catch (Exception e) {
			logger.error("Server Initialization Failed", e);
		}
	}
	
}
