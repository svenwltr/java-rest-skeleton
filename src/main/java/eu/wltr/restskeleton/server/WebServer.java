package eu.wltr.restskeleton.server;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Class for starting and stopping the HTTP server.
 * 
 * <p>
 * You may be able to dump this class and use the web.xml in a WAR container.
 * </p>
 * 
 * <p>
 * This class is based on {@link https
 * ://github.com/steveliles/jetty-embedded-spring-mvc}.
 * </p>
 * 
 */
public class WebServer {
	private static final String WEBAPP_PATH = "src/main/webapp";

	private Server server;

	private int port;

	private String bindInterface;

	public WebServer(int port) {
		this(port, null);

	}

	public WebServer(int port, String bindInterface) {
		this.port = port;
		this.bindInterface = bindInterface;

	}

	public void start() throws Exception {
		server = new Server();

		server.setThreadPool(createThreadPool());
		server.addConnector(createConnector());
		server.setHandler(createHandlers());
		server.setStopAtShutdown(true);

		server.start();

	}

	public void join() throws InterruptedException {
		server.join();

	}

	public void stop() throws Exception {
		server.stop();

	}

	private ThreadPool createThreadPool() {
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMinThreads(10);
		threadPool.setMaxThreads(100);
		return threadPool;

	}

	private SelectChannelConnector createConnector() {
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setHost(bindInterface);
		return connector;

	}

	private HandlerCollection createHandlers() {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setWar(WEBAPP_PATH);

		List<Handler> handlers = new ArrayList<Handler>();

		handlers.add(context);

		HandlerList handlerList = new HandlerList();
		handlerList.setHandlers(handlers.toArray(new Handler[0]));

		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers(new Handler[]{handlerList});

		return handlerCollection;

	}
}
