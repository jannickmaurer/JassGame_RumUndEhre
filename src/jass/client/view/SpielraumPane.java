package jass.client.view;

import java.util.ArrayList;

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
    
    ArrayList<OtherPlayerPane> otherPlayerPaneList;
    
    Label lblWait = new Label();
  
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

		vMessage.getChildren().addAll(lblChat, scrollPane, tfMessage, btnSend);
		this.add(vMessage, 3, 0, 1, 4);
		
		playerPane = new PlayerPane();
		this.add(playerPane, 0, 2, 3, 1);
		
		HBox hGame = new HBox();
		hGame.setId("HBoxCards");
		
		VBox vCardsGame = new VBox();
		vCardsGame.setId("VBoxCards");
		
		cardP1.setWidth(40);
		cardP1.setHeight(58);
		cardP1.setArcWidth(2);
		cardP1.setArcHeight(2);
		Image imgP1 = new Image("/jass/image/Rückseite.jpg");
		cardP1.setFill(new ImagePattern(imgP1));
		cardP1.setVisible(false);
		
		cardP2.setWidth(40);
		cardP2.setHeight(58);
		cardP2.setArcWidth(2);
		cardP2.setArcHeight(2);
		Image imgP2 = new Image("/jass/image/Rückseite.jpg");
		cardP2.setFill(new ImagePattern(imgP2));
		cardP2.setVisible(false);
		
		cardP3.setWidth(40);
		cardP3.setHeight(58);
		cardP3.setArcWidth(2);
		cardP3.setArcHeight(2);
		Image imgP3 = new Image("/jass/image/Rückseite.jpg");
		cardP3.setFill(new ImagePattern(imgP3));
		cardP3.setVisible(false);
		
		cardP4.setWidth(40);
		cardP4.setHeight(58);
		cardP4.setArcWidth(2);
		cardP4.setArcHeight(2);
		Image imgP4 = new Image("/jass/image/Rückseite.jpg");
		cardP4.setFill(new ImagePattern(imgP4));
		cardP4.setVisible(false);
		
		lblWait.setId("LabelWait");
		vCardsGame.getChildren().addAll(cardP3, cardP1);
		
		hGame.getChildren().addAll(cardP4, vCardsGame, cardP2);
		this.add(hGame, 1, 1);
		
		HBox h6 = new HBox();
		h6.setId("HBox");
		h6.getChildren().addAll(btnStartGame, btnLeave);
		
		VBox vControls = new VBox();
		vControls.setId("VBoxControls");
		vControls.getChildren().addAll(h6);
		this.add(vControls, 1, 3);
	
		GridPane.setHgrow(hGame, Priority.ALWAYS);
		GridPane.setVgrow(hGame, Priority.ALWAYS);
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		
		this.setId("root");
	}
	
	public void createOtherPlayerPane(int countMembers, String username) {
		System.out.println("Members:" + countMembers + "ArrayList: " + otherPlayerPaneList.size());
			
			
			for(int i = otherPlayerPaneList.size(); i < countMembers; i++) {
				
				if (i == 0) {
					OtherPlayerPane otherPlayerPane1 = new OtherPlayerPane(username);
					otherPlayerPaneList.add(otherPlayerPane1);
					otherPlayerPane1.getLblName().setText(username);
					this.add(otherPlayerPane1, 1, 0);
				}
				if (i == 1) {
					OtherPlayerPane otherPlayerPane2 = new OtherPlayerPane(username);
					otherPlayerPaneList.add(otherPlayerPane2);
					otherPlayerPane2.getLblName().setText(username);
					this.add(otherPlayerPane2, 2, 1);
				}
				if (i == 2) {
					OtherPlayerPane otherPlayerPane3 = new OtherPlayerPane(username);
					otherPlayerPaneList.add(otherPlayerPane3);
					otherPlayerPane3.getLblName().setText(username);
					this.add(otherPlayerPane3, 0, 1);
				}
			}
		
		//---> anpassen: 			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
	}
	
	public void clearPlayerPaneList() {
		otherPlayerPaneList.clear();
	}

	public PlayerPane getPlayerPane() {
		return playerPane;
	}

	public void setPlayerPane(PlayerPane playerPane) {
		this.playerPane = playerPane;
	}

	public OtherPlayerPane getOtherPlayerPane() {
		return otherPlayerPane;
	}

	public void setOtherPlayerPane(OtherPlayerPane otherPlayerPane) {
		this.otherPlayerPane = otherPlayerPane;
	}
	
	
}
