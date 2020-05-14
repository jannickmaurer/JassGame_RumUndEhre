package jass.message.result;

import java.util.ArrayList;

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
		for(int i = 2; i < content.length; i++) {
			this.playrooms.add(content[i]);
		}
	}
	@Override
	public void process(JassClientModel model) {
		for(String s : playrooms) {
			model.addNewPlayroom(s);
		}
		
	}

	

}
