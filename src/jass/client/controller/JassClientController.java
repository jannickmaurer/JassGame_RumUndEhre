package jass.client.controller;

import jass.client.model.JassClientModel;
import jass.client.view.JassClientView;

public class JassClientController {
	private JassClientModel model;
	private JassClientView view;

	public JassClientController(JassClientModel model, JassClientView view) {
		this.model = model;
		this.view = view;
		
		view.getBtnRun().setOnAction(event -> connect());
		view.getBtnPing().setOnAction(event -> model.ping());
	}
	
	public void connect() {
		model.connect(view.getTfIP().getText(), Integer.parseInt(view.getTfPort().getText()));
		System.out.println("Connected");
	}

}
