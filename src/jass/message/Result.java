package jass.message;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.server.Client;


// NOT YET DONE! 

public class Result extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String usecase;
	private ArrayList<String> playrooms;
	

	public Result(boolean result) {
		super(new String[] {"Result", Boolean.toString(result)});		
	}

	public Result(String[] content) {
		super(content);
		
		if(content.length > 2) {
			this.usecase = content[2];
			if(this.usecase.equals("Token")) this.token = content[3];	
		}
	}
	
	public Result(String[] content, ArrayList<String> list) {
		super(content, list);
		for(String s : list) {
			this.playrooms.add(s);
		}
		System.out.println(this.toString());
	}
	
	@Override
	public void process(JassClientModel model) {
		if(usecase != null) {
			if(this.usecase.equals("Token")) {
				model.setToken(this.token);
				logger.info("Token set to: " + this.token);
			}
			if(this.usecase.equals("ListPlayrooms")) {
				for(String s : playrooms) {
					model.addNewPlayroom(s);
				}
			}
		} else {
			logger.info("Nothing to process");
		}
	}

	
	
}
