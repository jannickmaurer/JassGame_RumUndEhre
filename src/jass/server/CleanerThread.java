package jass.server;

import java.util.logging.Logger;

/*
 * Class developed by Jannick
 * Some concepts are inspired by or according to Software Engineering Chat Project
 */

public class CleanerThread extends Thread {	private static Logger logger = Logger.getLogger("");

	public CleanerThread() {
		super();
		this.setName("CleanerThread");
	}

	@Override
	public void run() {
		while (true) {
			logger.info("Cleanup process triggered");
			
			Playroom.savePlayrooms();
	
			System.gc();
	
			try {
				Thread.sleep(60000); // Every 1 minute
			} catch (InterruptedException e) {
			}
		}
	}
}