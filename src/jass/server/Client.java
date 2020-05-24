package jass.server;

import java.io.IOException;


import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Some concepts are inspired by or according to Software Engineering Chat Project
 */
public class Client {
	
	private static final ArrayList<Client> clients = new ArrayList<>();
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private Account account = null;
	private String token = null;
	private Socket socket;
	private boolean clientReachable = true;
	private Playroom playroom;
	
	
	public Client(Socket socket) {
		this.socket = socket;
		
		// Thread for incoming messages
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					while(clientReachable) {
						// New Message object with static method receive
						Message msg = Message.receive(socket);
						
						if(msg != null) {
							logger.info("Server received message: " + msg.toString());
							msg.process(Client.this);
						} else {
							logger.info("Empty Message");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			} 
			
		};
		Thread t = new Thread(r);
		t.start();
		
	}
	
	// Add new client to the ArrayList
	public static void add(Client client) {
		// synchronized to make sure that only one client at once is editing the ArrayList
		synchronized (clients) {
			clients.add(client);
		}
	}
	
	//sned a new message to this client
	public void send(Message msg) {
		try {
			msg.send(socket);	
			logger.info("Server sent message: " + msg.toString());
		} catch (Exception e) { //TBD: Why not IOException?
			e.printStackTrace();
			this.token = null;
			this.clientReachable = false;
		}
	}

	//Getters and Setters
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Socket getSocket() {
		return socket;
	}

	public static ArrayList<Client> getClients() {
		return clients;
	}

	public static Client getClient(String username) {
		synchronized (clients) {
			for (Client c : clients) {
				if (c.getAccount() != null && c.getName().equals(username)) return c;
			}
		}
		return null;
	}
	
	public String getName() {
		return getAccount().getUsername();
	}

	public Playroom getPlayroom() {
		return playroom;
	}

	public void setPlayroom(Playroom playroom) {
		this.playroom = playroom;
	}
	
	
	

}

