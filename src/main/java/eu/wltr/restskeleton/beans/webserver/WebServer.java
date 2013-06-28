package eu.wltr.restskeleton.beans.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Service
public class WebServer implements ApplicationContextAware {

	private int port = 8080;

	public void setPort(int port) {
		this.port = port;

	}

	private String scanPackage;

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext parentContext)
			throws BeansException {
		this.applicationContext = parentContext;

	}

	private Server server;

	public WebServer() {
	}

	public void start() {
		// disable jetty logger
		org.eclipse.jetty.util.log.Log.setLog(null);

		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		context.addServlet(createServletHolder(), "/*");

		server = new Server(this.port);

		server.setHandler(context);

		try {
			server.start();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public ServletHolder createServletHolder() {
		final AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(WebConfig.class);
		webApplicationContext.scan(scanPackage);

		webApplicationContext.setParent(applicationContext);

		ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(
				webApplicationContext));

		return servletHolder;

	}
}
