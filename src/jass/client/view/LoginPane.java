package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class LoginPane extends GridPane {
	private JassClientView view;
	
	Label lblTitelLogin = new Label();
	Label lblSubtitelLogin = new Label();
	TextField tfUsername = new TextField();
	TextField tfPassword = new TextField();
	Button btnLogin = new Button();
	Button btnRegistration = new Button();
	
	public LoginPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		this.add(lblTitelLogin, 1, 0);
		lblTitelLogin.setId("titel");
		this.add(lblSubtitelLogin, 1, 1);
		lblSubtitelLogin.setId("subtitel");
		this.add(tfUsername, 1, 3);
		this.add(tfPassword, 1, 5);

		HBox h1 = new HBox();
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		h1.setId("HBox");
		h1.getChildren().addAll(btnLogin, spacer1, btnRegistration);
		
		this.add(h1, 1, 6);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
