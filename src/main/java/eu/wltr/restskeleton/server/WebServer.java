package eu.wltr.restskeleton.server;

import java.io.File;
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

/*
 * https://github.com/steveliles/jetty-embedded-spring-mvc
 */
public class WebServer {
	private static final String PROJECT_RELATIVE_PATH_TO_WEBAPP = "src/main/webapp";

	public static interface WebContext {
		public File getWarPath();

		public String getContextPath();
	}

	private Server server;

	private int port;

	private String bindInterface;

	public WebServer(int aPort) {
		this(aPort, null);
	}

	public WebServer(int aPort, String aBindInterface) {
		port = aPort;
		bindInterface = aBindInterface;
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
		// TODO: You should configure these appropriately
		// for your environment - this is an example only
		QueuedThreadPool _threadPool = new QueuedThreadPool();
		_threadPool.setMinThreads(10);
		_threadPool.setMaxThreads(100);
		return _threadPool;
	}

	private SelectChannelConnector createConnector() {
		SelectChannelConnector _connector = new SelectChannelConnector();
		_connector.setPort(port);
		_connector.setHost(bindInterface);
		return _connector;
	}

	private HandlerCollection createHandlers() {
		WebAppContext _ctx = new WebAppContext();
		_ctx.setContextPath("/");
		_ctx.setWar(PROJECT_RELATIVE_PATH_TO_WEBAPP);

		List<Handler> _handlers = new ArrayList<Handler>();

		_handlers.add(_ctx);

		HandlerList _contexts = new HandlerList();
		_contexts.setHandlers(_handlers.toArray(new Handler[0]));

		HandlerCollection _result = new HandlerCollection();
		_result.setHandlers(new Handler[]{_contexts});

		return _result;
	}
}
