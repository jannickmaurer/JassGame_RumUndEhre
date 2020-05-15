package jass.server;

import java.util.logging.Logger;

import jass.commons.ServiceLocator;

public class TrumpfGame extends Playroom {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	public TrumpfGame(String name, String owner) {
		super(name, owner);
	}
	
	
	
	

}
