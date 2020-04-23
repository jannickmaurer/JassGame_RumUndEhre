package jass.client.view;

import java.util.Locale;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.commons.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JassClientView {
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	
	protected Stage primaryStage;
	private JassClientModel model;
	
	Menu menuFileLanguage = new Menu();
	
	Scene scene, connectScene, lobbyScene, loginScene, registrationScene, spielraumScene;
	ConnectPane connectLayout = new ConnectPane();
	LobbyPane lobbyLayout = new LobbyPane();
	LoginPane loginLayout = new LoginPane();
	RegistrationPane registrationLayout = new RegistrationPane();
	SpielraumPane spielraumLayout = new SpielraumPane();
	
	TextField tfPort = connectLayout.tfPort;
	TextField tfIP = connectLayout.tfIP;
	Button btnRun = connectLayout.btnRun;
	Button btnPing = connectLayout.btnPing;
	Button btnStart = connectLayout.btnStart;
	
	TextField tfUsername = loginLayout.tfUsername;
	TextField tfPassword = loginLayout.tfPassword;
	Button btnLogin = loginLayout.btnLogin;
	Button btnRegistration = loginLayout.btnRegistration;
	
	TextField tfNewUsername = registrationLayout.tfUsername;
	TextField tfNewPassword = registrationLayout.tfPassword;
	Button btnNewRegistration = registrationLayout.btnRegistration;
	Button btnBack = registrationLayout.btnBack;
	
	Button btnJoin = lobbyLayout.btnJoin;
	Button btnCreatePlayroom = lobbyLayout.btnCreatePlayroom;
	Button btnLogout = lobbyLayout.btnLogout;
	
	Button btnLeave = spielraumLayout.btnLeave;

	public JassClientView(Stage primaryStage, JassClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		BorderPane root = new BorderPane();
		root.setId("root");
		
		connectLayout.btnStart.setOnAction(e ->{
			root.setCenter(loginLayout);
			primaryStage.setTitle("Login");
		});
		
		for (Locale locale : sl.getLocales()) {
            MenuItem language = new MenuItem(locale.getLanguage());
            menuFileLanguage.getItems().add(language);
            language.setOnAction( event -> {
    				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
                 sl.setTranslator(new Translator(locale.getLanguage()));
                 updateTexts();
             });
        }
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuFileLanguage);
		root.setTop(menuBar);
		root.setCenter(connectLayout);
		
		loginLayout.btnRegistration.setOnAction(e ->{
			root.setCenter(registrationLayout);
			primaryStage.setTitle("Registration");
		});
		
		registrationLayout.btnRegistration.setDisable(true);
		
		registrationLayout.btnBack.setOnAction(e ->{
			root.setCenter(loginLayout);
			primaryStage.setTitle("Login");
		});
		
		registrationLayout.btnRegistration.setOnAction(e ->{
			root.setCenter(loginLayout);
			primaryStage.setTitle("Login");
		});
		
		lobbyLayout.btnCreatePlayroom.setDisable(true);
		
		loginLayout.btnLogin.setOnAction(e ->{
			root.setCenter(lobbyLayout);
			primaryStage.setTitle("Lobby");
		});
		
		lobbyLayout.btnLogout.setOnAction(e ->{
			root.setCenter(loginLayout);
			primaryStage.setTitle("Login");
		});
		
		lobbyLayout.btnJoin.setOnAction(e ->{
			root.setCenter(spielraumLayout);
			primaryStage.setTitle("Spielraum");
		});
		
		spielraumLayout.btnLeave.setOnAction(e ->{
			root.setCenter(lobbyLayout);
			primaryStage.setTitle("Lobby");
		});
		
		scene = new Scene(root, 1500, 900);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Connect to Server");
	}

	public void start() {
		primaryStage.show();
		updateTexts();
	}
	
	public void stop() {
		primaryStage.hide();
	}
	
	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		this.menuFileLanguage.setText(t.getString("program.menu.file.language"));
		
	    // Text
		connectLayout.lblIP.setText(t.getString("label.IPAdress"));
		connectLayout.lblPort.setText(t.getString("label.port"));
		loginLayout.lblUsername.setText(t.getString("label.username"));
		loginLayout.lblPassword.setText(t.getString("label.password"));
		registrationLayout.lblUsername.setText(t.getString("label.username"));
		registrationLayout.lblPassword.setText(t.getString("label.password"));
		
	    // Other controls
		connectLayout.btnPing.setText(t.getString("button.ping"));
		connectLayout.btnRun.setText(t.getString("button.run"));
		connectLayout.btnStart.setText(t.getString("button.start"));
		loginLayout.btnLogin.setText(t.getString("button.login"));
		loginLayout.btnRegistration.setText(t.getString("button.registration"));
		registrationLayout.btnRegistration.setText(t.getString("button.registration"));
		registrationLayout.btnBack.setText(t.getString("button.back"));
		lobbyLayout.btnJoin.setText(t.getString("button.join"));
		lobbyLayout.btnCreatePlayroom.setText(t.getString("button.createplayroom"));
		lobbyLayout.btnLogout.setText(t.getString("button.logout"));
		spielraumLayout.btnLeave.setText(t.getString("button.leave"));
    }
	
	public Stage getStage() {
	    return primaryStage;
	}
	
	public TextField getTfPort() {
		return tfPort;
	}

	public void setTfPort(TextField tfPort) {
		this.tfPort = tfPort;
	}

	public TextField getTfIP() {
		return tfIP;
	}

	public void setTfIP(TextField tfIP) {
		this.tfIP = tfIP;
	}

	public Button getBtnRun() {
		return btnRun;
	}
	
	public void setBtnRun(Button btnRun) {
		this.btnRun = btnRun;
	}

	public Button getBtnPing() {
		return btnPing;
	}

	public void setBtnPing(Button btnPing) {
		this.btnPing = btnPing;
	}
	
	public Button getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(Button btnStart) {
		this.btnStart = btnStart;
	}
	
	public TextField getTfUsername() {
		return tfUsername;
	}

	public void setTfUsername(TextField tfUsername) {
		this.tfUsername = tfUsername;
	}
	
	public TextField getTfPassword() {
		return tfPassword;
	}

	public void setTfPassword(TextField tfPassword) {
		this.tfPassword = tfPassword;
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
	
	public TextField getTfNewUsername() {
		return tfNewUsername;
	}

	public void setTfNewUsername(TextField tfNewUsername) {
		this.tfNewUsername = tfNewUsername;
	}
	
	public TextField getTfNewPassword() {
		return tfNewPassword;
	}

	public void setTfNewPassword(TextField tfNewPassword) {
		this.tfNewPassword = tfNewPassword;
	}
	
	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
	public Button getBtnNewRegistration() {
		return btnNewRegistration;
	}

	public void setBtnNewRegistration(Button btnNewRegistration) {
		this.btnNewRegistration = btnNewRegistration;
	}
	
	public Button getBtnJoin() {
		return btnJoin;
	}

	public void setBtnJoin(Button btnJoin) {
		this.btnJoin = btnJoin;
	}
	
	public Button getBtnCreatePlayroom() {
		return btnCreatePlayroom;
	}

	public void setBtnCreatePlayroom(Button btnCreatePlayroom) {
		this.btnCreatePlayroom = btnCreatePlayroom;
	}
	
	public Button getBtnLogout() {
		return btnLogout;
	}

	public void setBtnLogout(Button btnLogout) {
		this.btnLogout = btnLogout;
	}
	
	public Button getBtnLeave() {
		return btnLeave;
	}

	public void setBtnLeave(Button btnLeave) {
		this.btnLeave = btnLeave;
	}

}

