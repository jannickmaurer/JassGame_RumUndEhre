package jass.client.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Individual message as response for client -> server message
 * Triggers events in controller depending on message success (true/false)
 */
public class ResultShuffle extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String tableCardsAsString;
	private ArrayList<String> tableCards;

	public ResultShuffle(boolean result) {
		super(new String[] {"ResultShuffle", Boolean.toString(result)});
	}
	public ResultShuffle(String[] content) {
		super(content);
		if(content.length > 1) {			
			this.tableCardsAsString = content[1];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
//		String[] temp = tableCardsAsString.split("\\|");
//		for (int i = 0; i < temp.length; i++) {
//			temp[i] = temp[i].trim();
//		}
//		for (int i = 0; i < temp.length; i++) {
//			tableCards.add(temp[i]);
//		}
		controller.getBoard().cardListener(tableCardsAsString);
	}
	
	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}

