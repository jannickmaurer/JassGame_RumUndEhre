package jass.server;

import java.io.IOException;
import java.util.Scanner;

public class Server {
	private static int port = 0;
	
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
		
		System.out.println("Alright, you can start the client and connect!");
		
	}

}
