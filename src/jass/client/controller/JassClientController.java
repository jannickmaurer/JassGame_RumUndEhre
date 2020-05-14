package jass.client.controller;

import jass.client.model.JassClientModel;
import jass.client.view.JassClientView;

public class JassClientController {
	private JassClientModel model;
	private JassClientView view;
	private String token;

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
			view.getRoot().setCenter(view.loginLayout);
			view.getRoot().setBottom(null);
			view.getStage().setTitle("Login");
		});
		view.getBtnCreatePlayroomPopup().setOnAction(e -> {
			createPlayroom();
			String name = view.getTfSpielraumName().getText();
			view.getTfSpielraumName().setText("");
			model.addNewElement(name);
			view.getBtnJoin().setDisable(false);
			view.getCreateSpielraumPopUp().hide();
		});
		view.getBtnCreatePlayroom().setOnAction(e ->{
			if (!view.createSpielraumPopUp.isShowing()) 
				view.createSpielraumPopUp.show(view.getStage());
		});
		
		view.getBtnJoin().setOnAction(e ->{
			view.getRoot().setCenter(view.spielraumLayout);
			view.spielraumLayout.setId("rootleft");
			view.getRoot().setBottom(null);
			view.getStage().setTitle("Spielraum");
		});
		
		view.getBtnLeave().setOnAction(e ->{
			view.getRoot().setCenter(view.lobbyLayout);
			view.getRoot().setId("root");
			view.getRoot().setBottom(view.v1);
			view.getStage().setTitle("Lobby");
		});

	
		model.getTokenProperty().addListener( (o, oldValue, newValue) -> {
			this.token = newValue;
		});
		
	}
	
	private void createPlayroom() {
		if(view.getCbTrumpf().isSelected() == true) {
			model.createPlayroom(view.getTfSpielraumName().getText(), "Trumpf");
		}
	}

	public void connect() {
		model.connect(view.getTfIP().getText(), Integer.parseInt(view.getTfPort().getText()));
	}
	
	public void createAccount() {
		model.createAccount(view.getTfNewUsername().getText(), view.getTfNewPassword().getText());
	}

	public void login() {
		model.login(view.getTfUsername().getText(), view.getTfPassword().getText());
	}
}
