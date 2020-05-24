package jass.client.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultBroadcastEndGame;
import jass.client.message.result.ResultBroadcastListMembers;
import jass.client.message.result.ResultBroadcastSendPoints;
import jass.client.message.result.ResultBroadcastSendTableCard;
import jass.client.message.result.ResultBroadcastSendTrumpf;
import jass.client.message.result.ResultBroadcastStartGame;
import jass.client.message.result.ResultBroadcastStartRound;
import jass.client.message.result.ResultCreateAccount;
import jass.client.message.result.ResultCreatePlayroom;
import jass.client.message.result.ResultDeleteAccount;
import jass.client.message.result.ResultDeletePlayroom;
import jass.client.message.result.ResultDisconnect;
import jass.client.message.result.ResultEndGame;
import jass.client.message.result.ResultJoinPlayroom;
import jass.client.message.result.ResultLeavePlayroom;
import jass.client.message.result.ResultListMembers;
import jass.client.message.result.ResultListPlayrooms;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultLogout;
import jass.client.message.result.ResultSendMessage;
import jass.client.message.result.ResultSendTableCard;
import jass.client.message.result.ResultSendTrumpf;
import jass.client.message.result.ResultShuffle;
import jass.client.message.result.ResultStartGame;
import jass.client.message.result.ResultStartRound;
import jass.client.message.result.ResultBroadcastSendMessage;
import jass.client.model.JassClientModel;
import jass.client.view.CardLabel;
import jass.client.view.JassClientView;
import jass.client.view.PlayerPane;
import jass.client.view.OtherPlayerPane;
import jass.commons.Board;
import jass.commons.Card;
import jass.commons.ServiceLocator;
import jass.message.Message;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;

public class JassClientController {

	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private JassClientModel model;
	private JassClientView view;
	private String token;
	private String currentPlayroom;
	private Board board;
	private String username;
	private String currentGameType;
	private ObservableList<String> playrooms = FXCollections.observableArrayList();
	private SimpleStringProperty playerOnTurn = new SimpleStringProperty();
	private SimpleStringProperty gameType = new SimpleStringProperty();
	private SimpleStringProperty Trumpf = new SimpleStringProperty();
	private SimpleStringProperty playerToStartRound = new SimpleStringProperty();
	private ArrayList<String> members = new ArrayList<>();
	private String owner;

	public String getCurrentGameType() {
		return currentGameType;
	}

	public void setCurrentGameType(String currentGameType) {
		this.currentGameType = currentGameType;
		this.board.setGameTyp(currentGameType);
	}

	public JassClientController(JassClientModel model, JassClientView view) {
		this.model = model;
		this.view = view;
		
		view.getBtnRun().setOnAction(event -> connect());
		view.getBtnNewRegistration().setOnAction(event -> {
			createAccount();
		});
		view.getBtnLogin().setOnAction(event -> {
			login();
		});
		view.getBtnStart().setOnAction(e -> {
			view.getRoot().setCenter(view.loginLayout);
			view.getStage().setTitle("Login");
		});
		view.getBtnRegistration().setOnAction(e -> {
			view.getRoot().setCenter(view.registrationLayout);
			view.getStage().setTitle("Registration");
		});
		view.getBtnBack().setOnAction(e -> {
			view.getRoot().setCenter(view.loginLayout);
			view.getStage().setTitle("Login");
			view.getTfNewUsername().setText("");
			view.getPfNewPassword().setText("");
		});
		view.getBtnLogout().setOnAction(e -> {
			logout();
		});
		view.getBtnDeleteAccount().setOnAction(e -> {
			deleteAccount();
			view.profilPopUp.hide();
		});
		view.getBtnDeletePlayroom().setOnAction(e -> {
			deletePlayroom();
		});
		view.getBtnCreatePlayroomPopup().setOnAction(e -> {
			createPlayroom();
			String name = view.getTfSpielraumName().getText();
			view.getTfSpielraumName().setText("");
			view.getBtnJoin().setDisable(false);
			view.getCreateSpielraumPopUp().hide();
		});
		view.getBtnProfil().setOnAction(e -> {
			if (!view.profilPopUp.isShowing())
				view.profilPopUp.show(view.getStage());
		});
		view.getBtnCreatePlayroom().setOnAction(e -> {
			if (!view.createSpielraumPopUp.isShowing())
				view.createSpielraumPopUp.show(view.getStage());
		});
		view.getBtnBackPlayroom().setOnAction(e -> {
			view.createSpielraumPopUp.hide();
			view.getTfSpielraumName().setText("");
		});
		view.getBtnBackError().setOnAction(e -> {
			view.errorPopUp.hide();
		});
		view.getBtnBackErrorConnect().setOnAction(e -> {
			view.errorConnectPopUp.hide();
		});
		view.getBtnBackErrorCreatePlayroom().setOnAction(e -> {
			view.errorCreatePlayroomPopUp.hide();
		});
		view.getBtnBackErrorLogin().setOnAction(e -> {
			view.errorLoginPopUp.hide();
		});
		view.getBtnBackErrorRegistration().setOnAction(e -> {
			view.errorRegistrationPopUp.hide();
		});
		view.getBtnBackErrorStartGame().setOnAction(e -> {
			view.errorStartGamePopUp.hide();
		});
		view.getBtnBackProfil().setOnAction(e -> {
			view.profilPopUp.hide();
		});
		view.getBtnBackSieger().setOnAction(e -> {
			view.siegerPopUp.hide();
		});
		view.getBtnBackStartGame().setOnAction(e -> {
			view.startGamePopUp.hide();
		});
		view.getBtnJoin().setOnAction(e -> {
			joinPlayroom();

		});
		view.getBtnLeave().setOnAction(e -> {
			leavePlayroonm();
			view.getRoot().setCenter(view.getV1());
			view.getRoot().setId("root");
			view.getStage().setTitle("Lobby");
		});
		view.getBtnStartGame().setOnAction(e -> {
			if (!view.startGamePopUp.isShowing())
				view.startGamePopUp.show(view.getStage());
		});

		view.getBtnStartGamePopUp().setOnAction(e -> {
			startGame();
			view.startGamePopUp.hide();
		});

		view.getBtnHearts().setOnAction(this::startRound);

		view.getBtnDiamonds().setOnAction(this::startRound);
		view.getBtnSpades().setOnAction(this::startRound);
		view.getBtnClubs().setOnAction(this::startRound);
		view.getBtnPush().setOnAction(this::startRound);

		view.getBtnWyss().setOnAction(e -> {
//			view.wyssPopUp.hide();
//			view.siegerPopUp.show(view.getStage());
		});
		view.getBtnNoWyss().setOnAction(e -> {
//			view.wyssPopUp.hide();
//			view.siegerPopUp.show(view.getStage());
		});

		view.getBtnTrumpf().setOnAction(e -> {
			view.gameTypePopup.hide();
			view.trumpfPopUp.show(view.getStage());
		});

		view.getBtnObeAbe().setOnAction(this::startRound);
		view.getBtnUndeUfe().setOnAction(this::startRound);
		view.getBtnSlalomObeAbe().setOnAction(this::startRound);
		view.getBtnSlalomUndeUfe().setOnAction(this::startRound);

		view.getBtnSend().setOnAction(e -> {
//			sendTableCard();
			sendMessage();
		});

		model.getPlayrooms().addListener((ListChangeListener<String>) change -> {
			Platform.runLater(new Runnable() {
				public void run() {
					while (change.next()) {
						view.getListView().scrollTo(change.getFrom());
					}
				}
			});
		});

//		model.getTokenProperty().addListener( (o, oldValue, newValue) -> {
//			this.token = newValue;
//		});

		model.getMessageProperty().addListener((o, oldValue, newValue) -> {
			System.out.println("Läuft");

		});

		model.getLastReceivedMessage().addListener((o, oldValue, newValue) -> {
			if (!newValue.equals("")) {
				String[] content = newValue.split("\\|");
				for (int i = 0; i < content.length; i++) {
					content[i] = content[i].trim();
				}
				createMessage(content);
				model.getLastReceivedMessage().setValue("");
			}
		});

		view.getStage().setOnCloseRequest((event) -> {
			if (model.isConnected()) {
				model.disconnect();
			}
		});

		for (int i = 0; i < view.getSpielraumLayout().getPlayerPane().getCardLabels().size(); i++) {
			view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).setOnMouseReleased(this::sendTableCard);
			logger.info("Card Mouse-Listener on Card"
					+ view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).getCardNameAsString()
					+ " running");
		}
		for (int i = 0; i < view.getSpielraumLayout().getPlayerPane().getCardLabels().size(); i++) {
			view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).setOnMouseEntered(this::highlightCard);
			logger.info("Card Mouse-Listener on Card"
					+ view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).getCardNameAsString()
					+ " running");
		}
		for (int i = 0; i < view.getSpielraumLayout().getPlayerPane().getCardLabels().size(); i++) {
			view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).setOnMouseExited(this::delightCard);
			logger.info("Card Mouse-Listener on Card"
					+ view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).getCardNameAsString()
					+ " running");
		}
		
		playerOnTurn.addListener((o, oldValue, newValue) ->{
			if(newValue.equals(this.username)) {
				board.play();
				logger.info("Playable Handcards: " + board.getPlayableHandCards());
				for(int i = 0; i < view.getSpielraumLayout().getPlayerPane().getCardLabels().size(); i++) {
					String temp = view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).getCardNameAsString();
					for(Card c : board.handCards.getPlayableHandCards()) {
						if(c.toString().equals(temp)) {
							view.getSpielraumLayout().getPlayerPane().getCardLabels().get(i).setDisable(false);
						}
					}
				}
			}
		});
		
		playerToStartRound.addListener((o, oldValue, newValue) ->{
			if(newValue.equals(this.username)) {
				
			}
			
		});
	}

	public ObservableList<String> getPlayrooms() {
		return playrooms;
	}

	public void setPlayrooms(ObservableList<String> playrooms) {
		this.playrooms = playrooms;
	}

	// Create Message object, called by listener on SimpleStringProperty
	// "lastReceivedMessage"
	private void createMessage(String[] content) {
		Message msg;
		if (content[0].equals("ResultPing")) {
			msg = new ResultPing(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLogin")) {
			msg = new ResultLogin(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);

		}
		if (content[0].equals("ResultListPlayrooms")) {
			msg = new ResultListPlayrooms(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultCreateAccount")) {
			msg = new ResultCreateAccount(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultCreatePlayroom")) {
			msg = new ResultCreatePlayroom(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultJoinPlayroom")) {
			msg = new ResultJoinPlayroom(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultDeleteAccount")) {
			msg = new ResultDeleteAccount(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultDeletePlayroom")) {
			msg = new ResultDeletePlayroom(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLogout")) {
			msg = new ResultLogout(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultSendMessage")) {
			msg = new ResultSendMessage(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastSendMessage")) {
			msg = new ResultBroadcastSendMessage(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultLeavePlayroom")) {
			msg = new ResultLeavePlayroom(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultStartGame")) {
			msg = new ResultStartGame(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastStartGame")) {
			msg = new ResultBroadcastStartGame(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastEndGame")) {
			msg = new ResultBroadcastEndGame(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultEndGame")) {
			msg = new ResultEndGame(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastSendPoints")) {
			msg = new ResultBroadcastSendPoints(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultListMembers")) {
			msg = new ResultListMembers(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultSendTableCard")) {
			msg = new ResultSendTableCard(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastSendTableCard")) {
			msg = new ResultBroadcastSendTableCard(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultShuffle")) {
			msg = new ResultShuffle(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultDisconnect")) {
			msg = new ResultDisconnect(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultSendTrumpf")) {
			msg = new ResultSendTrumpf(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastSendTrumpf")) {
			msg = new ResultBroadcastSendTrumpf(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastListMembers")) {
			msg = new ResultBroadcastListMembers(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultBroadcastStartRound")) {
			msg = new ResultBroadcastStartRound(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}
		if (content[0].equals("ResultStartRound")) {
			msg = new ResultStartRound(content);
			if (!msg.isFalse())
				msg.process(JassClientController.this);
			if (msg.isFalse())
				msg.processIfFalse(JassClientController.this);
		}

	}

	private void startRound(Event event) {
		String gameType = null;
		String additionalInfo = null;
		if (event.getSource() == view.getBtnObeAbe())
			gameType = "ObeAbe";
		if (event.getSource() == view.getBtnUndeUfe())
			gameType = "UndeUfe";
		if (event.getSource() == view.getBtnSlalomObeAbe()) {
			gameType = "Slalom";
			additionalInfo = "ObeAbe";
		}
		if (event.getSource() == view.getBtnSlalomUndeUfe()) {
			gameType = "Slalom";
			additionalInfo = "UndeUfe";
		} else {
			gameType = "Trumpf";
			if (event.getSource() == view.getBtnHearts())
				additionalInfo = "H";
			if (event.getSource() == view.getBtnClubs())
				additionalInfo = "C";
			if (event.getSource() == view.getBtnDiamonds())
				additionalInfo = "D";
			if (event.getSource() == view.getBtnSpades())
				additionalInfo = "S";
		}
		model.startRound(gameType, additionalInfo);

	}

	private void sendTrumpf() {
		model.sendTrumpf("Hearts");
	}

	private void sendTableCard(Event event) {
		CardLabel cl = (CardLabel) event.getSource();
		model.sendTableCard(cl.getCardNameAsString());
		logger.info("Send Tablecard: " + cl.getCardNameAsString());
	}

	private void endGame() {
		model.endGame();
	}

	private void startGame() {
		model.startGame(view.getTfPoints().getText());
	}

	protected void leavePlayroonm() {
		model.leavePlayroom();
	}

	private void sendMessage() {
		model.sendMessage(view.getTfMessage().getText());
		view.getTfMessage().setText("");
	}

	private void joinPlayroom() {
		model.joinPlayroom(view.getListView().getSelectionModel().getSelectedItem());
		view.getSpielraumLayout().getPlayerPane().getLblName().setText(view.getTfUsername().getText());
	}

	public void listPlayrooms() {
		model.listPlayrooms();
	}

	private void deletePlayroom() {

	}

	private void createPlayroom() {
			model.createPlayroom(view.getTfSpielraumName().getText(), "Trumpf");
	}

	public void deleteAccount() {

	}

	public void createAccount() {
		model.createAccount(view.getTfNewUsername().getText(), view.getPfNewPassword().getText());
	}

	private void logout() {
		model.logout();
	}

	public void login() {
		model.login(view.getTfUsername().getText(), view.getPfPassword().getText());
	}

	public void autologin() {
		model.login(view.getTfNewUsername().getText(), view.getPfNewPassword().getText());
		view.getTfNewUsername().setText("");
		view.getPfNewPassword().setText("");
	}

	public void connect() {
		try {
			model.connect(view.getTfIP().getText(), Integer.parseInt(view.getTfPort().getText()));
		} catch (Exception e) {
			logger.info("Server down");
			somethingFailedConnect();
		}
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
		this.username = account;
	}

	public String getAccount() {
		return this.username;
	}

	public void loginSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getRoot().setCenter(view.getV1());
				view.getStage().setTitle("Lobby");
				view.getTfUsername().setText("");
				view.getPfPassword().setText("");
			}
		});
	}

	public void logoutSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getRoot().setCenter(view.loginLayout);
				view.getRoot().setBottom(null);
				view.getStage().setTitle("Login");
			}
		});
	}

	public void joinSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getRoot().setCenter(view.spielraumLayout);
				view.spielraumLayout.setId("rootleft");
				view.getRoot().setBottom(null);
				view.getStage().setTitle("Spielraum");
			}
		});
	}

	public void startGameSuccess() {
		Platform.runLater(new Runnable() {
			public void run() {
//				view.getLblWait().setText("");
				view.gameTypePopup.show(view.getStage());
//				view.trumpfPopUp.show(view.getStage());
			}
		});
	}

	public void somethingFailed() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorPopUp.show(view.getStage());
			}
		});
	}
	
	public void somethingFailedConnect() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorConnectPopUp.show(view.getStage());
			}
		});
	}
	
	public void somethingFailedCreatePlayroom() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorCreatePlayroomPopUp.show(view.getStage());
			}
		});
	}
	
	public void somethingFailedLogin() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorLoginPopUp.show(view.getStage());
			}
		});
	}
	
	public void somethingFailedRegistration() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorRegistrationPopUp.show(view.getStage());
			}
		});
	}
	
	public void somethingFailedStartGame() {
		Platform.runLater(new Runnable() {
			public void run() {
				view.errorStartGamePopUp.show(view.getStage());
			}
		});
	}

	public void createBoard() {
		board = new Board(currentPlayroom, currentGameType);
		board.setMembers(this.members);
		logger.info("Client board created: " + currentPlayroom + " / " + currentGameType);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCurrentPlayroom() {
		return currentPlayroom;
	}

	public void setCurrentPlayroom(String currentPlayroom) {
		this.currentPlayroom = currentPlayroom;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setModel(JassClientModel model) {
		this.model = model;
	}

	public void setView(JassClientView view) {
		this.view = view;
	}

	public void addNewPlayroom(String playroom) {
		playrooms.add(playroom);
	}

	public void clearListView() {
		if (!view.getListView().getItems().isEmpty()) {

			Platform.runLater(new Runnable() {
				public void run() {
					view.getListView().getItems().clear();
				}
			});
		}
	}

	public void updatePlayerPane(String playedCard) {
		logger.info("Update PlayerPane: " + board.getHandCards().toString());
		Platform.runLater(new Runnable() {
			public void run() {
				PlayerPane pp = view.getSpielraumLayout().getPlayerPane();
				pp.updatePlayerDisplay(board.getHandCards(), playedCard);

			}
		});
	}

	public void updatePlayerPane() {
		logger.info("Update PlayerPane: " + board.getHandCards().toString());
		Platform.runLater(new Runnable() {
			public void run() {
				PlayerPane pp = view.getSpielraumLayout().getPlayerPane();
				pp.updatePlayerDisplay(board.getHandCards());
			}
		});

//		view.getRoot().setCenter(view.spielraumLayout);
	}

	public void startRoundSuccess() {
		if (view.trumpfPopUp.isShowing()) {
			Platform.runLater(new Runnable() {
				public void run() {
					view.trumpfPopUp.hide();
				}
			});
		}
	}

//	public void createOtherPlayerPane(int countMembers, String username) {
//		Platform.runLater(new Runnable() {
//			public void run() {
//				view.getSpielraumLayout().createOtherPlayerPane(countMembers, username);
//			}
//		});
//	}
	
	public void createOtherPlayerPanes(ArrayList<String> members) {
		Platform.runLater(new Runnable() {
			public void run() {
				
				view.getSpielraumLayout().clearOtherPlayerPaneList();
				view.getSpielraumLayout().createOtherPlayerPanes(members);
			}
		});
	}

	public void addPoints(String username, int points) {
		if (username.equals(this.username)) {
			Platform.runLater(new Runnable() {
				public void run() {
					int total = points + Integer
							.parseInt(view.getSpielraumLayout().getPlayerPane().getLblPointsPlayer().getText());
					view.getSpielraumLayout().getPlayerPane().getLblPointsPlayer().setText(Integer.toString(points));
				}
			});

		} else {
			Platform.runLater(new Runnable() {
				public void run() {
					int total = points + Integer.parseInt(
							view.getSpielraumLayout().getOtherPlayerPane(username).getLblPointsPlayer().getText());
					view.getSpielraumLayout().getOtherPlayerPane(username).getLblPointsPlayer()
							.setText(Integer.toString(total));
				}
			});
		}
	}

	public void highlightCard(Event event) {
		CardLabel cl = (CardLabel) event.getSource();
		cl.setStyle("-fx-border-width: 5; -fx-border-color: black");
//		cl.setStyle();
	}

	public void delightCard(Event event) {
		CardLabel cl = (CardLabel) event.getSource();
		cl.setStyle("-fx-border-width: 0");
	}

	public void playroomName(String name) {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getSpielraumLayout().getLblPlayroomName().setText(name);
			}
		});
	}

	public void maxPoints(String maxPoints) {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getSpielraumLayout().getLblPointsGoalIs().setText(maxPoints);
			}
		});
	}

	public void trumpf(String trumpf) {
		Platform.runLater(new Runnable() {
			public void run() {
				switch(trumpf) {
				case "H":
					view.getSpielraumLayout().getLblTrumpfIs().setText("Herz");
					break;
				case "D":
					view.getSpielraumLayout().getLblTrumpfIs().setText("Ecken");
					break;
				case "S":
					view.getSpielraumLayout().getLblTrumpfIs().setText("Schaufel");
					break;
				case "C":
					view.getSpielraumLayout().getLblTrumpfIs().setText("Kreuz");
					break;
				}
			}
		});
	}

	public void gameType(String gameType) {
		Platform.runLater(new Runnable() {
			public void run() {
				switch(gameType) {
				case "Trumpf":
					view.getSpielraumLayout().getLblTrumpf().setText("Trumpf");
					break;
				case "Slalom":
					view.getSpielraumLayout().getLblTrumpf().setText("Slalom");
					break;
				case "Unde Ufe":
					view.getSpielraumLayout().getLblTrumpfIs().setText("Unde Ufe");
					break;
				case "Obe Abe":
					view.getSpielraumLayout().getLblTrumpfIs().setText("Obe Abe");
					break;
				}
				view.getSpielraumLayout().getLblGameTypeIs().setText(gameType);
			}
		});
	}

	public void ownerName(String owner) {
		Platform.runLater(new Runnable() {
			public void run() {
				view.getSpielraumLayout().getLblOwnerIs().setText(owner);
			}
		});
	}

	public String getPlayerOnTurn() {
		return playerOnTurn.getValue();
	}

	public void setPlayerOnTurn(String playerOnTurn) {
		this.playerOnTurn.setValue(playerOnTurn);
	}

	public String getPlayerToStartRound() {
		return playerToStartRound.getValue();
	}

	public void setPlayerToStartRound(String playerToStartRound) {
		this.playerToStartRound.setValue(playerToStartRound);
	}

	public void setMembers(ArrayList<String> members) {
		this.members.clear();
		for(String m : members) {
			this.members.add(m);
		}
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void updatePlayedCard(String name, String tableCard) {
		Platform.runLater(new Runnable() {
			public void run() {
//				view.getSpielraumLayout().clearPlayedCards();
				if(name.equals(username)) {
					view.getSpielraumLayout().updatePlayedCard("Own", tableCard);

				} else {
					view.getSpielraumLayout().updatePlayedCard(name, tableCard);
				}
			}
		});

	}
	
	
}
