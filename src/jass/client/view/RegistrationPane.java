package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class RegistrationPane extends GridPane {
	private JassClientView view;
	
	Label lblTitelRegistration = new Label();
	Label lblSubtitelRegistration = new Label();
	Label lblUsername = new Label();
	Label lblPassword = new Label();
	TextField tfUsername = new TextField("Pesche");
	TextField tfPassword = new TextField("123456");
	Button btnRegistration = new Button();
	Button btnBack = new Button();
	
	public RegistrationPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		this.add(lblTitelRegistration, 1, 0);
		lblTitelRegistration.setId("titel");
		this.add(lblSubtitelRegistration, 1, 1);
		lblSubtitelRegistration.setId("subtitel");
		this.add(lblUsername, 1, 2);
		this.add(tfUsername, 1, 3);
		this.add(lblPassword, 1, 4);
		this.add(tfPassword, 1, 5);
		
		HBox h1 = new HBox();
		h1.setId("HBox");
		h1.getChildren().addAll(btnRegistration, btnBack);
		
		this.add(h1, 1, 6);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
