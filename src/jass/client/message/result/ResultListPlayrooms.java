package jass.client.message.result;

import java.util.ArrayList;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.message.Message;

public class ResultListPlayrooms extends Message {
	private ArrayList<String> playrooms;
	private String token;

	public ResultListPlayrooms(boolean result) {
		super(new String[] {"ResultListPlayrooms", Boolean.toString(result)});		
	}
	
	public ResultListPlayrooms(String[] content) {
		super(content);
		playrooms = new ArrayList<>();
		if(content.length > 2) {
			for(int i = 2; i < content.length; i++) {
				this.playrooms.add(content[i]);
			}
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.clearListView();
		if(!playrooms.isEmpty()) {
			for(String s : playrooms) {
				controller.getModel().addNewPlayroom(s);
			}			
		}
		
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}

}
