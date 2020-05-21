package jass.client.view;

import java.util.Locale;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.commons.Translator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class JassClientView {
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	
	protected Stage primaryStage;
	private JassClientModel model;
	public BorderPane root;
	
	Menu menuFileLanguage = new Menu();
	
	Scene scene, connectScene, lobbyScene, loginScene, registrationScene, spielraumScene;
	ConnectPane connectLayout = new ConnectPane();
	public LoginPane loginLayout = new LoginPane();
	public RegistrationPane registrationLayout = new RegistrationPane();
	public SpielraumPopupPane spielraumPopupLayout = new SpielraumPopupPane();
	public SpielraumPane spielraumLayout = new SpielraumPane();
	public StartGamePopupPane startGamePopupLayout = new StartGamePopupPane();
	public ProfilPopUpPane profilPopupLayout = new ProfilPopUpPane();
	public ErrorPopupPane errorPopupLayout = new ErrorPopupPane();
	public SiegerPopupPane siegerPopupLayout = new SiegerPopupPane();
	public TrumpfPopupPane trumpfPopupLayout = new TrumpfPopupPane();
	public WyssPopupPane wyssPopupLayout = new WyssPopupPane();
	public OtherPlayerPane otherPlayerLayout = new OtherPlayerPane();
	
	public Popup profilPopUp = new Popup();
	public Popup createSpielraumPopUp = new Popup();
	public Popup startGamePopUp = new Popup();
	public Popup errorPopUp = new Popup();
	public Popup siegerPopUp = new Popup();
	public Popup trumpfPopUp = new Popup();
	public Popup wyssPopUp = new Popup();
	
	Label lblPort = connectLayout.lblPort;
	Label lblIP = connectLayout.lblIP;
	TextField tfPort = connectLayout.tfPort;
	TextField tfIP = connectLayout.tfIP;
	Button btnRun = connectLayout.btnRun;
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
	
	Button btnProfil = new Button();
	Button btnConfig = new Button();
	Button btnJoin = new Button();
	Button btnCreatePlayroom = new Button();
	Button btnDeletePlayroom = new Button();
	Button btnLogout = new Button();
	ListView<String> listView;
	
	public VBox v1 = new VBox();
	public HBox h1 = new HBox();
	
	TextField tfSpielraumName = spielraumPopupLayout.tfSpielraumName;
	CheckBox cbTrumpf = spielraumPopupLayout.cbTrumpf;
	CheckBox cbUndeUfe = spielraumPopupLayout.cbUndeUfe;
	CheckBox cbObeAbe = spielraumPopupLayout.cbObeAbe;
	CheckBox cbSlalom = spielraumPopupLayout.cbSlalom;
	CheckBox cbWyss = spielraumPopupLayout.cbWyss;
	Button btnDeleteAccount = profilPopupLayout.btnDeleteAccount;
	Button btnBackProfil = profilPopupLayout.btnBack;
	Button btnCreatePlayroomPopup = spielraumPopupLayout.btnCreate;
	Button btnBackPlayroom = spielraumPopupLayout.btnBack;
	
	Label lblError = errorPopupLayout.lblError;
	Button btnBackError = errorPopupLayout.btnBack;
	
	Label lblWinner = siegerPopupLayout.lblWinner;
	Button btnBackSieger = siegerPopupLayout.btnBack;
	
	Label lblPointsOtherPlayer = otherPlayerLayout.lblPoints;
	
	Label lblPlayer = spielraumLayout.lblPlayer;
	Label lblPlayer1 = spielraumLayout.lblPlayer1;
	Label lblPlayer2 = spielraumLayout.lblPlayer2;
	Label lblPlayer3 = spielraumLayout.lblPlayer3;
	Label lblPlayer4 = spielraumLayout.lblPlayer4;
	Label lblPoints = spielraumLayout.lblPoints;
	Label lblPoints1 = spielraumLayout.lblPoints1;
	Label lblPoints2 = spielraumLayout.lblPoints2;
	Label lblPoints3 = spielraumLayout.lblPoints3;
	Label lblPoints4 = spielraumLayout.lblPoints4;
	Label lblChat = spielraumLayout.lblChat;
	Label lblWait = spielraumLayout.lblWait;
	TextArea txtMessages = spielraumLayout.txtMessages;
	TextField tfMessage = spielraumLayout.tfMessage;
	Button btnSend = spielraumLayout.btnSend;
	ScrollPane scrollPane = spielraumLayout.scrollPane;
	Circle crcP1 = spielraumLayout.crcP1;
	Circle crcP2 = spielraumLayout.crcP2;
	Circle crcP3 = spielraumLayout.crcP3;
	Circle crcP4 = spielraumLayout.crcP4;
	Rectangle card1 = spielraumLayout.card1;
    Rectangle card2 = spielraumLayout.card2;
    Rectangle card3 = spielraumLayout.card3;
    Rectangle card4 = spielraumLayout.card4;
    Rectangle card5 = spielraumLayout.card5;
    Rectangle card6 = spielraumLayout.card6;
    Rectangle card7 = spielraumLayout.card7;
    Rectangle card8 = spielraumLayout.card8;
    Rectangle card9 = spielraumLayout.card9;
    Rectangle cardP1 = spielraumLayout.cardP1;
	Button btnLeave = spielraumLayout.btnLeave;
	Button btnStartGame = spielraumLayout.btnStartGame;
	
	Label lblPointsLimit = startGamePopupLayout.lblPointsLimit;
	Button btnBackStartGame = startGamePopupLayout.btnBack;
	Button btnStartGamePopUp = startGamePopupLayout.btnStartGamePopUp;
	TextField tfPoints = startGamePopupLayout.tfPoints;
	
	Label lblChooseTrumpf = trumpfPopupLayout.lblChooseTrumpf;
	Button btnHearts = trumpfPopupLayout.btnHearts;
	Button btnDiamonds = trumpfPopupLayout.btnDiamonds;
	Button btnSpades = trumpfPopupLayout.btnSpades;
	Button btnClubs = trumpfPopupLayout.btnClubs;
	Button btnPush = trumpfPopupLayout.btnPush;
	
	Label lblWyss = wyssPopupLayout.lblWyss;
	Button btnWyss = wyssPopupLayout.btnWyss;
	Button btnNoWyss = wyssPopupLayout.btnNoWyss;
	
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
		btnRun.setPrefWidth(100);
		btnStart.setMinWidth(Region.USE_PREF_SIZE);
		btnStart.setPrefWidth(100);
		lblTitelLogin.setMinWidth(Region.USE_PREF_SIZE);
		lblSubtitelLogin.setMinWidth(Region.USE_PREF_SIZE);
		tfUsername.setMinWidth(Region.USE_PREF_SIZE);
		tfUsername.setPrefWidth(240);
		tfPassword.setMinWidth(Region.USE_PREF_SIZE);
		tfPassword.setPrefWidth(240);
		btnLogin.setMinWidth(Region.USE_PREF_SIZE);
		btnLogin.setPrefWidth(100);
		btnRegistration.setMinWidth(Region.USE_PREF_SIZE);
		btnRegistration.setPrefWidth(100);
		lblTitelRegistration.setMinWidth(Region.USE_PREF_SIZE);
		lblSubtitelRegistration.setMinWidth(Region.USE_PREF_SIZE);
		tfNewUsername.setMinWidth(Region.USE_PREF_SIZE);
		tfNewUsername.setPrefWidth(240);
		tfNewPassword.setMinWidth(Region.USE_PREF_SIZE);
		tfNewPassword.setPrefWidth(240);
		btnNewRegistration.setMinWidth(Region.USE_PREF_SIZE);
		btnNewRegistration.setPrefWidth(100);
		btnBack.setMinWidth(Region.USE_PREF_SIZE);
		btnBack.setPrefWidth(100);
		btnProfil.setMinWidth(Region.USE_PREF_SIZE);
		btnProfil.setPrefWidth(100);
		btnConfig.setMinWidth(Region.USE_PREF_SIZE);
		btnConfig.setPrefWidth(100);
		btnJoin.setMinWidth(Region.USE_PREF_SIZE);
		btnJoin.setPrefWidth(100);
		btnCreatePlayroom.setMinWidth(Region.USE_PREF_SIZE);
		btnCreatePlayroom.setPrefWidth(140);
		btnDeletePlayroom.setMinWidth(Region.USE_PREF_SIZE);
		btnDeletePlayroom.setPrefWidth(140);
		btnLogout.setMinWidth(Region.USE_PREF_SIZE);
		btnLogout.setPrefWidth(100);
		tfSpielraumName.setMinWidth(Region.USE_PREF_SIZE);
		tfSpielraumName.setPrefWidth(240);
		lblPlayer.setMinWidth(Region.USE_PREF_SIZE);
		lblPlayer1.setMinWidth(Region.USE_PREF_SIZE);
		lblPlayer2.setMinWidth(Region.USE_PREF_SIZE);
		lblPlayer3.setMinWidth(Region.USE_PREF_SIZE);
		lblPlayer4.setMinWidth(Region.USE_PREF_SIZE);
		lblPoints.setMinWidth(Region.USE_PREF_SIZE);
		lblPoints1.setMinWidth(Region.USE_PREF_SIZE);
		lblPoints2.setMinWidth(Region.USE_PREF_SIZE);
		lblPoints3.setMinWidth(Region.USE_PREF_SIZE);
		lblPoints4.setMinWidth(Region.USE_PREF_SIZE);
		lblChat.setMinWidth(Region.USE_PREF_SIZE);
		lblWait.setMinWidth(Region.USE_PREF_SIZE);
		btnDeleteAccount.setMinWidth(Region.USE_PREF_SIZE);
		btnDeleteAccount.setPrefWidth(140);
		btnCreatePlayroomPopup.setMinWidth(Region.USE_PREF_SIZE);
		btnCreatePlayroomPopup.setPrefWidth(140);
		btnBackPlayroom.setMinWidth(Region.USE_PREF_SIZE);
		btnBackPlayroom.setPrefWidth(140);
		tfMessage.setMinWidth(Region.USE_PREF_SIZE);
		tfMessage.setPrefWidth(250);
		txtMessages.setMinWidth(Region.USE_PREF_SIZE);
		txtMessages.setPrefWidth(250);
		btnSend.setMinWidth(Region.USE_PREF_SIZE);
		btnSend.setPrefWidth(250);
		btnLeave.setMinWidth(Region.USE_PREF_SIZE);
		btnLeave.setPrefWidth(100);
		btnStartGame.setMinWidth(Region.USE_PREF_SIZE);
		btnStartGame.setPrefWidth(100);
		btnStartGamePopUp.setMinWidth(Region.USE_PREF_SIZE);
		btnStartGamePopUp.setPrefWidth(140);
		btnBackError.setMinWidth(Region.USE_PREF_SIZE);
		btnBackError.setPrefWidth(140);
		btnBackProfil.setMinWidth(Region.USE_PREF_SIZE);
		btnBackProfil.setPrefWidth(140);
		btnBackSieger.setMinWidth(Region.USE_PREF_SIZE);
		btnBackSieger.setPrefWidth(140);
		btnBackStartGame.setMinWidth(Region.USE_PREF_SIZE);
		btnBackStartGame.setPrefWidth(140);
		btnHearts.setMinWidth(Region.USE_PREF_SIZE);
		btnHearts.setPrefWidth(140);
		btnDiamonds.setMinWidth(Region.USE_PREF_SIZE);
		btnDiamonds.setPrefWidth(140);
		btnSpades.setMinWidth(Region.USE_PREF_SIZE);
		btnSpades.setPrefWidth(140);
		btnClubs.setMinWidth(Region.USE_PREF_SIZE);
		btnClubs.setPrefWidth(140);
		btnPush.setMinWidth(Region.USE_PREF_SIZE);
		btnPush.setPrefWidth(140);
		tfPoints.setMinWidth(Region.USE_PREF_SIZE);
		tfPoints.setPrefWidth(120);
		btnWyss.setMinWidth(Region.USE_PREF_SIZE);
		btnWyss.setPrefWidth(140);
		btnNoWyss.setMinWidth(Region.USE_PREF_SIZE);
		btnNoWyss.setPrefWidth(140);
	
		root = new BorderPane();
		root.setId("root");
		
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
		
		registrationLayout.btnRegistration.setDisable(false); // changed from Jannick
		
	
		this.btnConfig.setDisable(true);
		this.btnJoin.setDisable(false);
		this.btnDeletePlayroom.setDisable(true);
		v1.setId("VBox");
		
		h1.setId("HBoxTop");
		h1.getChildren().addAll(btnProfil, btnConfig, btnLogout);
		
		HBox h2 = new HBox();
		h2.setId("HBoxRight");
		h2.getChildren().addAll(btnJoin, btnCreatePlayroom, btnDeletePlayroom);
		listView = new ListView<>(model.getPlayrooms());
		v1.getChildren().addAll(h1, listView, h2);
		listView.setMinHeight(Region.USE_PREF_SIZE);
		listView.setPrefHeight(440);
		
		siegerPopUp.getContent().add(siegerPopupLayout);
		siegerPopUp.setAutoHide(true);
		
		trumpfPopUp.getContent().add(trumpfPopupLayout);
		trumpfPopUp.setAutoHide(false);
		
		wyssPopUp.getContent().add(wyssPopupLayout);
		wyssPopUp.setAutoHide(false);
		
		errorPopUp.getContent().add(errorPopupLayout);
		errorPopUp.setAutoHide(true);
		
		profilPopUp.getContent().add(profilPopupLayout);
		profilPopUp.setAutoHide(true);
		
		createSpielraumPopUp.getContent().add(spielraumPopupLayout);
		spielraumPopupLayout.btnCreate.disableProperty().bind(spielraumPopupLayout.tfSpielraumName.textProperty().isEmpty());
		createSpielraumPopUp.setAutoHide(true);
		
		startGamePopUp.getContent().add(startGamePopupLayout);
		startGamePopUp.setAutoHide(true);
		
		txtMessages.setEditable(false);
		
		v1.setVgrow(listView, Priority.ALWAYS);
		
		scene = new Scene(root, 950, 635);
		scene.getStylesheets().add(
                getClass().getResource("Client.css").toExternalForm());
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
		Platform.exit();
		System.exit(0);
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
		spielraumLayout.lblPlayer.setText(t.getString("label.player"));
		otherPlayerLayout.lblPoints.setText(t.getString("label.points"));
		spielraumLayout.lblPoints.setText(t.getString("label.points"));
		spielraumLayout.lblChat.setText(t.getString("label.chat"));
		spielraumLayout.lblWait.setText(t.getString("label.wait"));
		startGamePopupLayout.lblPointsLimit.setText(t.getString("label.limit"));
		errorPopupLayout.lblError.setText(t.getString("label.error"));
		siegerPopupLayout.lblWinner.setText(t.getString("label.winner"));
		trumpfPopupLayout.lblChooseTrumpf.setText(t.getString("label.choosetrumpf"));
		wyssPopupLayout.lblWyss.setText(t.getString("label.wyss"));
		
	    // Other controls
		connectLayout.btnRun.setText(t.getString("button.run"));
		connectLayout.btnStart.setText(t.getString("button.start"));
		loginLayout.btnLogin.setText(t.getString("button.login"));
		loginLayout.btnRegistration.setText(t.getString("button.registration"));
		registrationLayout.btnRegistration.setText(t.getString("button.registration"));
		registrationLayout.btnBack.setText(t.getString("button.back"));
		profilPopupLayout.btnDeleteAccount.setText(t.getString("button.deleteaccount"));
		this.btnProfil.setText(t.getString("button.profil"));
		this.btnConfig.setText(t.getString("button.config"));
		this.btnJoin.setText(t.getString("button.join"));
		this.btnCreatePlayroom.setText(t.getString("button.createplayroom"));
		this.btnDeletePlayroom.setText(t.getString("button.deleteplayroom"));
		this.btnLogout.setText(t.getString("button.logout"));
		spielraumPopupLayout.cbTrumpf.setText(t.getString("checkbox.trumpf"));
		spielraumPopupLayout.cbUndeUfe.setText(t.getString("checkbox.undeufe"));
		spielraumPopupLayout.cbObeAbe.setText(t.getString("checkbox.obeabe"));
		spielraumPopupLayout.cbSlalom.setText(t.getString("checkbox.slalom"));
		spielraumPopupLayout.cbWyss.setText(t.getString("checkbox.wyss"));
		spielraumPopupLayout.btnCreate.setText(t.getString("button.createplayroom"));
		spielraumPopupLayout.btnBack.setText(t.getString("button.back"));
		spielraumLayout.btnSend.setText(t.getString("button.send"));
		spielraumLayout.btnLeave.setText(t.getString("button.leave"));
		spielraumLayout.btnStartGame.setText(t.getString("button.start"));
		startGamePopupLayout.btnStartGamePopUp.setText(t.getString("button.start"));
		startGamePopupLayout.btnBack.setText(t.getString("button.back"));
		wyssPopupLayout.btnWyss.setText(t.getString("button.wyss"));
		wyssPopupLayout.btnNoWyss.setText(t.getString("button.nowyss"));
		profilPopupLayout.btnBack.setText(t.getString("button.back"));
		errorPopupLayout.btnBack.setText(t.getString("button.back"));
		siegerPopupLayout.btnBack.setText(t.getString("button.back"));
		trumpfPopupLayout.btnHearts.setText(t.getString("button.hearts"));
		trumpfPopupLayout.btnDiamonds.setText(t.getString("button.diamonds"));
		trumpfPopupLayout.btnSpades.setText(t.getString("button.spades"));
		trumpfPopupLayout.btnClubs.setText(t.getString("button.clubs"));
		trumpfPopupLayout.btnPush.setText(t.getString("button.push"));
    }
	
	public Stage getStage() {
	    return primaryStage;
	}
	
	public BorderPane getRoot() {
		return root;
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
	
	public Button getBtnDeletePlayroom() {
		return btnDeletePlayroom;
	}

	public void setBtnDeletePlayroom(Button btnDeletePlayroom) {
		this.btnDeletePlayroom = btnDeletePlayroom;
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
	
	public CheckBox getCbTrumpf() {
		return cbTrumpf;
	}

	public void setCbTrumpf(CheckBox cbTrumpf) {
		this.cbTrumpf = cbTrumpf;
	}
	
	public CheckBox getCbUndeUfe() {
		return cbUndeUfe;
	}

	public void setCbUndeUfe(CheckBox cbUndeUfe) {
		this.cbUndeUfe = cbUndeUfe;
	}
	
	public CheckBox getCbObeAbe() {
		return cbObeAbe;
	}

	public void setCbObeAbe(CheckBox cbObeAbe) {
		this.cbObeAbe = cbObeAbe;
	}
	
	public CheckBox getCbSlalom() {
		return cbSlalom;
	}

	public void setCbSlalom(CheckBox rbSlalom) {
		this.cbSlalom = rbSlalom;
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
	
	public Button getBtnBackPlayroom() {
		return btnBackPlayroom;
	}

	public void setBtnBackPlayroom(Button btnBackPlayroom) {
		this.btnBackPlayroom = btnBackPlayroom;
	}
	
	public Button getBtnLeave() {
		return btnLeave;
	}

	public void setBtnLeave(Button btnLeave) {
		this.btnLeave = btnLeave;
	}
	
	public Button getBtnStartGame() {
		return btnStartGame;
	}

	public void setBtnStartGame(Button btnStartGame) {
		this.btnStartGame = btnStartGame;
	}
	
	public Popup getCreateSpielraumPopUp() {
		return createSpielraumPopUp;
	}

	public void setCreateSpielraumPopUp(Popup createSpielraumPopUp) {
		this.createSpielraumPopUp = createSpielraumPopUp;
	}
	
	public Label getLblWait() {
		return lblWait;
	}

	public void setLblWait(Label lblWait) {
		this.lblWait = lblWait;
	}
	
	public Label getLblPlayer() {
		return lblPlayer;
	}

	public void setLblPlayer(Label lblPlayer) {
		this.lblPlayer = lblPlayer;
	}
	
	public Label getLblPlayer1() {
		return lblPlayer1;
	}

	public void setLblPlayer1(Label lblPlayer1) {
		this.lblPlayer1 = lblPlayer1;
	}
	
	public Label getLblPlayer2() {
		return lblPlayer2;
	}

	public void setLblPlayer2(Label lblPlayer2) {
		this.lblPlayer2 = lblPlayer2;
	}
	
	public Label getLblPlayer3() {
		return lblPlayer3;
	}

	public void setLblPlayer3(Label lblPlayer3) {
		this.lblPlayer3 = lblPlayer3;
	}
	
	public Label getLblPlayer4() {
		return lblPlayer4;
	}

	public void setLblPlayer4(Label lblPlayer4) {
		this.lblPlayer4 = lblPlayer4;
	}
	
	public Label getLblChat() {
		return lblChat;
	}

	public void setLblChat(Label lblChat) {
		this.lblChat = lblChat;
	}
	
	public TextArea getTxtMessages() {
		return txtMessages;
	}

	public void setTxtMessages(TextArea txtMessages) {
		this.txtMessages = txtMessages;
	}
	
	public TextField getTfMessage() {
		return tfMessage;
	}

	public void setTfMessage(TextField tfMessage) {
		this.tfMessage = tfMessage;
	}
	
	public Button getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(Button btnSend) {
		this.btnSend = btnSend;
	}
	
	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public TextField getTfPoints() {
		return tfPoints;
	}

	public void setTfPoints(TextField tfPoints) {
		this.tfPoints = tfPoints;
	}
	
	public Button getBtnBackError() {
		return btnBackError;
	}

	public void setBtnBackError(Button btnBackError) {
		this.btnBackError = btnBackError;
	}
	
	public Button getBtnBackProfil() {
		return btnBackProfil;
	}

	public void setBtnBackProfil(Button btnBackProfil) {
		this.btnBackProfil = btnBackProfil;
	}
	
	public Button getBtnDeleteAccount() {
		return btnDeleteAccount;
	}

	public void setBtnDeleteAccount(Button btnDeleteAccount) {
		this.btnDeleteAccount = btnDeleteAccount;
	}
	
	public Button getBtnBackSieger() {
		return btnBackSieger;
	}

	public void setBtnBackSieger(Button btnBackSieger) {
		this.btnBackSieger = btnBackSieger;
	}
	
	public Label getLblWinner() {
		return lblWinner;
	}

	public void setLblWinner(Label lblWinner) {
		this.lblWinner = lblWinner;
	}
	
	public Button getBtnBackStartGame() {
		return btnBackStartGame;
	}

	public void setBtnBackStartGame(Button btnBackStartGame) {
		this.btnBackStartGame = btnBackStartGame;
	}
	
	public Button getBtnStartGamePopUp() {
		return btnStartGamePopUp;
	}

	public void setBtnStartGamePopup(Button btnStartGamePopUp) {
		this.btnStartGamePopUp = btnStartGamePopUp;
	}
	
	public Button getBtnHearts() {
		return btnHearts;
	}

	public void setBtnHearts(Button btnHearts) {
		this.btnHearts = btnHearts;
	}
	
	public Button getBtnDiamonds() {
		return btnDiamonds;
	}

	public void setBtnDiamonds(Button btnDiamonds) {
		this.btnDiamonds = btnDiamonds;
	}
	
	public Button getBtnSpades() {
		return btnSpades;
	}

	public void setBtnSpades(Button btnSpades) {
		this.btnSpades = btnSpades;
	}
	
	public Button getBtnClubs() {
		return btnClubs;
	}

	public void setBtnClubs(Button btnClubs) {
		this.btnClubs = btnClubs;
	}
	
	public Button getBtnPush() {
		return btnPush;
	}

	public void setBtnPush(Button btnPush) {
		this.btnPush = btnPush;
	}
	
	public Button getBtnWyss() {
		return btnWyss;
	}

	public void setBtnWyss(Button btnWyss) {
		this.btnWyss = btnWyss;
	}
	
	public Button getBtnNoWyss() {
		return btnNoWyss;
	}

	public void setBtnNoWyss(Button btnNoWyss) {
		this.btnNoWyss = btnNoWyss;
	}
	
	public Rectangle getCard1() {
		return card1;
	}

	public void setCard1(Rectangle card1) {
		this.card1 = card1;
	}
	
	public Rectangle getCard2() {
		return card2;
	}

	public void setCard2(Rectangle card2) {
		this.card2 = card2;
	}
	
	public Rectangle getCard3() {
		return card3;
	}

	public void setCard3(Rectangle card3) {
		this.card3 = card3;
	}
	
	public Rectangle getCard4() {
		return card4;
	}

	public void setCard4(Rectangle card4) {
		this.card4 = card4;
	}
	
	public Rectangle getCard5() {
		return card5;
	}

	public void setCard5(Rectangle card5) {
		this.card5 = card5;
	}
	
	public Rectangle getCard6() {
		return card6;
	}

	public void setCard6(Rectangle card6) {
		this.card6 = card6;
	}
	
	public Rectangle getCard7() {
		return card7;
	}

	public void setCard7(Rectangle card7) {
		this.card7 = card7;
	}
	
	public Rectangle getCard8() {
		return card8;
	}

	public void setCard8(Rectangle card8) {
		this.card8 = card8;
	}
	
	public Rectangle getCard9() {
		return card9;
	}

	public void setCard9(Rectangle card9) {
		this.card9 = card9;
	}
	
	public Rectangle getCardP1() {
		return cardP1;
	}

	public void setCardP1(Rectangle cardP1) {
		this.cardP1 = cardP1;
	}


	public ListView<String> getListView() {
		return listView;
	}

	public void setListView(ListView<String> listView) {
		this.listView = listView;
	}

}

