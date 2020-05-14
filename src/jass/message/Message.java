package jass.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.result.Result;
import jass.message.result.ResultCreateAccount;
import jass.message.result.ResultCreatePlayroom;
import jass.message.result.ResultJoinPlayroom;
import jass.message.result.ResultListPlayrooms;
import jass.message.result.ResultLogin;
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
			String[] content = msgText.split("\\|");
			for (int i = 0; i < content.length; i++) {
				content[i] = content[i].trim();
			}
			
			if (content[0].equals("Ping")) msg = new Ping(content);
			if (content[0].equals("CreateAccount")) msg = new CreateAccount(content);
			if (content[0].equals("Login")) msg = new Login(content);
			if (content[0].equals("CreatePlayroom")) msg = new CreatePlayroom(content);
			if (content[0].equals("ListPlayrooms")) msg = new ListPlayrooms(content);
			if (content[0].equals("Result")) msg = new Result(content); 
			if (content[0].equals("ResultLogin")) msg = new ResultLogin(content);
			if (content[0].equals("ResultListPlayrooms")) msg = new ResultListPlayrooms(content);
			if (content[0].equals("ResultCreateAccount")) msg = new ResultCreateAccount(content);
			if (content[0].equals("ResultCreatePlayrom")) msg = new ResultCreatePlayroom(content);
			if (content[0].equals("ResultJoinPlayroom")) msg = new ResultJoinPlayroom(content);

			
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
}

