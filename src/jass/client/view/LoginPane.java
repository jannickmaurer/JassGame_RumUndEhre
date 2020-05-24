//Geschrieben von Samuel David

package jass.client.view;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoginPane extends GridPane {
	private JassClientView view;
	
	private Label lblTitelLogin = new Label();
	private Label lblSubtitelLogin = new Label();
	private TextField tfUsername = new TextField();
	private PasswordField pfPassword = new PasswordField();
	private Button btnLogin = new Button();
	private Button btnRegistration = new Button();
	
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
		btnLogin.disableProperty().bind(tfUsername.textProperty().isEmpty() .or(pfPassword.textProperty().isEmpty()));
		v1.getChildren().addAll(lblTitelLogin, lblSubtitelLogin, tfUsername, pfPassword, h1);
		
		this.add(v1, 0, 0);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
	}

	public Label getLblTitelLogin() {
		return lblTitelLogin;
	}

	public void setLblTitelLogin(Label lblTitelLogin) {
		this.lblTitelLogin = lblTitelLogin;
	}

	public Label getLblSubtitelLogin() {
		return lblSubtitelLogin;
	}

	public void setLblSubtitelLogin(Label lblSubtitelLogin) {
		this.lblSubtitelLogin = lblSubtitelLogin;
	}

	public TextField getTfUsername() {
		return tfUsername;
	}

	public void setTfUsername(TextField tfUsername) {
		this.tfUsername = tfUsername;
	}

	public PasswordField getPfPassword() {
		return pfPassword;
	}

	public void setPfPassword(PasswordField pfPassword) {
		this.pfPassword = pfPassword;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}

	public Button getBtnRegistration() {
		return btnRegistration;
	}

	public void setBtnRegistration(Button btnRegistration) {
		this.btnRegistration = btnRegistration;
	}
	
}

