package eu.wltr.restskeleton.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;
import org.springframework.web.servlet.DispatcherServlet;

public class App {
	public static void main(String[] args) throws Exception {
		final Server server = new Server(8080);

        final ContextHandler context = new ContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        final DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("classpath:rest-context.xml");

        final ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(new ServletHolder(dispatcherServlet), "/*");

        context.addHandler(handler);

        server.start();
	}
}
