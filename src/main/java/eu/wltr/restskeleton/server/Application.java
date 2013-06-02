package eu.wltr.restskeleton.server;

public class Application {

	private WebServer server;

	public static final int PORT = 7778;

	public static void main(String[] args) {
		new Application().join();
	}

	public Application() {
		server = new WebServer(PORT);
	}
	public void join() {
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
