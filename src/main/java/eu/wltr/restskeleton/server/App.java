package eu.wltr.restskeleton.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.servlet.DispatcherServlet;

public class App {

	private static MongoOperations mongoOperations;

	public static void main(String[] args) throws Exception {
		mongoOperations = createMongoOperations();
		createServer().start();
		
		Thread.currentThread().join();
	}
	
	private static MongoOperations createMongoOperations()
	{
		final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"mongo-context.xml");

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("close");
				ctx.close();
			}
		});
		
		return (MongoOperations) ctx.getBean("mongoTemplate");
	}
	
	private static Server createServer()
	{
		final Server server = new Server(8080);

		final ContextHandler context = new ContextHandler();
		context.setContextPath("/");
		server.setHandler(context);

		final DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet
				.setContextConfigLocation("classpath:rest-context.xml");

		final ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(new ServletHolder(dispatcherServlet),
				"/*");

		context.addHandler(handler);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					server.stop();
				} catch (Exception e) {
					// already stopped? fine ...
				}
			}
		});
		
		return server;
	}

	public static MongoOperations getMongoOperations() {
		return mongoOperations;
	}
}
