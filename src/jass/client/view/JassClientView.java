//Geschrieben von Samuel David und Jannick Maurer

package jass.client.view;

import java.util.Locale;

import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.commons.Translator;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JassClientView {
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	
	protected Stage primaryStage;
	private JassClientModel model;
	public BorderPane root;
	
	Menu menuFileLanguage = new Menu();
	
	private Scene scene, connectScene, lobbyScene, loginScene, registrationScene, spielraumScene;
	public ConnectPane connectLayout = new ConnectPane();
	public LoginPane loginLayout = new LoginPane();
	public RegistrationPane registrationLayout = new RegistrationPane();
	public SpielraumPopupPane spielraumPopupLayout = new SpielraumPopupPane();
	public SpielraumPane spielraumLayout = new SpielraumPane();
	public StartGamePopupPane startGamePopupLayout = new StartGamePopupPane();
	public ProfilPopUpPane profilPopupLayout = new ProfilPopUpPane();
	public ErrorPopupPane errorPopupLayout = new ErrorPopupPane();
	public ErrorPopupPaneConnect errorPopupConnectLayout = new ErrorPopupPaneConnect();
	public ErrorPopupPaneCreatePlayroom errorPopupCreatePlayroomLayout = new ErrorPopupPaneCreatePlayroom();
	public ErrorPopupPaneLogin errorPopupLoginLayout = new ErrorPopupPaneLogin();
	public ErrorPopupPaneRegistration errorPopupRegistrationLayout = new ErrorPopupPaneRegistration();
	public ErrorPopupPaneStartGame errorPopupStartGameLayout = new ErrorPopupPaneStartGame();
	public SiegerPopupPane siegerPopupLayout = new SiegerPopupPane();
	public TrumpfPopupPane trumpfPopupLayout = new TrumpfPopupPane();
	public WyssPopupPane wyssPopupLayout = new WyssPopupPane();
	public GameTypePopupPane gameTypePopupLayout = new GameTypePopupPane();

	
	public Popup profilPopUp = new Popup();
	public Popup createSpielraumPopUp = new Popup();
	public Popup startGamePopUp = new Popup();
	public Popup errorPopUp = new Popup();
	public Popup errorConnectPopUp = new Popup();
	public Popup errorCreatePlayroomPopUp = new Popup();
	public Popup errorLoginPopUp = new Popup();
	public Popup errorRegistrationPopUp = new Popup();
	public Popup errorStartGamePopUp = new Popup();
	public Popup siegerPopUp = new Popup();
	public Popup trumpfPopUp = new Popup();
	public Popup wyssPopUp = new Popup();
	public Popup gameTypePopup = new Popup();
	
	private Label lblPort = connectLayout.getLblPort();
	private Label lblIP = connectLayout.getLblIP();
	private TextField tfPort = connectLayout.getTfPort();
	private TextField tfIP = connectLayout.getTfIP();
	private Button btnRun = connectLayout.getBtnRun();
	private Button btnStart = connectLayout.getBtnStart();
	
	private Label lblTitelLogin = loginLayout.getLblTitelLogin();
	private Label lblSubtitelLogin = loginLayout.getLblSubtitelLogin();
	private TextField tfUsername = loginLayout.getTfUsername();
	private PasswordField pfPassword = loginLayout.getPfPassword();
	private Button btnLogin = loginLayout.getBtnLogin();
	private Button btnRegistration = loginLayout.getBtnRegistration();
	
	private Label lblTitelRegistration = registrationLayout.getLblTitelRegistration();
	private Label lblSubtitelRegistration = registrationLayout.getLblSubtitelRegistration();
	private TextField tfNewUsername = registrationLayout.getTfNewUsername();
	private PasswordField pfNewPassword = registrationLayout.getPfNewPassword();
	private Button btnNewRegistration = registrationLayout.getBtnRegistration();
	private Button btnBack = registrationLayout.getBtnBack();
	
	private Button btnProfil = new Button();
	private Button btnConfig = new Button();
	private Button btnJoin = new Button();
	private Button btnCreatePlayroom = new Button();
	private Button btnDeletePlayroom = new Button();
	private Button btnLogout = new Button();
	private ListView<String> listView;
	
	private VBox v1 = new VBox();
	private HBox h1 = new HBox();
	
	private Button btnDeleteAccount = profilPopupLayout.getBtnDeleteAccount();
	private Button btnBackProfil = profilPopupLayout.getBtnBack();
	
	private TextField tfSpielraumName = spielraumPopupLayout.getTfSpielraumName();
	private Button btnCreatePlayroomPopup = spielraumPopupLayout.getBtnCreate();
	private Button btnBackPlayroom = spielraumPopupLayout.getBtnBack();
	
	private Label lblError = errorPopupLayout.getLblError();
	private Button btnBackError = errorPopupLayout.getBtnBack();
	
	private Label lblErrorConnect = errorPopupConnectLayout.getLblErrorConnect();
	private Button btnBackErrorConnect = errorPopupConnectLayout.getBtnBack();
	
	private Label lblErrorCreatePlayroom = errorPopupCreatePlayroomLayout.getLblErrorCreatePlayroom();
	private Button btnBackErrorCreatePlayroom = errorPopupCreatePlayroomLayout.getBtnBack();
	
	private Label lblErrorLogin = errorPopupLoginLayout.getLblErrorLogin();
	private Button btnBackErrorLogin = errorPopupLoginLayout.getBtnBack();
	
	private Label lblErrorRegistration = errorPopupRegistrationLayout.getLblErrorRegistration();
	private Button btnBackErrorRegistration = errorPopupRegistrationLayout.getBtnBack();
	
	private Label lblErrorStartGame = errorPopupStartGameLayout.getLblErrorStartGame();
	private Button btnBackErrorStartGame = errorPopupStartGameLayout.getBtnBack();
	
	private Label lblWinner = siegerPopupLayout.getLblWinner();
	private Button btnBackSieger = siegerPopupLayout.getBtnBack();
	
	private Label lblChat = spielraumLayout.getLblChat();
	private Label lblWait = spielraumLayout.getLblWait();
	private TextArea txtMessages = spielraumLayout.getTxtMessages();
	private TextField tfMessage = spielraumLayout.getTfMessage();
	private Button btnSend = spielraumLayout.getBtnSend();
	private ScrollPane scrollPane = spielraumLayout.getScrollPane();
	private Button btnLeave = spielraumLayout.getBtnLeave();
	private Button btnStartGame = spielraumLayout.getBtnStartGame();
	private Label lblPlayroom = spielraumLayout.getLblPlayroom();
	private Label lblPlayroomName = spielraumLayout.getLblPlayroomName();
	private Label lblPointsGoal = spielraumLayout.getLblPointsGoal();
	private Label lblPointsGoalIs = spielraumLayout.getLblPointsGoalIs();
	private Label lblTrumpf = spielraumLayout.getLblTrumpf();
	private Label lblTrumpfIs = spielraumLayout.getLblTrumpfIs();
	
	private Label lblPointsLimit = startGamePopupLayout.getLblPointsLimit();
	private Button btnBackStartGame = startGamePopupLayout.getBtnBack();
	private Button btnStartGamePopUp = startGamePopupLayout.getBtnStartGamePopUp();
	private TextField tfPoints = startGamePopupLayout.getTfPoints();
	
	private Label lblChooseTrumpf = trumpfPopupLayout.getLblChooseTrumpf();
	private Button btnHearts = trumpfPopupLayout.getBtnHearts();
	private Button btnDiamonds = trumpfPopupLayout.getBtnDiamonds();
	private Button btnSpades = trumpfPopupLayout.getBtnSpades();
	private Button btnClubs = trumpfPopupLayout.getBtnClubs();
	
	// Initialize GameTypePopup Buttons
	private Button btnTrumpf = gameTypePopupLayout.getBtnTrumpf();
	
	private Label lblWyss = wyssPopupLayout.getLblWyss();
	private Button btnWyss = wyssPopupLayout.getBtnWyss();
	private Button btnNoWyss = wyssPopupLayout.getBtnNoWyss();
	
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
		pfPassword.setMinWidth(Region.USE_PREF_SIZE);
		pfPassword.setPrefWidth(240);
		btnLogin.setMinWidth(Region.USE_PREF_SIZE);
		btnLogin.setPrefWidth(100);
		btnRegistration.setMinWidth(Region.USE_PREF_SIZE);
		btnRegistration.setPrefWidth(100);
		lblTitelRegistration.setMinWidth(Region.USE_PREF_SIZE);
		lblSubtitelRegistration.setMinWidth(Region.USE_PREF_SIZE);
		tfNewUsername.setMinWidth(Region.USE_PREF_SIZE);
		tfNewUsername.setPrefWidth(240);
		pfNewPassword.setMinWidth(Region.USE_PREF_SIZE);
		pfNewPassword.setPrefWidth(240);
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
		btnBackErrorConnect.setMinWidth(Region.USE_PREF_SIZE);
		btnBackErrorConnect.setPrefWidth(140);
		btnBackErrorCreatePlayroom.setMinWidth(Region.USE_PREF_SIZE);
		btnBackErrorCreatePlayroom.setPrefWidth(140);
		btnBackErrorLogin.setMinWidth(Region.USE_PREF_SIZE);
		btnBackErrorLogin.setPrefWidth(140);
		btnBackErrorRegistration.setMinWidth(Region.USE_PREF_SIZE);
		btnBackErrorRegistration.setPrefWidth(140);
		btnBackErrorStartGame.setMinWidth(Region.USE_PREF_SIZE);
		btnBackErrorStartGame.setPrefWidth(140);
		btnBackProfil.setMinWidth(Region.USE_PREF_SIZE);
		btnBackProfil.setPrefWidth(140);
		btnBackSieger.setMinWidth(Region.USE_PREF_SIZE);
		btnBackSieger.setPrefWidth(140);
		btnBackStartGame.setMinWidth(Region.USE_PREF_SIZE);
		btnBackStartGame.setPrefWidth(140);
		btnTrumpf.setMinWidth(Region.USE_PREF_SIZE);
		btnTrumpf.setPrefWidth(180);
		btnHearts.setMinWidth(Region.USE_PREF_SIZE);
		btnHearts.setPrefWidth(140);
		btnDiamonds.setMinWidth(Region.USE_PREF_SIZE);
		btnDiamonds.setPrefWidth(140);
		btnSpades.setMinWidth(Region.USE_PREF_SIZE);
		btnSpades.setPrefWidth(140);
		btnClubs.setMinWidth(Region.USE_PREF_SIZE);
		btnClubs.setPrefWidth(140);
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
		
		registrationLayout.getBtnRegistration().setDisable(false); // changed from Jannick
		spielraumLayout.getBtnStartGame().setDisable(true);
		
		this.btnConfig.setDisable(true);
		this.btnJoin.setDisable(false);
		this.btnDeletePlayroom.setDisable(false);
		v1.setId("VBox");
		
		h1.setId("HBoxTop");
		h1.getChildren().addAll(btnLogout);
		
		HBox h2 = new HBox();
		h2.setId("HBoxRight");
		h2.getChildren().addAll(btnJoin, btnCreatePlayroom);
		listView = new ListView<>(model.getPlayrooms());
		v1.getChildren().addAll(h1, listView, h2);
		listView.setMinHeight(Region.USE_PREF_SIZE);
		listView.setPrefHeight(440);
		
		siegerPopUp.getContent().add(siegerPopupLayout);
		siegerPopUp.setAutoHide(false);
		
		trumpfPopUp.getContent().add(trumpfPopupLayout);
		trumpfPopUp.setAutoHide(false);
		
		gameTypePopup.getContent().add(gameTypePopupLayout);
		gameTypePopup.setAutoHide(false);
		
		wyssPopUp.getContent().add(wyssPopupLayout);
		wyssPopUp.setAutoHide(false);
		
		errorPopUp.getContent().add(errorPopupLayout);
		errorPopUp.setAutoHide(false);
		
		errorConnectPopUp.getContent().add(errorPopupConnectLayout);
		errorConnectPopUp.setAutoHide(false);
		
		errorCreatePlayroomPopUp.getContent().add(errorPopupCreatePlayroomLayout);
		errorCreatePlayroomPopUp.setAutoHide(false);
		
		errorLoginPopUp.getContent().add(errorPopupLoginLayout);
		errorLoginPopUp.setAutoHide(false);
		
		errorRegistrationPopUp.getContent().add(errorPopupRegistrationLayout);
		errorRegistrationPopUp.setAutoHide(false);
		
		errorStartGamePopUp.getContent().add(errorPopupStartGameLayout);
		errorStartGamePopUp.setAutoHide(false);
		
		profilPopUp.getContent().add(profilPopupLayout);
		profilPopUp.setAutoHide(false);
		
		btnNewRegistration.disableProperty().bind(tfNewUsername.textProperty().isEmpty() .or(pfNewPassword.textProperty().isEmpty()));
		
		btnStartGamePopUp.disableProperty().bind(tfPoints.textProperty().isEmpty());
		
		createSpielraumPopUp.getContent().add(spielraumPopupLayout);
		spielraumPopupLayout.getBtnCreate().disableProperty().bind(spielraumPopupLayout.getTfSpielraumName().textProperty().isEmpty());
		createSpielraumPopUp.setAutoHide(false);
		
		startGamePopUp.getContent().add(startGamePopupLayout);
		startGamePopUp.setAutoHide(false);
		
		txtMessages.setEditable(false);
		spielraumLayout.getBtnSend().disableProperty().bind(spielraumLayout.getTfMessage().textProperty().isEmpty());
		v1.setVgrow(listView, Priority.ALWAYS);
		
		scene = new Scene(root, 960, 635);
		
		scene.getStylesheets().add(
                getClass().getResource("Client.css").toExternalForm());
		primaryStage.setMaximized(true);
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		primaryStage.setResizable(false);
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
		connectLayout.getLblIP().setText(t.getString("label.IPAdress"));
		connectLayout.getLblPort().setText(t.getString("label.port"));
		loginLayout.getLblTitelLogin().setText(t.getString("text.titel.jass"));
		loginLayout.getLblSubtitelLogin().setText(t.getString("text.untertitel.login"));
		loginLayout.getTfUsername().setPromptText(t.getString("label.username"));
		loginLayout.getPfPassword().setPromptText(t.getString("label.password"));
		registrationLayout.getLblTitelRegistration().setText(t.getString("text.titel.jass"));
		registrationLayout.getLblSubtitelRegistration().setText(t.getString("text.untertitel.registration"));
		registrationLayout.getTfNewUsername().setPromptText(t.getString("label.username"));
		registrationLayout.getPfNewPassword().setPromptText(t.getString("label.password"));
		spielraumPopupLayout.getTfSpielraumName().setPromptText(t.getString("label.spielraumname"));
		spielraumLayout.getLblChat().setText(t.getString("label.chat"));
		spielraumLayout.getLblWait().setText(t.getString("label.wait"));
		spielraumLayout.getLblPlayroom().setText(t.getString("label.spielraumname"));
		spielraumLayout.getLblPointsGoal().setText(t.getString("label.limit"));
		spielraumLayout.getLblOwner().setText(t.getString("label.owner"));
		spielraumLayout.getLblGameType().setText(t.getString("label.gametype"));
		startGamePopupLayout.getLblPointsLimit().setText(t.getString("label.limit"));
		errorPopupLayout.getLblError().setText(t.getString("label.error"));
		errorPopupConnectLayout.getLblErrorConnect().setText(t.getString("label.error.connect"));
		errorPopupCreatePlayroomLayout.getLblErrorCreatePlayroom().setText(t.getString("label.error.createplayroom"));
		errorPopupLoginLayout.getLblErrorLogin().setText(t.getString("label.error.login"));
		errorPopupRegistrationLayout.getLblErrorRegistration().setText(t.getString("label.error.registration"));
		errorPopupStartGameLayout.getLblErrorStartGame().setText(t.getString("label.error.startgame"));
		siegerPopupLayout.getLblWinner().setText(t.getString("label.winner"));
		trumpfPopupLayout.getLblChooseTrumpf().setText(t.getString("label.choosetrumpf"));
		wyssPopupLayout.getLblWyss().setText(t.getString("label.wyss"));
		gameTypePopupLayout.getLblChooseGameType().setText(t.getString("label.gametype"));
		
	    // Other controls
		connectLayout.getBtnRun().setText(t.getString("button.run"));
		connectLayout.getBtnStart().setText(t.getString("button.start"));
		loginLayout.getBtnLogin().setText(t.getString("button.login"));
		loginLayout.getBtnRegistration().setText(t.getString("button.registration"));
		registrationLayout.getBtnRegistration().setText(t.getString("button.registration"));
		registrationLayout.getBtnBack().setText(t.getString("button.back"));
		profilPopupLayout.getBtnDeleteAccount().setText(t.getString("button.deleteaccount"));
		this.btnProfil.setText(t.getString("button.profil"));
		this.btnConfig.setText(t.getString("button.config"));
		this.btnJoin.setText(t.getString("button.join"));
		this.btnCreatePlayroom.setText(t.getString("button.createplayroom"));
		this.btnDeletePlayroom.setText(t.getString("button.deleteplayroom"));
		this.btnLogout.setText(t.getString("button.logout"));
		spielraumPopupLayout.getBtnCreate().setText(t.getString("button.createplayroom"));
		spielraumPopupLayout.getBtnBack().setText(t.getString("button.back"));
		spielraumLayout.getBtnSend().setText(t.getString("button.send"));
		spielraumLayout.getBtnLeave().setText(t.getString("button.leave"));
		spielraumLayout.getBtnStartGame().setText(t.getString("button.start"));
		startGamePopupLayout.getBtnStartGamePopUp().setText(t.getString("button.start"));
		startGamePopupLayout.getBtnBack().setText(t.getString("button.back"));
		wyssPopupLayout.getBtnWyss().setText(t.getString("button.wyss"));
		wyssPopupLayout.getBtnNoWyss().setText(t.getString("button.nowyss"));
		profilPopupLayout.getBtnBack().setText(t.getString("button.back"));
		errorPopupLayout.getBtnBack().setText(t.getString("button.back"));
		errorPopupConnectLayout.getBtnBack().setText(t.getString("button.back"));
		errorPopupCreatePlayroomLayout.getBtnBack().setText(t.getString("button.back"));
		errorPopupLoginLayout.getBtnBack().setText(t.getString("button.back"));
		errorPopupRegistrationLayout.getBtnBack().setText(t.getString("button.back"));
		errorPopupStartGameLayout.getBtnBack().setText(t.getString("button.back"));
		siegerPopupLayout.getBtnBack().setText(t.getString("button.back.sieger"));
		trumpfPopupLayout.getBtnHearts().setText(t.getString("button.hearts"));
		trumpfPopupLayout.getBtnDiamonds().setText(t.getString("button.diamonds"));
		trumpfPopupLayout.getBtnSpades().setText(t.getString("button.spades"));
		trumpfPopupLayout.getBtnClubs().setText(t.getString("button.clubs"));
		gameTypePopupLayout.getBtnTrumpf().setText(t.getString("button.trumpf"));
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
	
	public TextField getTfNewUsername() {
		return tfNewUsername;
	}

	public void setTfNewUsername(TextField tfNewUsername) {
		this.tfNewUsername = tfNewUsername;
	}
	
	public PasswordField getPfNewPassword() {
		return pfNewPassword;
	}

	public void setPfNewPassword(PasswordField pfNewPassword) {
		this.pfNewPassword = pfNewPassword;
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

	public Button getBtnWyss() {
		return btnWyss;
	}

	public void setBtnWyss(Button btnWyss) {
		this.btnWyss = btnWyss;
	}
	
	public Button getBtnTrumpf() {
		return btnTrumpf;
	}

	public void setBtnTrumpf(Button btnTrumpf) {
		this.btnTrumpf = btnTrumpf;
	}

	public Button getBtnNoWyss() {
		return btnNoWyss;
	}

	public void setBtnNoWyss(Button btnNoWyss) {
		this.btnNoWyss = btnNoWyss;
	}

	public ListView<String> getListView() {
		return listView;
	}

	public void setListView(ListView<String> listView) {
		this.listView = listView;
	}

	public SpielraumPane getSpielraumLayout() {
		return spielraumLayout;
	}

	public void setSpielraumLayout(SpielraumPane spielraumLayout) {
		this.spielraumLayout = spielraumLayout;
	}

	public Label getLblPlayroom() {
		return lblPlayroom;
	}

	public void setLblPlayroom(Label lblPlayroom) {
		this.lblPlayroom = lblPlayroom;
	}

	public Label getLblPlayroomName() {
		return lblPlayroomName;
	}

	public void setLblPlayroomName(Label lblPlayroomName) {
		this.lblPlayroomName = lblPlayroomName;
	}

	public Label getLblPointsGoal() {
		return lblPointsGoal;
	}

	public void setLblPointsGoal(Label lblPointsGoal) {
		this.lblPointsGoal = lblPointsGoal;
	}

	public Label getLblPointsGoalIs() {
		return lblPointsGoalIs;
	}

	public void setLblPointsGoalIs(Label lblPointsGoalIs) {
		this.lblPointsGoalIs = lblPointsGoalIs;
	}

	public Label getLblTrumpf() {
		return lblTrumpf;
	}

	public void setLblTrumpf(Label lblTrumpf) {
		this.lblTrumpf = lblTrumpf;
	}

	public Label getLblTrumpfIs() {
		return lblTrumpfIs;
	}

	public void setLblTrumpfIs(Label lblTrumpfIs) {
		this.lblTrumpfIs = lblTrumpfIs;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Menu getMenuFileLanguage() {
		return menuFileLanguage;
	}

	public void setMenuFileLanguage(Menu menuFileLanguage) {
		this.menuFileLanguage = menuFileLanguage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Scene getConnectScene() {
		return connectScene;
	}

	public void setConnectScene(Scene connectScene) {
		this.connectScene = connectScene;
	}

	public Scene getLobbyScene() {
		return lobbyScene;
	}

	public void setLobbyScene(Scene lobbyScene) {
		this.lobbyScene = lobbyScene;
	}

	public Scene getLoginScene() {
		return loginScene;
	}

	public void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}

	public Scene getRegistrationScene() {
		return registrationScene;
	}

	public void setRegistrationScene(Scene registrationScene) {
		this.registrationScene = registrationScene;
	}

	public Scene getSpielraumScene() {
		return spielraumScene;
	}

	public void setSpielraumScene(Scene spielraumScene) {
		this.spielraumScene = spielraumScene;
	}

	public ConnectPane getConnectLayout() {
		return connectLayout;
	}

	public void setConnectLayout(ConnectPane connectLayout) {
		this.connectLayout = connectLayout;
	}

	public LoginPane getLoginLayout() {
		return loginLayout;
	}

	public void setLoginLayout(LoginPane loginLayout) {
		this.loginLayout = loginLayout;
	}

	public RegistrationPane getRegistrationLayout() {
		return registrationLayout;
	}

	public void setRegistrationLayout(RegistrationPane registrationLayout) {
		this.registrationLayout = registrationLayout;
	}

	public SpielraumPopupPane getSpielraumPopupLayout() {
		return spielraumPopupLayout;
	}

	public void setSpielraumPopupLayout(SpielraumPopupPane spielraumPopupLayout) {
		this.spielraumPopupLayout = spielraumPopupLayout;
	}

	public StartGamePopupPane getStartGamePopupLayout() {
		return startGamePopupLayout;
	}

	public void setStartGamePopupLayout(StartGamePopupPane startGamePopupLayout) {
		this.startGamePopupLayout = startGamePopupLayout;
	}

	public ProfilPopUpPane getProfilPopupLayout() {
		return profilPopupLayout;
	}

	public void setProfilPopupLayout(ProfilPopUpPane profilPopupLayout) {
		this.profilPopupLayout = profilPopupLayout;
	}

	public ErrorPopupPane getErrorPopupLayout() {
		return errorPopupLayout;
	}

	public void setErrorPopupLayout(ErrorPopupPane errorPopupLayout) {
		this.errorPopupLayout = errorPopupLayout;
	}

	public SiegerPopupPane getSiegerPopupLayout() {
		return siegerPopupLayout;
	}

	public void setSiegerPopupLayout(SiegerPopupPane siegerPopupLayout) {
		this.siegerPopupLayout = siegerPopupLayout;
	}

	public TrumpfPopupPane getTrumpfPopupLayout() {
		return trumpfPopupLayout;
	}

	public void setTrumpfPopupLayout(TrumpfPopupPane trumpfPopupLayout) {
		this.trumpfPopupLayout = trumpfPopupLayout;
	}

	public WyssPopupPane getWyssPopupLayout() {
		return wyssPopupLayout;
	}

	public void setWyssPopupLayout(WyssPopupPane wyssPopupLayout) {
		this.wyssPopupLayout = wyssPopupLayout;
	}

	public GameTypePopupPane getGameTypePopupLayout() {
		return gameTypePopupLayout;
	}

	public void setGameTypePopupLayout(GameTypePopupPane gameTypePopupLayout) {
		this.gameTypePopupLayout = gameTypePopupLayout;
	}

	public Popup getProfilPopUp() {
		return profilPopUp;
	}

	public void setProfilPopUp(Popup profilPopUp) {
		this.profilPopUp = profilPopUp;
	}

	public Popup getStartGamePopUp() {
		return startGamePopUp;
	}

	public void setStartGamePopUp(Popup startGamePopUp) {
		this.startGamePopUp = startGamePopUp;
	}

	public Popup getErrorPopUp() {
		return errorPopUp;
	}

	public void setErrorPopUp(Popup errorPopUp) {
		this.errorPopUp = errorPopUp;
	}

	public Popup getSiegerPopUp() {
		return siegerPopUp;
	}

	public void setSiegerPopUp(Popup siegerPopUp) {
		this.siegerPopUp = siegerPopUp;
	}

	public Popup getTrumpfPopUp() {
		return trumpfPopUp;
	}

	public void setTrumpfPopUp(Popup trumpfPopUp) {
		this.trumpfPopUp = trumpfPopUp;
	}

	public Popup getWyssPopUp() {
		return wyssPopUp;
	}

	public void setWyssPopUp(Popup wyssPopUp) {
		this.wyssPopUp = wyssPopUp;
	}

	public Popup getGameTypePopup() {
		return gameTypePopup;
	}

	public void setGameTypePopup(Popup gameTypePopup) {
		this.gameTypePopup = gameTypePopup;
	}

	public Label getLblPort() {
		return lblPort;
	}

	public void setLblPort(Label lblPort) {
		this.lblPort = lblPort;
	}

	public Label getLblIP() {
		return lblIP;
	}

	public void setLblIP(Label lblIP) {
		this.lblIP = lblIP;
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

	public Label getLblTitelRegistration() {
		return lblTitelRegistration;
	}

	public void setLblTitelRegistration(Label lblTitelRegistration) {
		this.lblTitelRegistration = lblTitelRegistration;
	}

	public Label getLblSubtitelRegistration() {
		return lblSubtitelRegistration;
	}

	public void setLblSubtitelRegistration(Label lblSubtitelRegistration) {
		this.lblSubtitelRegistration = lblSubtitelRegistration;
	}

	public VBox getV1() {
		return v1;
	}

	public void setV1(VBox v1) {
		this.v1 = v1;
	}

	public HBox getH1() {
		return h1;
	}

	public void setH1(HBox h1) {
		this.h1 = h1;
	}

	public Label getLblError() {
		return lblError;
	}

	public void setLblError(Label lblError) {
		this.lblError = lblError;
	}

	public Label getLblPointsLimit() {
		return lblPointsLimit;
	}

	public void setLblPointsLimit(Label lblPointsLimit) {
		this.lblPointsLimit = lblPointsLimit;
	}

	public Label getLblChooseTrumpf() {
		return lblChooseTrumpf;
	}

	public void setLblChooseTrumpf(Label lblChooseTrumpf) {
		this.lblChooseTrumpf = lblChooseTrumpf;
	}

	public Label getLblWyss() {
		return lblWyss;
	}

	public void setLblWyss(Label lblWyss) {
		this.lblWyss = lblWyss;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public void setBtnStartGamePopUp(Button btnStartGamePopUp) {
		this.btnStartGamePopUp = btnStartGamePopUp;
	}

	public ErrorPopupPaneConnect getErrorPopupConnectLayout() {
		return errorPopupConnectLayout;
	}

	public void setErrorPopupConnectLayout(ErrorPopupPaneConnect errorPopupConnectLayout) {
		this.errorPopupConnectLayout = errorPopupConnectLayout;
	}

	public ErrorPopupPaneCreatePlayroom getErrorPopupCreatePlayroomLayout() {
		return errorPopupCreatePlayroomLayout;
	}

	public void setErrorPopupCreatePlayroomLayout(ErrorPopupPaneCreatePlayroom errorPopupCreatePlayroomLayout) {
		this.errorPopupCreatePlayroomLayout = errorPopupCreatePlayroomLayout;
	}

	public ErrorPopupPaneLogin getErrorPopupLoginLayout() {
		return errorPopupLoginLayout;
	}

	public void setErrorPopupLoginLayout(ErrorPopupPaneLogin errorPopupLoginLayout) {
		this.errorPopupLoginLayout = errorPopupLoginLayout;
	}

	public ErrorPopupPaneRegistration getErrorPopupRegistrationLayout() {
		return errorPopupRegistrationLayout;
	}

	public void setErrorPopupRegistrationLayout(ErrorPopupPaneRegistration errorPopupRegistrationLayout) {
		this.errorPopupRegistrationLayout = errorPopupRegistrationLayout;
	}

	public ErrorPopupPaneStartGame getErrorPopupStartGameLayout() {
		return errorPopupStartGameLayout;
	}

	public void setErrorPopupStartGameLayout(ErrorPopupPaneStartGame errorPopupStartGameLayout) {
		this.errorPopupStartGameLayout = errorPopupStartGameLayout;
	}

	public Popup getErrorConnectPopUp() {
		return errorConnectPopUp;
	}

	public void setErrorConnectPopUp(Popup errorConnectPopUp) {
		this.errorConnectPopUp = errorConnectPopUp;
	}

	public Popup getErrorCreatePlayroomPopUp() {
		return errorCreatePlayroomPopUp;
	}

	public void setErrorCreatePlayroomPopUp(Popup errorCreatePlayroomPopUp) {
		this.errorCreatePlayroomPopUp = errorCreatePlayroomPopUp;
	}

	public Popup getErrorLoginPopUp() {
		return errorLoginPopUp;
	}

	public void setErrorLoginPopUp(Popup errorLoginPopUp) {
		this.errorLoginPopUp = errorLoginPopUp;
	}

	public Popup getErrorRegistrationPopUp() {
		return errorRegistrationPopUp;
	}

	public void setErrorRegistrationPopUp(Popup errorRegistrationPopUp) {
		this.errorRegistrationPopUp = errorRegistrationPopUp;
	}

	public Popup getErrorStartGamePopUp() {
		return errorStartGamePopUp;
	}

	public void setErrorStartGamePopUp(Popup errorStartGamePopUp) {
		this.errorStartGamePopUp = errorStartGamePopUp;
	}

	public Label getLblErrorConnect() {
		return lblErrorConnect;
	}

	public void setLblErrorConnect(Label lblErrorConnect) {
		this.lblErrorConnect = lblErrorConnect;
	}

	public Button getBtnBackErrorConnect() {
		return btnBackErrorConnect;
	}

	public void setBtnBackErrorConnect(Button btnBackErrorConnect) {
		this.btnBackErrorConnect = btnBackErrorConnect;
	}

	public Label getLblErrorCreatePlayroom() {
		return lblErrorCreatePlayroom;
	}

	public void setLblErrorCreatePlayroom(Label lblErrorCreatePlayroom) {
		this.lblErrorCreatePlayroom = lblErrorCreatePlayroom;
	}

	public Button getBtnBackErrorCreatePlayroom() {
		return btnBackErrorCreatePlayroom;
	}

	public void setBtnBackErrorCreatePlayroom(Button btnBackErrorCreatePlayroom) {
		this.btnBackErrorCreatePlayroom = btnBackErrorCreatePlayroom;
	}

	public Label getLblErrorLogin() {
		return lblErrorLogin;
	}

	public void setLblErrorLogin(Label lblErrorLogin) {
		this.lblErrorLogin = lblErrorLogin;
	}

	public Button getBtnBackErrorLogin() {
		return btnBackErrorLogin;
	}

	public void setBtnBackErrorLogin(Button btnBackErrorLogin) {
		this.btnBackErrorLogin = btnBackErrorLogin;
	}

	public Label getLblErrorRegistration() {
		return lblErrorRegistration;
	}

	public void setLblErrorRegistration(Label lblErrorRegistration) {
		this.lblErrorRegistration = lblErrorRegistration;
	}

	public Button getBtnBackErrorRegistration() {
		return btnBackErrorRegistration;
	}

	public void setBtnBackErrorRegistration(Button btnBackErrorRegistration) {
		this.btnBackErrorRegistration = btnBackErrorRegistration;
	}

	public Label getLblErrorStartGame() {
		return lblErrorStartGame;
	}

	public void setLblErrorStartGame(Label lblErrorStartGame) {
		this.lblErrorStartGame = lblErrorStartGame;
	}

	public Button getBtnBackErrorStartGame() {
		return btnBackErrorStartGame;
	}

	public void setBtnBackErrorStartGame(Button btnBackErrorStartGame) {
		this.btnBackErrorStartGame = btnBackErrorStartGame;
	}
	
}

