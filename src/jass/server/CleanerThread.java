package jass.server;

import java.util.logging.Logger;

public class CleanerThread extends Thread {	private static Logger logger = Logger.getLogger("");

	public CleanerThread() {
		super();
		this.setName("CleanerThread");
	}

	@Override
	public void run() {
		while (true) {
			logger.info("Cleanup process triggered");
	
			// Clean up clients
//			Client.cleanupClients();
	
			// Clean up accounts
//			Account.cleanupAccounts();
//			Account.saveAccounts();
	
			// Clean up chatrooms
//			Chatroom.cleanupChatrooms();
			Playroom.savePlayrooms();
	
			System.gc();
	
			// Log status
//			long freeMemory = Runtime.getRuntime().freeMemory();
//			freeMemory /= (1024 * 1024);
//			logger.info("Cleanup process complete; " + freeMemory + "MB available, " +
//						currentThread().activeCount() + " threads running");
	
			try {
				Thread.sleep(60000); // Every 1 minute
			} catch (InterruptedException e) {
			}
		}
	}
}