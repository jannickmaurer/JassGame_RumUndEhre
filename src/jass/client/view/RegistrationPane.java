package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RegistrationPane extends GridPane {
	private JassClientView view;
	
	Label lblTitelRegistration = new Label();
	Label lblSubtitelRegistration = new Label();
	TextField tfNewUsername = new TextField();
	PasswordField pfNewPassword = new PasswordField();
	Button btnRegistration = new Button();
	Button btnBack = new Button();
	
	public RegistrationPane() {
		
		VBox v1 = new VBox();
		v1.setId("VBox");
		
		lblTitelRegistration.setId("titel");
		lblSubtitelRegistration.setId("subtitel");
		
		HBox h1 = new HBox();
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		h1.setId("HBox");
		h1.getChildren().addAll(btnRegistration, spacer1, btnBack);
		v1.getChildren().addAll(lblTitelRegistration, lblSubtitelRegistration, tfNewUsername, pfNewPassword, h1);
		
		this.add(v1, 0, 0);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
	}
	
}
