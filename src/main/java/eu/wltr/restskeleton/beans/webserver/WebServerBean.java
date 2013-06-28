package eu.wltr.restskeleton.beans.webserver;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


/**
 * Logical instance for Service Selection Function.
 * 
 * @author Sven Walter <sven.walter@wltr.eu>
 */
@Service
public class WebServerBean implements ApplicationContextAware {

	private ApplicationContext parentContext;

	@Override
	public void setApplicationContext(ApplicationContext parentContext)
			throws BeansException {
		this.parentContext = parentContext;

	}

	public void start() {
		WebServer server = new WebServer();
		server.setPort(7778);
		server.setScanPackage("eu.wltr.restskeleton.controllers");
		server.setApplicationContext(parentContext);
		server.start();

	}

}