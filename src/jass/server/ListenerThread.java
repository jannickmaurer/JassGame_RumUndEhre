package jass.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenerThread extends Thread {
	private final ServerSocket listener;
	private final int port;
	
	public ListenerThread(int port) throws IOException {
		super();
		this.port = port;
		this.setName("ListenerThread");
		
		listener = new ServerSocket(port, 10, null);	
		System.out.println("LT started");
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
