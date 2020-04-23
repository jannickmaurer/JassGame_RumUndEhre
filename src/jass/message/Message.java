package jass.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.sun.media.jfxmedia.logging.Logger;

import jass.server.Client;

public abstract class Message {
	
	private String[] content;

	public abstract void process(Client client);
	
	public Message(String[] content) {
		this.content = content;
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
			String[] parts = msgText.split("\\|");
			for (int i = 0; i < parts.length; i++) {
				parts[i] = parts[i].trim();
			}
			if (parts[0].equals("Ping")) msg = new Ping(parts);
			if (parts[0].equals("Result")) msg = new Result(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public String toString() {
		return String.join("|", content);
	}
}

