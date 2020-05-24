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

	Label lblChat = new Label();
	TextArea txtMessages = new TextArea();
	TextField tfMessage = new TextField();
	Button btnSend = new Button();
	ScrollPane scrollPane = new ScrollPane();

	Rectangle cardP1 = new Rectangle();
	Rectangle cardP2 = new Rectangle();
	Rectangle cardP3 = new Rectangle();
	Rectangle cardP4 = new Rectangle();

	PlayerPane playerPane;
	OtherPlayerPane otherPlayerPane;
	GridPane playedCards;
	CardLabel clPlayedCard;

	ArrayList<OtherPlayerPane> otherPlayerPaneList;
	ArrayList<CardLabel> playedCardList;

	Label lblWait = new Label();
	Label lblPlayroom = new Label();
	Label lblPlayroomName = new Label();
	Label lblGameType = new Label();
	Label lblGameTypeIs = new Label("-");
	Label lblTrumpf = new Label();
	Label lblTrumpfIs = new Label();
	Label lblPointsGoal = new Label();
	Label lblPointsGoalIs = new Label("-");
	Label lblOwner = new Label();
	Label lblOwnerIs = new Label();

	Button btnLeave = new Button();
	Button btnStartGame = new Button();
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

		cardP1.setWidth(40);
		cardP1.setHeight(58);
		cardP1.setArcWidth(2);
		cardP1.setArcHeight(2);
		Image imgP1 = new Image("/jass/image/Rückseite.jpg");
		cardP1.setFill(new ImagePattern(imgP1));
		cardP1.setVisible(true);
//		playedCards.add(cardP1, 1, 2);

		cardP2.setWidth(40);
		cardP2.setHeight(58);
		cardP2.setArcWidth(2);
		cardP2.setArcHeight(2);
		Image imgP2 = new Image("/jass/image/Rückseite.jpg");
		cardP2.setFill(new ImagePattern(imgP2));
		cardP2.setVisible(true);

		cardP3.setWidth(40);
		cardP3.setHeight(58);
		cardP3.setArcWidth(2);
		cardP3.setArcHeight(2);
		Image imgP3 = new Image("/jass/image/Rückseite.jpg");
		cardP3.setFill(new ImagePattern(imgP3));
		cardP3.setVisible(true);

		cardP4.setWidth(40);
		cardP4.setHeight(58);
		cardP4.setArcWidth(2);
		cardP4.setArcHeight(2);
		Image imgP4 = new Image("/jass/image/Rückseite.jpg");
		cardP4.setFill(new ImagePattern(imgP4));
		cardP4.setVisible(true);

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

//		System.out.println("Members:" + countMembers + "ArrayList: " + otherPlayerPaneList.size());
//			
//			
//			for(int i = otherPlayerPaneList.size(); i < countMembers; i++) {
//				
//				if (i == 0) {
//					logger.info("OPL i=0: " + username);
//					OtherPlayerPane otherPlayerPane1 = new OtherPlayerPane(username);
//					otherPlayerPaneList.add(otherPlayerPane1);
//					otherPlayerPane1.getLblName().setText(username);
//					this.add(otherPlayerPane1, 1, 0);
//					countMembers--;
//				}
//				if (i == 1) {
//					logger.info("OPL i=1: " + username);
//					OtherPlayerPane otherPlayerPane2 = new OtherPlayerPane(username);
//					otherPlayerPaneList.add(otherPlayerPane2);
//					otherPlayerPane2.getLblName().setText(username);
//					this.add(otherPlayerPane2, 2, 1);
//					countMembers--;
//				}
//				if (i == 2) {
//					logger.info("OPL i=2: " + username);
//					OtherPlayerPane otherPlayerPane3 = new OtherPlayerPane(username);
//					otherPlayerPaneList.add(otherPlayerPane3);
//					otherPlayerPane3.getLblName().setText(username);
//					this.add(otherPlayerPane3, 0, 1);
//					countMembers--;
//				}
//			}
//		
//		//---> anpassen: 			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
//	}

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
	
	
	
	
	
	//public void clearPlayedCards() {
	//	playedCards.getChildren().clear();
	//}

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
	
//	public void clearPlayedCards() {
//		for (int i = 0; i < playedCardList.size(); i++) {
//			playedCards.getChildren().remove(playedCardList.get(i));
//			playedCardList.remove(i);
//		}
//	}

}
