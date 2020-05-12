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
import jass.server.Client;

public abstract class Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String[] content;

	
	public Message(String[] content) {
		this.content = content;
		logger.info("Message created:" + this.toString());
	}
	public Message(String[] content, ArrayList<String> elements) {
		this.content = new String[content.length + elements.size()];
		for (int i = 0; i < content.length; i++)
			this.content[i] = content[i];
		for (int i = 0; i < elements.size(); i++)
			this.content[i + content.length] = elements.get(i);
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
			
			
			/* Since there are some different types of Result messages we have to handle it different:
			 * If String array's length is < 3 the message is only for sending true or false
			 * If it is longer, we have to check for the usecase value in third position. Depending on the value,
			 * we have to use different constructors
			 */
			
			if (content[0].equals("Result")) {
				if (content.length < 3) { 
					msg = new Result(content); 
				} else { 
					if (content[2].equals("Token")) msg = new Result(content);
					if (content[2] == "ListPlayrooms") msg = new Result(content);
				}
			
			
			}
			
			
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
		// TODO Auto-generated method stub
	}
	
	public void process() {
		
	}
	public void process(JassClientModel model) {
		// TODO Auto-generated method stub
		
	}
}

