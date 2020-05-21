package jass.client.view;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoginPane extends GridPane {
	private JassClientView view;
	
	Label lblTitelLogin = new Label();
	Label lblSubtitelLogin = new Label();
	TextField tfUsername = new TextField();
	TextField tfPassword = new TextField();
	Button btnLogin = new Button();
	Button btnRegistration = new Button();
	
	public LoginPane() {
		
		VBox v1 = new VBox();
		v1.setId("VBox");
		
		lblTitelLogin.setId("titel");
		lblSubtitelLogin.setId("subtitel");


		HBox h1 = new HBox();
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		h1.setId("HBox");
		h1.getChildren().addAll(btnLogin, spacer1, btnRegistration);		
		btnLogin.disableProperty().bind(tfUsername.textProperty().isEmpty() .or(tfPassword.textProperty().isEmpty()));
		v1.getChildren().addAll(lblTitelLogin, lblSubtitelLogin, tfUsername, tfPassword, h1);
		
		this.add(v1, 0, 0);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);

	}
}

