package jass.client.message.result;

import java.util.ArrayList;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.message.Message;

public class ResultListMembers extends Message {
	private ArrayList<String> members;

	public ResultListMembers(boolean result) {
		super(new String[] {"ResultListMembers", Boolean.toString(result)});		
	}
	
	public ResultListMembers(String[] content) {
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
		// TODO Auto-generated method stub
		
	}

}
