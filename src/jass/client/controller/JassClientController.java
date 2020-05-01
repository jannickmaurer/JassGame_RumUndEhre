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
		view.getBtnNewRegistration().setOnAction(event -> createAccount());
		view.getBtnLogin().setOnAction(event -> login());
		view.getBtnCreatePlayroomPopup().setOnAction(e -> {
		
			String name = view.getTfSpielraumName().getText();
			view.getTfSpielraumName().setText("");
			model.addNewElement(name);
			view.getCreateSpielraumPopUp().hide();
   
		});
	
		model.getTokenProperty().addListener( (o, oldValue, newValue) -> {
			this.token = newValue;
		});
		
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
