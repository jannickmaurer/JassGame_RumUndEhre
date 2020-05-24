package jass.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultCreateAccount;
import jass.client.message.result.ResultCreatePlayroom;
import jass.client.message.result.ResultDeleteAccount;
import jass.client.message.result.ResultDeletePlayroom;
import jass.client.message.result.ResultJoinPlayroom;
import jass.client.message.result.ResultLeavePlayroom;
import jass.client.message.result.ResultListPlayrooms;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultLogout;
import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.server.Client;

public abstract class Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String[] content;

	
	public Message(String[] content) {
		this.content = content;
		logger.info("Message created:" + this.toString());
	}
	

	public void send(Socket socket) throws IOException {
		OutputStreamWriter out;
		out = new OutputStreamWriter(socket.getOutputStream());
		out.write(this.toString() + "\n");
		out.flush();
	}

	public static Message receive(Socket socket) {
		BufferedReader in;
		Message msg = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msgText = in.readLine(); // Will wait here for complete line

			// Break message into individual parts, and remove extra spaces
			String[] content;
			content = msgText.split("\\|");
			
			for (int i = 0; i < content.length; i++) {
				content[i] = content[i].trim();
			}
			
			if (content[0].equals("Ping")) msg = new Ping(content);
			if (content[0].equals("CreateAccount")) msg = new CreateAccount(content);
			if (content[0].equals("Login")) msg = new Login(content);
			if (content[0].equals("CreatePlayroom")) msg = new CreatePlayroom(content);
			if (content[0].equals("ListPlayrooms")) msg = new ListPlayrooms(content);
			if (content[0].equals("Logout")) msg = new Logout(content);
			if (content[0].equals("DeleteAccount")) msg = new DeleteAccount(content);
			if (content[0].equals("DeletePlayroom")) msg = new DeletePlayroom(content);
			if (content[0].equals("JoinPlayroom")) msg = new JoinPlayroom(content);
			if (content[0].equals("SendMessage")) msg = new SendMessage(content);
			if (content[0].equals("Text")) msg = new Text(content);
			if (content[0].equals("LeavePlayroom")) msg = new LeavePlayroom(content);
			if (content[0].equals("StartGame")) msg = new StartGame(content);
			if (content[0].equals("EndGame")) msg = new EndGame(content);
			if (content[0].equals("SendTableCard")) msg = new SendTableCard(content);
			if (content[0].equals("Disconnect")) msg = new Disconnect(content);
			if (content[0].equals("SendTrumpf")) msg = new SendTrumpf(content);
			if (content[0].equals("StartRound")) msg = new StartRound(content);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public String toString() {
		return String.join("|", content);
	}
	public void process(Client client) {
		logger.warning("Wrong process (Message) Used!");
	}
	
	public void process() {
		logger.warning("Wrong process (Message) Used!");
		
	}
	public void process(JassClientModel model) {
		logger.warning("Wrong process (Message) Used!");
	}
	
	public String[] combineArrayAndArrayList(String[] array, ArrayList<String> list) {
		this.content = new String[array.length + list.size()];
		for (int i = 0; i < array.length; i++)
			this.content[i] = array[i];
		for (int i = 0; i < list.size(); i++)
			this.content[i + array.length] = list.get(i);
		return content;
	}
	
	public Boolean isTrue() {
		return this.content[1].equalsIgnoreCase("True");
	}
	public Boolean isFalse() {
		return this.content[1].equalsIgnoreCase("False");
	}


	public void process(JassClientController jassClientController) {
		
	}


	public void processIfFalse(JassClientController jassClientController) {
		// TODO Auto-generated method stub
		
	}
}

