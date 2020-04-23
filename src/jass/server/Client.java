package jass.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import jass.message.Message;

public class Client {
	
	private static final ArrayList<Client> clients = new ArrayList<>();
	
	private Account account = null;
	private String token = null;
	private Socket socket;
	private boolean clientReachable = true;
	
	
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
						System.out.println("Server received message: " + msg.toString());
						// if msg != null -> static method process from class Message
						if(msg != null) {
							msg.process(Client.this);
						} else {
							System.out.println("Message is empty!");
							//TBD: Send new Errormessage
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
	
	public void send(Message msg) {
		try {
			msg.send(socket);	
			System.out.println("Server sent message: " + msg.toString());
		} catch (Exception e) { //TBD: Why not IOException?
			e.printStackTrace();
			this.token = null;
			this.clientReachable = false;
		}
	}

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
	
	

}

