package jass.message.result;

import java.util.logging.Logger;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultText extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String name;
	private String message;

	public ResultText(String[] content) {
		super(content);
		this.name = content[1];
		this.message = content[2];
	}
	
	@Override
	public void process(JassClientModel model) {
		model.updateChat(this.name + ": " + this.message);
	}

}
