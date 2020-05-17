package jass.client.controller;

import jass.client.message.result.Result;
import jass.client.message.result.ResultCreateAccount;
import jass.client.message.result.ResultCreatePlayroom;
import jass.client.message.result.ResultDeleteAccount;
import jass.client.message.result.ResultDeletePlayroom;
import jass.client.message.result.ResultJoinPlayroom;
import jass.client.message.result.ResultLeavePlayroom;
import jass.client.message.result.ResultListPlayrooms;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultLogout;
import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultText;
import jass.client.model.JassClientModel;
import jass.client.view.JassClientView;
import jass.message.Message;
import javafx.application.Platform;

public class JassClientController {
	private JassClientModel model;
	private JassClientView view;
	private String token;
	private String currentPlayroom;

	
	public JassClientController(JassClientModel model, JassClientView view) {
		this.model = model;
		this.view = view;
		
		view.getBtnRun().setOnAction(event -> connect());
		view.getBtnPing().setOnAction(event -> model.ping());
		view.getBtnNewRegistration().setOnAction(event ->{
			createAccount();
			view.getRoot().setCenter(view.loginLayout);
			view.getStage().setTitle("Login");
		});
		view.getBtnLogin().setOnAction(event ->{
			login();
			view.getRoot().setCenter(view.lobbyLayout);
			view.getRoot().setBottom(view.v1);
			view.getStage().setTitle("Lobby");
		});
		view.getBtnStart().setOnAction(e ->{
			view.getRoot().setCenter(view.loginLayout);
			view.getStage().setTitle("Login");
		});
		view.getBtnRegistration().setOnAction(e ->{
			view.getRoot().setCenter(view.registrationLayout);
			view.getStage().setTitle("Registration");
		});
		view.getBtnBack().setOnAction(e ->{
			view.getRoot().setCenter(view.loginLayout);
			view.getStage().setTitle("Login");
		});
		view.getBtnLogout().setOnAction(e ->{
			logout();
			view.getRoot().setCenter(view.loginLayout);
			view.getRoot().setBottom(null);
			view.getStage().setTitle("Login");
		});
		view.getBtnCreatePlayroomPopup().setOnAction(e -> {
			createPlayroom();
			String name = view.getTfSpielraumName().getText();
			view.getTfSpielraumName().setText("");
//			model.addNewElement(name);
			view.getBtnJoin().setDisable(false);
			view.getCreateSpielraumPopUp().hide();
		});
		view.getBtnCreatePlayroom().setOnAction(e ->{
			if (!view.createSpielraumPopUp.isShowing()) 
				view.createSpielraumPopUp.show(view.getStage());
		});
		
		view.getBtnJoin().setOnAction(e ->{
			joinPlayroom();
			view.getRoot().setCenter(view.spielraumLayout);
			view.spielraumLayout.setId("rootleft");
			view.getRoot().setBottom(null);
			view.getStage().setTitle("Spielraum");
		});
		
		view.getBtnLeave().setOnAction(e ->{
			leavePlayroonm();
			view.getRoot().setCenter(view.lobbyLayout);
			view.getRoot().setId("root");
			view.getRoot().setBottom(view.v1);
			view.getStage().setTitle("Lobby");
		});
		
		view.getBtnSend().setOnAction(e -> sendMessage());

	
//		model.getTokenProperty().addListener( (o, oldValue, newValue) -> {
//			this.token = newValue;
//		});
		
		model.getMessageProperty().addListener((o, oldValue, newValue) ->{
			System.out.println("Läuft");
			
		});
		
		model.getLastReceivedMessage().addListener( (o, oldValue, newValue) -> {
			String[] content = newValue.split("\\|");
			for (int i = 0; i < content.length; i++) {
				content[i] = content[i].trim();
			}
			createMessage(content);
			
		});	
	}

	private void createMessage(String[] content) {
		Message msg;
		if (content[0].equals("Result")) {
			msg = new Result(content); 
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLogin")) {
			msg = new ResultLogin(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);

		}
		if (content[0].equals("ResultListPlayrooms")) {
			msg = new ResultListPlayrooms(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultCreateAccount")) {
			msg = new ResultCreateAccount(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultCreatePlayroom")) {
			msg = new ResultCreatePlayroom(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultJoinPlayroom")) {
			msg = new ResultJoinPlayroom(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultDeleteAccount")) {
			msg = new ResultDeleteAccount(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultDeletePlayroom")) {
			msg = new ResultDeletePlayroom(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLogout")) {
			msg = new ResultLogout(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultSendMessage")) {
			msg = new ResultSendMessage(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultText")) {
			msg = new ResultText(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLeavePlayroom")) {
			msg = new ResultLeavePlayroom(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		
	}

	protected void leavePlayroonm() {
		model.leavePlayroom();
	}

	private void sendMessage() {
		model.sendMessage(view.getTfMessage().getText());
		view.getTfMessage().setText("");
	}
	
	private void joinPlayroom() {
		model.joinPlayroom("Testraum");
	}
	
	public void listPlayrooms() {
		model.listPlayrooms();
	}
	
	private void deletePlayroom() {
		
	}
	
	private void createPlayroom() {
		if(view.getCbTrumpf().isSelected() == true) {
			model.createPlayroom(view.getTfSpielraumName().getText(), "Trumpf");
		}
	}
	
	public void deleteAccount() {
		
	}
	
	public void createAccount() {
		model.createAccount(view.getTfNewUsername().getText(), view.getTfNewPassword().getText());
	}

	private void logout() {
		model.logout();
	}
	
	public void login() {
		model.login(view.getTfUsername().getText(), view.getTfPassword().getText());
	}
	
	public void connect() {
		model.connect(view.getTfIP().getText(), Integer.parseInt(view.getTfPort().getText()));
	}
	
	public JassClientModel getModel() {
		return model;
	}

	public JassClientView getView() {
		return view;
	}
	
	public void updateChatText(String text) {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getTxtMessages().appendText(text + "\n");
			}
			});
	}

	
}
