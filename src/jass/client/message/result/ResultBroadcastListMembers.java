package jass.client.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultBroadcastListMembers extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	private ArrayList<String> members;
	
	public ResultBroadcastListMembers(String[] content) {
		super(content);
		if(content.length > 1) {
			members = new ArrayList<>();
			for(int i = 1; i < content.length; i++) {
				this.members.add(content[i]);
			}
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		
	}

	public void processIfFalse(JassClientController jassClientController) {
		
	}
}
