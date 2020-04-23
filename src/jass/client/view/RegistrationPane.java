package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegistrationPane extends GridPane {
	private JassClientView view;
	
	Label lblUsername = new Label();
	Label lblPassword = new Label();
	TextField tfUsername = new TextField("Pesche");
	TextField tfPassword = new TextField("123456");
	Button btnRegistration = new Button();
	Button btnBack = new Button();
	
	public RegistrationPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		this.add(lblUsername, 1, 1);
		this.add(tfUsername, 2, 1);
		this.add(lblPassword, 3, 1);
		this.add(tfPassword, 4, 1);
		this.add(btnRegistration, 5, 1);
		this.add(btnBack, 6, 1);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}

