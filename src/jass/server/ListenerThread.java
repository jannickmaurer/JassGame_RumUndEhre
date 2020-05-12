package jass.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;

// ListenerThread is for building a connection to the client

public class ListenerThread extends Thread {
	private final ServerSocket listener;
	private final int port;
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	public ListenerThread(int port) throws IOException {
		super();
		this.port = port;
		this.setName("ListenerThread");
		
		listener = new ServerSocket(port, 10, null);	
		logger.info("ListenerThread started");
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Socket socket = listener.accept();
				Client c = new Client(socket);
				Client.add(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
