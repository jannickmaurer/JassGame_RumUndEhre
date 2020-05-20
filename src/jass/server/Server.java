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
	private static String directory = "";

	public static void main(String[] args) {

		if (args.length > 1) {
			directory = args[1];
		}

		Playroom.readPlayrooms();
		Account.readAccounts();

		System.out.println("Welcome to our Jass Game");

		try (Scanner scan = new Scanner(System.in)) {

			while (port < 1024 || port > 65535) {
				System.out.println("Please enter the port number");
				System.out.println("-> Port number has to be between 1024 and 65535");
				System.out.println("-> Only numbers allowed");
				String input = scan.nextLine();
				try {
					port = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Only numbers allowed");
				}
			}
			try {
				ListenerThread lt = new ListenerThread(port);
				lt.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("Server started on port: " + port);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String operation = "";
			while (!operation.equals("END")) {
				System.out.println("Operate or terminate the server at any time by entering Operate");
				String temp = "";
				while(!temp.equals("Operate")) {
					temp = scan.nextLine();
				}
				System.out.println("A -> Delete all existing accounts");
				System.out.println("P -> Delete all existing playrooms");
				System.out.println("C -> Disconnect all connected Clients");
				System.out.println("END -> Terminate Server");
				operation = scan.nextLine();
				switch(operation) {
				case("A"): ;
				case("P"): ;
				case("C"): ;
				}
			}   disconnect();			
		}
	}

	
	private static void disconnect() {
		// TODO Auto-generated method stub
		
	}


	public static String getDirectory() {
		return directory;
	}

}
