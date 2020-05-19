package jass.client.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import jass.commons.Configuration;
import jass.commons.ServiceLocator;
import jass.commons.Translator;
import jass.message.CreateAccount;
import jass.message.CreatePlayroom;
import jass.message.EndGame;
import jass.message.JoinPlayroom;
import jass.message.LeavePlayroom;
import jass.message.ListPlayrooms;
import jass.message.Login;
import jass.message.Logout;
import jass.message.MakeTrumpf;
import jass.message.Message;
import jass.message.Ping;
import jass.message.SendMessage;
import jass.message.StartGame;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class JassClientModel {
	private Socket socket = null;
	private SimpleStringProperty token = new SimpleStringProperty();
	private SimpleStringProperty message = new SimpleStringProperty();
	private SimpleStringProperty lastReceivedMessage = new SimpleStringProperty();

	private static ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
	private static Logger logger = serviceLocator.getClientLogger();
	private ObservableList<String> playrooms = FXCollections.observableArrayList();
	
	public void connect(String ipAdress, int port) {
		try {
			socket = new Socket(ipAdress, port);
			
			Runnable r = new Runnable() {
				public void run() {
					while (true) {
						BufferedReader in;
						try {
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							String msgText = in.readLine(); // Will wait here for complete line
							lastReceivedMessage.setValue(msgText);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
//						Message msg = Message.receive(socket);
//						
//						// Only "Result" messages got sent to Client. Therefore, we use process method from Result class and
//						// provide the model to the method in order for it to able to use model's methods
//						
//						if(msg != null) {
//							if(!msg.isFalse()) {
//								msg.process(JassClientModel.this); 
//							}
//						}
//						System.out.println("Client Message received: " + msg.toString());
//						}
				}
			};
			Thread t = new Thread(r);
			t.start();
			logger.info("Client Connected");
			
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
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createAccount(String username, String password) {
		String[] content = new String[] {"CreateAccount", username, password};
		Message msg = new CreateAccount(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(String username, String password) {
		String[] content = new String[] {"Login", username, password};
		Message msg = new Login(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {
		String[] content = new String[] {"Logout", this.token.getValue()};
		Message msg = new Logout(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createPlayroom(String name, String playmode) {
		String[] content = new String[] {"CreatePlayroom", this.token.getValue(), name, playmode};
		Message msg = new CreatePlayroom(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listPlayrooms() {
		String[] content = new String[] {"ListPlayrooms", this.token.getValue()};
		Message msg = new ListPlayrooms(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void joinPlayroom(String name) {
		String[] content = new String[] {"JoinPlayroom", this.token.getValue(), name};
		Message msg = new JoinPlayroom(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void leavePlayroom() {
		String[] content = new String[] {"LeavePlayroom", this.token.getValue()};
		Message msg = new LeavePlayroom(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void sendMessage(String message) {
		String[] content = new String[] {"SendMessage", this.token.getValue(), message};
		Message msg = new SendMessage(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startGame() {
		String[] content = new String[] {"StartGame", this.token.getValue()};
		Message msg = new StartGame(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void endGame() {
		String[] content = new String[] {"EndGame", this.token.getValue()};
		Message msg = new EndGame(content);
		try {
			msg.send(socket);
			logger.info("Client tries to send message: " + msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void addNewPlayroom(String playroom) {
		playrooms.add(playroom);
	}
    
    public void removePlayroom(String playroom) {
		playrooms.remove(playroom);
	}
	
	public ObservableList<String> getElements() {
		return playrooms;
	}
	public SimpleStringProperty getTokenProperty() {
		return token;
	}
	public void setToken(String token) {
		this.token.set(token);
		logger.info("Client set token to: " + this.token.getValue());
	}
	
	public void updateChat(String message) {
		System.out.println("Sollte gehen");
		this.message.set(message);
	}
	
	public SimpleStringProperty getMessageProperty() {
		return this.message;
	}
	
	public SimpleStringProperty getLastReceivedMessage() {
		return lastReceivedMessage;
	}

	public void setLastReceivedMessage(SimpleStringProperty lastReceivedMessage) {
		this.lastReceivedMessage = lastReceivedMessage;
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
