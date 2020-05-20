package jass.message;

import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class SendMessage extends Message {
	private String token;
	private String message;

	public SendMessage(String[] content) {
		super(content);
		this.token = content[1];
		this.message = content[2];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		
		if(this.token.equals(client.getToken())) {
			Playroom targetPlayroom = client.getPlayroom();
			if(targetPlayroom != null) {
				String[] content = new String[] {"ResultBroadcastSendMessage", client.getAccount().getUsername(), this.message};
				Message msg = new ResultBroadcastSendMessage(content);
				targetPlayroom.getChatroom().send(msg);
				result = true;
			}
		}
		client.send(new ResultSendMessage(result));
	}
}

