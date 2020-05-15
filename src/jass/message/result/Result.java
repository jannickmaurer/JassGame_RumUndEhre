package jass.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;
import jass.server.Client;


// NOT YET DONE! 

public class Result extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String usecase;
	
	public Result(boolean result) {
		super(new String[] {"Result", Boolean.toString(result)});		
	}

	public Result(String[] content) {
		super(content);
//		System.out.println("Richtig");
	}
	
//	public Result(String[] content, ArrayList<String> list) {
//		super(content, list);
//		System.out.println(this.toString());
//	}
	
	@Override
	public void process(JassClientModel model) {
		logger.warning("Wrong process (Result) Used!");
	}
}
