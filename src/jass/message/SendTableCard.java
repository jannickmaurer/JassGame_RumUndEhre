package jass.message;

import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultSendTableCard;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.client.message.result.ResultBroadcastSendTableCard;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class SendTableCard extends Message {
	private String token;
	private String tableCard;
	private String nextPlayer;

	public SendTableCard(String[] content) {
		super(content);
		this.token = content[1];
		this.tableCard = content[2];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		
		if(this.token.equals(client.getToken())) {
			Playroom playroom = client.getPlayroom();
			if(playroom != null) {
				String clientCard = client.getName() + ":" + tableCard;
				playroom.addClientCard(clientCard);
				if(!playroom.hasRoundEnded()) nextPlayer = playroom.getPlayerOnTurn(client.getName());
				String[] content = new String[] {"ResultBroadcastSendTableCard", client.getName(), this.tableCard, nextPlayer};
				Message msg = new ResultBroadcastSendTableCard(content);
				playroom.send(msg);
				result = true;
			}
		}
		String[] content2 = new String[] {"ResultSendTableCard", Boolean.toString(result), tableCard};
		client.send(new ResultSendTableCard(content2));	}
}

