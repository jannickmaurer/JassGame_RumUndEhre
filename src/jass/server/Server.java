package jass.server;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;

// Server has no GUI! Must be started in console by typing in the disired port number

public class Server {
	private static int port = 0;
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to our Jass Game");
		System.out.println("Let's set up the server");
		
		while(port < 1024 || port > 65535) {
			try(Scanner scan = new Scanner(System.in)) {
				System.out.println("Please enter the port number (1024 - 65535): ");
				String input = scan.nextLine();
				
				try {
					port = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}	
		}
		
		try {
			ListenerThread lt = new ListenerThread(port);
			lt.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info("Server started on port: " + port);
		
	}

}
