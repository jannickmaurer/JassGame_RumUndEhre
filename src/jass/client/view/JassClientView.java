package jass.client.view;

import java.util.Locale;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.commons.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
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
	SpielraumPopupPane spielraumPopupLayout = new SpielraumPopupPane();
	SpielraumPane spielraumLayout = new SpielraumPane();
	
	Popup createSpielraumPopUp = new Popup();
	
	Label lblPort = connectLayout.lblPort;
	Label lblIP = connectLayout.lblIP;
	TextField tfPort = connectLayout.tfPort;
	TextField tfIP = connectLayout.tfIP;
	Button btnRun = connectLayout.btnRun;
	Button btnPing = connectLayout.btnPing;
	Button btnStart = connectLayout.btnStart;
	
	Label lblTitelLogin = loginLayout.lblTitelLogin;
	Label lblSubtitelLogin = loginLayout.lblSubtitelLogin;
	TextField tfUsername = loginLayout.tfUsername;
	TextField tfPassword = loginLayout.tfPassword;
	Button btnLogin = loginLayout.btnLogin;
	Button btnRegistration = loginLayout.btnRegistration;
	
	Label lblTitelRegistration = registrationLayout.lblTitelRegistration;
	Label lblSubtitelRegistration = registrationLayout.lblSubtitelRegistration;
	TextField tfNewUsername = registrationLayout.tfUsername;
	TextField tfNewPassword = registrationLayout.tfPassword;
	Button btnNewRegistration = registrationLayout.btnRegistration;
	Button btnBack = registrationLayout.btnBack;
	
	Button btnProfil = lobbyLayout.btnProfil;
	Button btnConfig = lobbyLayout.btnConfig;
	Button btnJoin = lobbyLayout.btnJoin;
	Button btnCreatePlayroom = lobbyLayout.btnCreatePlayroom;
	Button btnLogout = lobbyLayout.btnLogout;
	
	TextField tfSpielraumName = spielraumPopupLayout.tfSpielraumName;
	RadioButton rbTrumpf = spielraumPopupLayout.rbTrumpf;
	RadioButton rbUndeUfe = spielraumPopupLayout.rbUndeUfe;
	RadioButton rbObeAbe = spielraumPopupLayout.rbObeAbe;
	RadioButton rbSlalom = spielraumPopupLayout.rbSlalom;
	CheckBox cbWyss = spielraumPopupLayout.cbWyss;
	Button btnCreatePlayroomPopup = spielraumPopupLayout.btnCreate;
	
	Button btnLeave = spielraumLayout.btnLeave;

	public JassClientView(Stage primaryStage, JassClientModel model) {
		this.primaryStage = primaryStage;
		this.model = model;
		
		lblPort.setMinWidth(Region.USE_PREF_SIZE);
		lblIP.setMinWidth(Region.USE_PREF_SIZE);
		tfPort.setMinWidth(Region.USE_PREF_SIZE);
		tfPort.setPrefWidth(120);
		tfIP.setMinWidth(Region.USE_PREF_SIZE);
		tfIP.setPrefWidth(120);
		btnRun.setMinWidth(Region.USE_PREF_SIZE);
		btnPing.setMinWidth(Region.USE_PREF_SIZE);
		btnStart.setMinWidth(Region.USE_PREF_SIZE);
		lblTitelLogin.setMinWidth(Region.USE_PREF_SIZE);
		lblSubtitelLogin.setMinWidth(Region.USE_PREF_SIZE);
		tfUsername.setMinWidth(Region.USE_PREF_SIZE);
		tfUsername.setPrefWidth(240);
		tfPassword.setMinWidth(Region.USE_PREF_SIZE);
		tfPassword.setPrefWidth(240);
		btnLogin.setMinWidth(Region.USE_PREF_SIZE);
		btnRegistration.setMinWidth(Region.USE_PREF_SIZE);
		lblTitelRegistration.setMinWidth(Region.USE_PREF_SIZE);
		lblSubtitelRegistration.setMinWidth(Region.USE_PREF_SIZE);
		tfNewUsername.setMinWidth(Region.USE_PREF_SIZE);
		tfNewUsername.setPrefWidth(240);
		tfNewPassword.setMinWidth(Region.USE_PREF_SIZE);
		tfNewPassword.setPrefWidth(240);
		btnNewRegistration.setMinWidth(Region.USE_PREF_SIZE);
		btnBack.setMinWidth(Region.USE_PREF_SIZE);
		btnProfil.setMinWidth(Region.USE_PREF_SIZE);
		btnConfig.setMinWidth(Region.USE_PREF_SIZE);
		btnJoin.setMinWidth(Region.USE_PREF_SIZE);
		btnCreatePlayroom.setMinWidth(Region.USE_PREF_SIZE);
		btnLogout.setMinWidth(Region.USE_PREF_SIZE);
		btnLeave.setMinWidth(Region.USE_PREF_SIZE);
		
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
		
		lobbyLayout.btnConfig.setDisable(true);
		lobbyLayout.btnProfil.setDisable(true);
		
		loginLayout.btnLogin.setOnAction(e ->{
			root.setCenter(lobbyLayout);
			primaryStage.setTitle("Lobby");
		});
		
		lobbyLayout.btnLogout.setOnAction(e ->{
			root.setCenter(loginLayout);
			primaryStage.setTitle("Login");
		});
		
		createSpielraumPopUp.getContent().add(spielraumPopupLayout);
		createSpielraumPopUp.setAutoHide(true);
		
		lobbyLayout.btnCreatePlayroom.setOnAction(e ->{
			if (!createSpielraumPopUp.isShowing()) 
				createSpielraumPopUp.show(primaryStage);
		});
		
		lobbyLayout.btnJoin.setOnAction(e ->{
			root.setCenter(spielraumLayout);
			primaryStage.setTitle("Spielraum");
		});
		
		spielraumLayout.btnLeave.setOnAction(e ->{
			root.setCenter(lobbyLayout);
			primaryStage.setTitle("Lobby");
		});
		
		scene = new Scene(root, 850, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Connect to Server");
	}

	public void start() {
		primaryStage.show();
		primaryStage.setMinWidth(primaryStage.getWidth());
		primaryStage.setMinHeight(primaryStage.getHeight());
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
		loginLayout.lblTitelLogin.setText(t.getString("text.titel.jass"));
		loginLayout.lblSubtitelLogin.setText(t.getString("text.untertitel.login"));
		loginLayout.tfUsername.setPromptText(t.getString("label.username"));
		loginLayout.tfPassword.setPromptText(t.getString("label.password"));
		registrationLayout.lblTitelRegistration.setText(t.getString("text.titel.jass"));
		registrationLayout.lblSubtitelRegistration.setText(t.getString("text.untertitel.registration"));
		registrationLayout.tfUsername.setPromptText(t.getString("label.username"));
		registrationLayout.tfPassword.setPromptText(t.getString("label.password"));
		spielraumPopupLayout.tfSpielraumName.setPromptText(t.getString("label.spielraumname"));
		
	    // Other controls
		connectLayout.btnPing.setText(t.getString("button.ping"));
		connectLayout.btnRun.setText(t.getString("button.run"));
		connectLayout.btnStart.setText(t.getString("button.start"));
		loginLayout.btnLogin.setText(t.getString("button.login"));
		loginLayout.btnRegistration.setText(t.getString("button.registration"));
		registrationLayout.btnRegistration.setText(t.getString("button.registration"));
		registrationLayout.btnBack.setText(t.getString("button.back"));
		lobbyLayout.btnProfil.setText(t.getString("button.profil"));
		lobbyLayout.btnConfig.setText(t.getString("button.config"));
		lobbyLayout.btnJoin.setText(t.getString("button.join"));
		lobbyLayout.btnCreatePlayroom.setText(t.getString("button.createplayroom"));
		lobbyLayout.btnLogout.setText(t.getString("button.logout"));
		spielraumPopupLayout.rbTrumpf.setText(t.getString("radiobutton.trumpf"));
		spielraumPopupLayout.rbUndeUfe.setText(t.getString("radiobutton.undeufe"));
		spielraumPopupLayout.rbObeAbe.setText(t.getString("radiobutton.obeabe"));
		spielraumPopupLayout.rbSlalom.setText(t.getString("radiobutton.slalom"));
		spielraumPopupLayout.cbWyss.setText(t.getString("checkbox.wyss"));
		spielraumPopupLayout.btnCreate.setText(t.getString("button.createplayroom"));
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
	
	public Button getBtnProfil() {
		return btnProfil;
	}

	public void setBtnProfil(Button btnProfil) {
		this.btnProfil = btnProfil;
	}
	
	public Button getBtnConfig() {
		return btnConfig;
	}

	public void setBtnConfig(Button btnConfig) {
		this.btnConfig = btnConfig;
	}
	
	public TextField getTfSpielraumName() {
		return tfSpielraumName;
	}

	public void setTfSpielraumName(TextField tfSpielraumName) {
		this.tfSpielraumName = tfSpielraumName;
	}
	
	public RadioButton getRbTrumpf() {
		return rbTrumpf;
	}

	public void setRbTrumpf(RadioButton rbTrumpf) {
		this.rbTrumpf = rbTrumpf;
	}
	
	public RadioButton getRbUndeUfe() {
		return rbUndeUfe;
	}

	public void setRbUndeUfe(RadioButton rbUndeUfe) {
		this.rbUndeUfe = rbUndeUfe;
	}
	
	public RadioButton getRbObeAbe() {
		return rbObeAbe;
	}

	public void setRbObeAbe(RadioButton rbObeAbe) {
		this.rbObeAbe = rbObeAbe;
	}
	
	public RadioButton getRbSlalom() {
		return rbSlalom;
	}

	public void setRbSlalom(RadioButton rbSlalom) {
		this.rbSlalom = rbSlalom;
	}
	
	public CheckBox getCbWyss() {
		return cbWyss;
	}

	public void setCbWyss(CheckBox cbWyss) {
		this.cbWyss = cbWyss;
	}
	
	public Button getBtnCreatePlayroomPopup() {
		return btnCreatePlayroomPopup;
	}

	public void setBtnCreatePlayroomPopup(Button btnCreatePlayroomPopup) {
		this.btnCreatePlayroomPopup = btnCreatePlayroomPopup;
	}
	
	public Button getBtnLeave() {
		return btnLeave;
	}

	public void setBtnLeave(Button btnLeave) {
		this.btnLeave = btnLeave;
	}

}

