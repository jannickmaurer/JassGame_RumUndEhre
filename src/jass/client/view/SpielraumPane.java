package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SpielraumPane extends GridPane {
	private JassClientView view;
	
	Label lblPlayer = new Label();
	Label lblPlayer1 = new Label("P1");
	Label lblPlayer2 = new Label("P2");
	Label lblPlayer3 = new Label("P3");
	Label lblPlayer4 = new Label("P4");
	Label lblPoints = new Label();
	Label lblPoints1 = new Label("0");
	Label lblPoints2 = new Label("0");
	Label lblPoints3 = new Label("0");
	Label lblPoints4 = new Label("0");
	
	Label lblChat = new Label();
	TextArea txtMessages = new TextArea();
	TextField tfMessage = new TextField();
    Button btnSend = new Button();
    ScrollPane scrollPane = new ScrollPane();
    
    Circle crcP1 = new Circle();
    Circle crcP2 = new Circle();
    Circle crcP3 = new Circle();
    Circle crcP4 = new Circle();
    
    Rectangle card1 = new Rectangle();
    Rectangle card2 = new Rectangle();
    Rectangle card3 = new Rectangle();
    Rectangle card4 = new Rectangle();
    Rectangle card5 = new Rectangle();
    Rectangle card6 = new Rectangle();
    Rectangle card7 = new Rectangle();
    Rectangle card8 = new Rectangle();
    Rectangle card9 = new Rectangle();
    
    Label lblWait = new Label();
  
	Button btnLeave = new Button();
	Button btnStartGame = new Button();
	
	public SpielraumPane() {
		VBox vPoints = new VBox();
		vPoints.setId("VBoxPoints");
		
		HBox h1 = new HBox();
		h1.setId("HBox");
		h1.getChildren().addAll(lblPlayer, lblPoints);
		HBox h2 = new HBox();
		h2.setId("HBox");
		h2.getChildren().addAll(lblPlayer1, lblPoints1);
		HBox h3 = new HBox();
		h3.setId("HBox");
		h3.getChildren().addAll(lblPlayer2, lblPoints2);
		HBox h4 = new HBox();
		h4.setId("HBox");
		h4.getChildren().addAll(lblPlayer3, lblPoints3);
		HBox h5 = new HBox();
		h5.setId("HBox");
		h5.getChildren().addAll(lblPlayer4, lblPoints4);
		
		vPoints.getChildren().addAll(h1, h2, h3, h4, h5);
		this.add(vPoints, 0, 0);
		
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
		this.add(vMessage, 2, 0);
		
		
		VBox vP3 = new VBox();
		vP3.setId("VBoxP3");
		
		crcP3.setCenterX(100.0f);
		crcP3.setCenterY(100.0f);
		crcP3.setRadius(50.0f);
		crcP3.setFill(Color.rgb(255, 236, 111, 0.8));
		vP3.getChildren().addAll(crcP3);
		this.add(vP3, 1, 0);
		
		VBox vP1 = new VBox();
		vP1.setId("VBoxP1");
		
		HBox hCards = new HBox();
		hCards.setId("HBox");
		
		card1.setWidth(90);
		card1.setHeight(130);
		card1.setArcWidth(5);
		card1.setArcHeight(5);
		card1.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card2.setWidth(90);
		card2.setHeight(130);
		card2.setArcWidth(5);
		card2.setArcHeight(5);
		card2.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card3.setWidth(90);
		card3.setHeight(130);
		card3.setArcWidth(5);
		card3.setArcHeight(5);
		card3.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card4.setWidth(90);
		card4.setHeight(130);
		card4.setArcWidth(5);
		card4.setArcHeight(5);
		card4.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card5.setWidth(90);
		card5.setHeight(130);
		card5.setArcWidth(5);
		card5.setArcHeight(5);
		card5.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card6.setWidth(90);
		card6.setHeight(130);
		card6.setArcWidth(5);
		card6.setArcHeight(5);
		card6.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card7.setWidth(90);
		card7.setHeight(130);
		card7.setArcWidth(5);
		card7.setArcHeight(5);
		card7.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card8.setWidth(90);
		card8.setHeight(130);
		card8.setArcWidth(5);
		card8.setArcHeight(5);
		card8.setFill(Color.rgb(255, 236, 111, 0.8));
		
		card9.setWidth(90);
		card9.setHeight(130);
		card9.setArcWidth(5);
		card9.setArcHeight(5);
		card9.setFill(Color.rgb(255, 236, 111, 0.8));
		
		hCards.getChildren().addAll(card1, card2, card3, card4, card5, card6, card7, card8, card9);
		vP1.getChildren().addAll(hCards);
		this.add(vP1, 0, 2, 3, 1);
		
		VBox vP2 = new VBox();
		vP2.setId("VBoxP2");
		
		crcP2.setCenterX(100.0f);
		crcP2.setCenterY(100.0f);
		crcP2.setRadius(50.0f);
		crcP2.setFill(Color.rgb(255, 236, 111, 0.8));
		vP2.getChildren().addAll(crcP2);
		this.add(vP2, 2, 1);
		
		VBox vP4 = new VBox();
		vP4.setId("VBoxP4");
		
		crcP4.setCenterX(100.0f);
		crcP4.setCenterY(100.0f);
		crcP4.setRadius(50.0f);
		crcP4.setFill(Color.rgb(255, 236, 111, 0.8));
		vP4.getChildren().addAll(crcP4);
		this.add(vP4, 0, 1);
		
		VBox vGame = new VBox();
		vGame.setId("VBoxP4");
		lblWait.setId("LabelWait");
		vGame.getChildren().addAll(lblWait);
		this.add(vGame, 1, 1);
		
		HBox h6 = new HBox();
		h6.setId("HBox");
		h6.getChildren().addAll(btnStartGame, btnLeave);
		
		VBox vControls = new VBox();
		vControls.setId("VBoxP4");
		vControls.getChildren().addAll(h6);
		this.add(vControls, 1, 3);

		GridPane.setHgrow(vP1, Priority.ALWAYS);
		GridPane.setVgrow(vP1, Priority.ALWAYS);
		GridPane.setHgrow(vP2, Priority.ALWAYS);
		GridPane.setVgrow(vP2, Priority.ALWAYS);
		GridPane.setHgrow(vP4, Priority.ALWAYS);
		GridPane.setVgrow(vP4, Priority.ALWAYS);
		GridPane.setHgrow(btnLeave, Priority.ALWAYS);
		GridPane.setVgrow(btnLeave, Priority.ALWAYS);
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
