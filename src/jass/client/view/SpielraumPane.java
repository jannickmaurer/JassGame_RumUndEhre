package jass.client.view;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.ServiceLocator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SpielraumPane extends GridPane {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private JassClientView view;

	private GridPane players;

	private Label lblChat = new Label();
	private TextArea txtMessages = new TextArea();
	private TextField tfMessage = new TextField();
	private Button btnSend = new Button();
	private ScrollPane scrollPane = new ScrollPane();

	private PlayerPane playerPane;
	private OtherPlayerPane otherPlayerPane;
	private GridPane playedCards;
	private CardLabel clPlayedCard;

	private ArrayList<OtherPlayerPane> otherPlayerPaneList;
	private ArrayList<CardLabel> playedCardList;

	private Label lblWait = new Label();
	private Label lblPlayroom = new Label();
	private Label lblPlayroomName = new Label();
	private Label lblGameType = new Label();
	private Label lblGameTypeIs = new Label("-");
	private Label lblTrumpf = new Label();
	private Label lblTrumpfIs = new Label();
	private Label lblPointsGoal = new Label();
	private Label lblPointsGoalIs = new Label("-");
	private Label lblOwner = new Label();
	private Label lblOwnerIs = new Label();

	private Button btnLeave = new Button();
	private Button btnStartGame = new Button();
	int i = 0;

	public SpielraumPane() {
		otherPlayerPaneList = new ArrayList<>();

		VBox vMessage = new VBox();
		vMessage.setId("VBoxMessage");

		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(txtMessages);
		txtMessages.setWrapText(true);
		scrollPane.setId("ScrollPane");
		btnSend.setId("ButtonSend");
		tfMessage.setId("TextFieldMessage");
	
		playedCardList = new ArrayList<>();


		lblChat.setId("LabelBold");
		vMessage.getChildren().addAll(lblChat, scrollPane, tfMessage, btnSend);
		this.add(vMessage, 3, 0, 1, 2);

		GridPane gInformation = new GridPane();
		gInformation.setId("GInfo");
		lblPlayroom.setId("LabelBold");
		lblPointsGoal.setId("LabelBold");
		lblTrumpf.setId("LabelBold");
		lblOwner.setId("LabelBold");
		lblGameType.setId("LabelBold");
		gInformation.add(lblPlayroom, 0, 0);
		gInformation.add(lblPlayroomName, 0, 1);
		gInformation.add(lblOwner, 0, 3);
		gInformation.add(lblOwnerIs, 0, 4);
		gInformation.add(lblPointsGoal, 0, 6);
		gInformation.add(lblPointsGoalIs, 0, 7);
		gInformation.add(lblGameType, 0, 9);
		gInformation.add(lblGameTypeIs, 0, 10);
		gInformation.add(lblTrumpf, 0, 12);
		gInformation.add(lblTrumpfIs, 0, 13);
		this.add(gInformation, 3, 2, 1, 2);

		playerPane = new PlayerPane();
		this.add(playerPane, 0, 2, 3, 1);
		
		playedCards = new GridPane();
		playedCards.setId("HBoxCards");
		
		CardLabel ownCl = new CardLabel("Own");
		playedCards.add(ownCl, 1, 2);
		playedCardList.add(ownCl);

		lblWait.setId("LabelWait");

		this.add(playedCards, 1, 1);

		
		HBox h6 = new HBox();
		h6.setId("HBox");
		h6.getChildren().addAll(btnStartGame, btnLeave);

		VBox vControls = new VBox();
		vControls.setId("VBoxControls");
		vControls.getChildren().addAll(h6);
		this.add(vControls, 1, 3);

		GridPane.setHgrow(playedCards, Priority.ALWAYS);
		GridPane.setVgrow(playedCards, Priority.ALWAYS);
		VBox.setVgrow(scrollPane, Priority.ALWAYS);

		this.setId("root");
	}

	public void createOtherPlayerPanes(ArrayList<String> members) {
		for (int i = 0; i < members.size(); i++) {
			OtherPlayerPane opl = new OtherPlayerPane(members.get(i));
			otherPlayerPaneList.add(opl);
			CardLabel clPlayedCard = new CardLabel(members.get(i));
			if (i == 0) {
				this.add(opl, 1, 0);
				playedCards.add(clPlayedCard, 1, 0);
				playedCardList.add(clPlayedCard);
			}
			if (i == 1) {
				this.add(opl, 2, 1);
				playedCards.add(clPlayedCard, 2, 1);
				playedCardList.add(clPlayedCard);
			}
			if (i == 2) {
				this.add(opl, 0, 1);
				playedCards.add(clPlayedCard, 0, 1);
				playedCardList.add(clPlayedCard);
			}
		}
	}

	public void clearOtherPlayerPaneList() {
		for (int i = 0; i < otherPlayerPaneList.size(); i++) {
			this.getChildren().remove(otherPlayerPaneList.get(i));
			otherPlayerPaneList.remove(i);
		}
	}
	
	
	
//	
//	
//	public void clearPlayedCards() {
//		playedCards.getChildren().clear();
//	}

	public PlayerPane getPlayerPane() {

		return playerPane;
	}

	public void setPlayerPane(PlayerPane playerPane) {
		this.playerPane = playerPane;
	}

	public OtherPlayerPane getOtherPlayerPane(String username) {
		for (OtherPlayerPane opl : otherPlayerPaneList) {
			if (opl.getUsername().equals(username))
				return opl;
		}

		return otherPlayerPane;
	}

	public void setOtherPlayerPane(OtherPlayerPane otherPlayerPane) {
		this.otherPlayerPane = otherPlayerPane;
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

	public Label getLblGameType() {
		return lblGameType;
	}

	public void setLblGameType(Label lblGameType) {
		this.lblGameType = lblGameType;
	}

	public Label getLblGameTypeIs() {
		return lblGameTypeIs;
	}

	public void setLblGameTypeIs(Label lblGameTypeIs) {
		this.lblGameTypeIs = lblGameTypeIs;
	}

	public Label getLblOwner() {
		return lblOwner;
	}

	public void setLblOwner(Label lblOwner) {
		this.lblOwner = lblOwner;
	}

	public Label getLblOwnerIs() {
		return lblOwnerIs;
	}

	public void setLblOwnerIs(Label lblOwnerIs) {
		this.lblOwnerIs = lblOwnerIs;
	}
	
	public void updatePlayedCard(String username, String playedCard) {
		logger.info("Update Played Card: " + username + " " + playedCard);
		for(int i = 0; i < playedCardList.size(); i++) {
			if(playedCardList.get(i).getUsername().equals(username)) {
				CardLabel cl = (CardLabel) playedCardList.get(i);
				cl.setCard(new Card(playedCard));
			}
		}
	}
	
	public void clearPlayedCards() {
		for(int i = 0; i < playedCardList.size(); i++) {
			playedCardList.remove(i);
//				CardLabel cl = (CardLabel) playedCardList.get(i);
//				cl.setCard(null);
		}
	}

	public GridPane getPlayers() {
		return players;
	}

	public void setPlayers(GridPane players) {
		this.players = players;
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

	public GridPane getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCards(GridPane playedCards) {
		this.playedCards = playedCards;
	}

	public CardLabel getClPlayedCard() {
		return clPlayedCard;
	}

	public void setClPlayedCard(CardLabel clPlayedCard) {
		this.clPlayedCard = clPlayedCard;
	}

	public ArrayList<OtherPlayerPane> getOtherPlayerPaneList() {
		return otherPlayerPaneList;
	}

	public void setOtherPlayerPaneList(ArrayList<OtherPlayerPane> otherPlayerPaneList) {
		this.otherPlayerPaneList = otherPlayerPaneList;
	}

	public ArrayList<CardLabel> getPlayedCardList() {
		return playedCardList;
	}

	public void setPlayedCardList(ArrayList<CardLabel> playedCardList) {
		this.playedCardList = playedCardList;
	}

	public Label getLblWait() {
		return lblWait;
	}

	public void setLblWait(Label lblWait) {
		this.lblWait = lblWait;
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

	public OtherPlayerPane getOtherPlayerPane() {
		return otherPlayerPane;
	}
	
}
