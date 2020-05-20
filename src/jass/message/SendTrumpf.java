package jass.message;

import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultSendTableCard;
import jass.client.message.result.ResultSendTrumpf;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.client.message.result.ResultBroadcastSendTableCard;
import jass.client.message.result.ResultBroadcastSendTrumpf;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class SendTrumpf extends Message {
	private String token;
	private String trumpf;

	public SendTrumpf(String[] content) {
		super(content);
		this.token = content[1];
		this.trumpf = content[2];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		
		if(this.token.equals(client.getToken())) {
			
			
			
			String[] content = new String[] {"ResultBroadcastSendTrumpf", this.trumpf};
			Message msg = new ResultBroadcastSendTrumpf(content);
			result = true;
		}
		client.send(new ResultSendTrumpf(result));
	}
}
