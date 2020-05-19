package jass.message;

import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultSendTableCard;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.client.message.result.ResultBroadcastSendTableCard;
import jass.server.Client;
import jass.server.Playroom;

public class SendTableCard extends Message {
	private String token;
	private String tableCard;

	public SendTableCard(String[] content) {
		super(content);
		this.token = content[1];
		this.tableCard = content[2];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		
		if(this.token.equals(client.getToken())) {
			Playroom targetPlayroom = client.getPlayroom();
			if(targetPlayroom != null) {
				String[] content = new String[] {"ResultBroadcastSendTableCard", client.getAccount().getUsername(), this.tableCard};
				Message msg = new ResultBroadcastSendTableCard(content);
				targetPlayroom.getChatroom().send(msg);
				result = true;
			}
		}
		client.send(new ResultSendTableCard(result));
	}
}
