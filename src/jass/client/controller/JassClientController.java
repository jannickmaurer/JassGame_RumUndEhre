package jass.client.controller;

import java.io.IOException;

import jass.client.message.result.Result;
import jass.client.message.result.ResultBroadcastEndGame;
import jass.client.message.result.ResultBroadcastSendPoints;
import jass.client.message.result.ResultBroadcastSendTableCard;
import jass.client.message.result.ResultBroadcastStartGame;
import jass.client.message.result.ResultCreateAccount;
import jass.client.message.result.ResultCreatePlayroom;
import jass.client.message.result.ResultDeleteAccount;
import jass.client.message.result.ResultDeletePlayroom;
import jass.client.message.result.ResultEndGame;
import jass.client.message.result.ResultJoinPlayroom;
import jass.client.message.result.ResultLeavePlayroom;
import jass.client.message.result.ResultListMembers;
import jass.client.message.result.ResultListPlayrooms;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultLogout;
import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultSendTableCard;
import jass.client.message.result.ResultShuffle;
import jass.client.message.result.ResultStartGame;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.client.model.JassClientModel;
import jass.client.view.JassClientView;
import jass.commons.Board;
import jass.message.Message;
import javafx.application.Platform;

public class JassClientController {
	private JassClientModel model;
	private JassClientView view;
	private String token;
	private String currentPlayroom;
	private Board board;
	private String account;
	
	public JassClientController(JassClientModel model, JassClientView view) {
		this.model = model;
		this.view = view;
		
		view.getBtnRun().setOnAction(event -> connect());
		view.getBtnPing().setOnAction(event -> model.ping());
		view.getBtnNewRegistration().setOnAction(event ->{
			createAccount();
			autologin();
		});
		view.getBtnLogin().setOnAction(event ->{
			login();
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
		});
		view.getBtnCreatePlayroomPopup().setOnAction(e -> {
			createPlayroom();
			String name = view.getTfSpielraumName().getText();
			view.getTfSpielraumName().setText("");
			model.addNewPlayroom(name);
			view.getBtnJoin().setDisable(false);
			view.getCreateSpielraumPopUp().hide();
		});
		view.getBtnProfil().setOnAction(e ->{
			if (!view.profilPopUp.isShowing()) 
				view.profilPopUp.show(view.getStage());
		});
		view.getBtnCreatePlayroom().setOnAction(e ->{
			if (!view.createSpielraumPopUp.isShowing()) 
				view.createSpielraumPopUp.show(view.getStage());
		});
		view.getBtnBackPlayroom().setOnAction(e ->{
			view.createSpielraumPopUp.hide();
			view.getTfSpielraumName().setText("");
		});
		view.getBtnBackError().setOnAction(e ->{
			view.errorPopUp.hide();
		});
		view.getBtnBackProfil().setOnAction(e ->{
			view.profilPopUp.hide();
		});
		view.getBtnBackSieger().setOnAction(e ->{
			view.siegerPopUp.hide();
		});
		view.getBtnBackStartGame().setOnAction(e ->{
			view.startGamePopUp.hide();
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
			view.getRoot().setCenter(view.v1);
			view.getRoot().setId("root");
			view.getStage().setTitle("Lobby");
		});
		view.getBtnStartGame().setOnAction(e ->{
			if (!view.startGamePopUp.isShowing()) 
				view.startGamePopUp.show(view.getStage());
		});
		
		view.getBtnStartGamePopUp().setOnAction(e ->{
			startGame();
			view.startGamePopUp.hide();
			view.trumpfPopUp.show(view.getStage());
		});
		
		view.getBtnHearts().setOnAction(e ->{
			view.trumpfPopUp.hide();
			view.wyssPopUp.show(view.getStage());
		});
		view.getBtnDiamonds().setOnAction(e ->{
			view.trumpfPopUp.hide();
			view.wyssPopUp.show(view.getStage());
		});
		view.getBtnSpades().setOnAction(e ->{
			view.trumpfPopUp.hide();
			view.wyssPopUp.show(view.getStage());
		});
		view.getBtnClubs().setOnAction(e ->{
			view.trumpfPopUp.hide();
			view.wyssPopUp.show(view.getStage());
		});
		view.getBtnPush().setOnAction(e ->{
			view.trumpfPopUp.hide();
			view.wyssPopUp.show(view.getStage());
		});
		
		view.getBtnWyss().setOnAction(e ->{
			view.wyssPopUp.hide();
			view.siegerPopUp.show(view.getStage());
		});
		view.getBtnNoWyss().setOnAction(e ->{
			view.wyssPopUp.hide();
			view.siegerPopUp.show(view.getStage());
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

	// Create Message object, called by listener on SimpleStringProperty "lastReceivedMessage"
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
		if (content[0].equals("ResultBroadcastSendMessage")) {
			msg = new ResultBroadcastSendMessage(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLeavePlayroom")) {
			msg = new ResultLeavePlayroom(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultStartGame")) {
			msg = new ResultStartGame(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastStartGame")) {
			msg = new ResultBroadcastStartGame(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastEndGame")) {
			msg = new ResultBroadcastEndGame(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultEndGame")) {
			msg = new ResultEndGame(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastSendPoints")) {
			msg = new ResultBroadcastSendPoints(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultListMembers")) {
			msg = new ResultListMembers(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultSendTableCard")) {
			msg = new ResultSendTableCard(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastSendTableCard")) {
			msg = new ResultBroadcastSendTableCard(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultShuffle")) {
			msg = new ResultShuffle(content);
			if (!msg.isFalse()) msg.process(JassClientController.this);
			if (msg.isFalse()) msg.processIfFalse(JassClientController.this);
		}
		
		
		
	}
	
	private void sendTableCard() {
		String tableCard = "";
		model.sendTableCard(tableCard);
	}

	private void endGame() {
		model.endGame();
	}
	
	private void startGame() {
		model.startGame();
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
		currentPlayroom = "Testraum";
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
	
	public void autologin() {
		model.login(view.getTfNewUsername().getText(), view.getTfNewPassword().getText());
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

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return this.account;
	}
	
	public void LoginSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getRoot().setCenter(view.v1);
				view.getStage().setTitle("Lobby");
			}
		});
	}
	
	public void LogoutSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getRoot().setCenter(view.loginLayout);
				view.getRoot().setBottom(null);
				view.getStage().setTitle("Login");
			}
		});
	}

	public void StartGameSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getLblWait().setText("");
				view.trumpfPopUp.show(view.getStage());
			}
		});	
	}
	
	public void SomethingFailed() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorPopUp.show(view.getStage());
			}
		});
	}	
}
