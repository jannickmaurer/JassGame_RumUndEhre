package jass.client.model;


import java.io.IOException;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import jass.commons.Configuration;
import jass.commons.ServiceLocator;
import jass.commons.Translator;
import jass.message.Message;
import jass.message.Ping;
import javafx.concurrent.Task;

public class JassClientModel {
	private Socket socket = null;
	
	ServiceLocator serviceLocator;
	
	public void connect(String ipAdress, int port) {
		try {
			socket = new Socket(ipAdress, port);
			
			Runnable r = new Runnable() {
				public void run() {
					while (true) {
						Message msg = Message.receive(socket);
						System.out.println("Client Message received: " + msg.toString());
						}
					
				}
			};
			Thread t = new Thread(r);
			t.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ping() {
		String[] content = new String[2];
		content[0] = "Ping";
		Message msg = new Ping(content);
		try {
			msg.send(socket);
			System.out.println("Client sent message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
        new Thread(initializer).start();
    }
	
	final Task<Void> initializer = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            this.updateProgress(1,  6);

            // Create the service locator to hold our resources
            serviceLocator = ServiceLocator.getServiceLocator();
            this.updateProgress(2,  6);

            // Initialize the resources in the service locator
            serviceLocator.setClientLogger(configureLogging());
            this.updateProgress(3,  6);

            serviceLocator.setConfiguration(new Configuration());
            this.updateProgress(4,  6);

            String language = serviceLocator.getConfiguration().getOption("Language");
            serviceLocator.setTranslator(new Translator(language));
            this.updateProgress(5,  6);
            
            // ... more resources would go here...
            this.updateProgress(6,  6);
            
            return null;
        }
    };
    
    private Logger configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.FINEST);

        // By default there is one handler: the console
        Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
        defaultHandlers[0].setLevel(Level.INFO);

        // Add our logger
        Logger ourLogger = Logger.getLogger(serviceLocator.getAPP_NAME());
        ourLogger.setLevel(Level.FINEST);
        
        // Add a file handler, putting the rotating files in the tmp directory
        try {
            Handler logHandler = new FileHandler("%t/"
                    + serviceLocator.getAPP_NAME() + "_%u" + "_%g" + ".log",
                    1000000, 9);
            logHandler.setLevel(Level.FINEST);
            ourLogger.addHandler(logHandler);
        } catch (Exception e) { // If we are unable to create log files
            throw new RuntimeException("Unable to initialize log files: "
                    + e.toString());
        }

        return ourLogger;
    }
	

}
