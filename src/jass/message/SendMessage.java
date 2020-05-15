package jass.message;

import jass.message.result.ResultSendMessage;
import jass.message.result.ResultText;
import jass.server.Client;
import jass.server.Playroom;

public class SendMessage extends Message {
	private String token;
	private String targetPlayroom;
	private String message;

	public SendMessage(String[] content) {
		super(content);
		this.token = content[1];
		this.targetPlayroom = content[2];
		this.message = content[3];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		
		if(this.token.equals(client.getToken())) {
			Playroom targetPlayroom = Playroom.getPlayroom(this.targetPlayroom);
			if(targetPlayroom != null) {
				String[] content = new String[] {"Text", client.getName(), this.message};
				Message msg = new ResultText(content);
				targetPlayroom.getChatroom().send(msg);
				result = true;
			}
		}
		client.send(new ResultSendMessage(result));
	}
}
