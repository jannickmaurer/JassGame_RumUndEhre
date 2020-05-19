package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
	
	Label lblPlayer = new Label();
	Label lblPlayer1 = new Label("P1");
	Label lblPlayer2 = new Label("P2");
	Label lblPlayer3 = new Label("P3");
	Label lblPlayer4 = new Label("P4");
	Label lblName1 = new Label("P1");
	Label lblName2 = new Label("P2");
	Label lblName3 = new Label("P3");
	Label lblName4 = new Label("P4");
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
    
    Rectangle cardP1 = new Rectangle();
    Rectangle cardP2 = new Rectangle();
    Rectangle cardP3 = new Rectangle();
    Rectangle cardP4 = new Rectangle();
    
    Label lblWait = new Label();
  
	Button btnLeave = new Button();
	Button btnStartGame = new Button();
	
	public SpielraumPane() {
		VBox vPoints = new VBox();
		vPoints.setId("VBoxPoints");
		vPoints.setMinWidth(Region.USE_PREF_SIZE);
		vPoints.setPrefWidth(200);
		
		HBox h1 = new HBox();
		h1.setId("HBoxPoints");
		h1.getChildren().addAll(lblPlayer, lblPoints);
		HBox h2 = new HBox();
		h2.setId("HBoxPoints");
		h2.getChildren().addAll(lblPlayer1, lblPoints1);
		HBox h3 = new HBox();
		h3.setId("HBoxPoints");
		h3.getChildren().addAll(lblPlayer2, lblPoints2);
		HBox h4 = new HBox();
		h4.setId("HBoxPoints");
		h4.getChildren().addAll(lblPlayer3, lblPoints3);
		HBox h5 = new HBox();
		h5.setId("HBoxPoints");
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
		vP3.getChildren().addAll(crcP3, lblName3);
		this.add(vP3, 1, 0);
		
		VBox vP1 = new VBox();
		vP1.setId("VBoxP1");
		
		HBox hCards = new HBox();
		hCards.setId("HBox");
		
		Image img1 = new Image("/jass/image/Rückseite.jpg");
		card1.setId("Card");
		card1.setFill(new ImagePattern(img1));
		card1.setWidth(90);
		card1.setHeight(130);
		card1.setArcWidth(5);
		card1.setArcHeight(5);
		card1.setDisable(false); //muss wieder zurückgestellt werden
		
		card2.setId("Card");
		card2.setWidth(90);
		card2.setHeight(130);
		card2.setArcWidth(5);
		card2.setArcHeight(5);
		Image img2 = new Image("/jass/image/Rückseite.jpg");
		card2.setFill(new ImagePattern(img2));
		card2.setDisable(true);
		
		card3.setId("Card");
		card3.setWidth(90);
		card3.setHeight(130);
		card3.setArcWidth(5);
		card3.setArcHeight(5);
		Image img3 = new Image("/jass/image/Rückseite.jpg");
		card3.setFill(new ImagePattern(img3));
		card3.setDisable(true);
		
		card4.setId("Card");
		card4.setWidth(90);
		card4.setHeight(130);
		card4.setArcWidth(5);
		card4.setArcHeight(5);
		Image img4 = new Image("/jass/image/Rückseite.jpg");
		card4.setFill(new ImagePattern(img4));
		card4.setDisable(true);
		
		card5.setId("Card");
		card5.setWidth(90);
		card5.setHeight(130);
		card5.setArcWidth(5);
		card5.setArcHeight(5);
		Image img5 = new Image("/jass/image/Rückseite.jpg");
		card5.setFill(new ImagePattern(img5));
		card5.setDisable(true);
		
		card6.setId("Card");
		card6.setWidth(90);
		card6.setHeight(130);
		card6.setArcWidth(5);
		card6.setArcHeight(5);
		Image img6 = new Image("/jass/image/Rückseite.jpg");
		card6.setFill(new ImagePattern(img6));
		card6.setDisable(true);
		
		card7.setId("Card");
		card7.setWidth(90);
		card7.setHeight(130);
		card7.setArcWidth(5);
		card7.setArcHeight(5);
		Image img7 = new Image("/jass/image/Rückseite.jpg");
		card7.setFill(new ImagePattern(img7));
		card7.setDisable(true);
		
		card8.setId("Card");
		card8.setWidth(90);
		card8.setHeight(130);
		card8.setArcWidth(5);
		card8.setArcHeight(5);
		Image img8 = new Image("/jass/image/Rückseite.jpg");
		card8.setFill(new ImagePattern(img8));
		card8.setDisable(true);
		
		card9.setId("Card");
		card9.setWidth(90);
		card9.setHeight(130);
		card9.setArcWidth(5);
		card9.setArcHeight(5);
		Image img9 = new Image("/jass/image/Rückseite.jpg");
		card9.setFill(new ImagePattern(img9));
		card9.setDisable(true);
		
		hCards.getChildren().addAll(card1, card2, card3, card4, card5, card6, card7, card8, card9);
		vP1.getChildren().addAll(hCards, lblName1);
		this.add(vP1, 0, 2, 3, 1);
		
		VBox vP2 = new VBox();
		vP2.setId("VBoxP2");
		
		crcP2.setCenterX(100.0f);
		crcP2.setCenterY(100.0f);
		crcP2.setRadius(50.0f);
		crcP2.setFill(Color.rgb(255, 236, 111, 0.8));
		vP2.getChildren().addAll(crcP2, lblName2);
		this.add(vP2, 2, 1);
		
		VBox vP4 = new VBox();
		vP4.setId("VBoxP4");
		
		crcP4.setRadius(50.0f);
		crcP4.setFill(Color.rgb(255, 236, 111, 0.8));
		vP4.getChildren().addAll(crcP4, lblName4);
		this.add(vP4, 0, 1);
		
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
	
		GridPane.setHgrow(vP1, Priority.ALWAYS);
		GridPane.setVgrow(vP1, Priority.ALWAYS);
		GridPane.setHgrow(vP3, Priority.ALWAYS);
		GridPane.setVgrow(vP3, Priority.ALWAYS);
		GridPane.setHgrow(vP2, Priority.ALWAYS);
		GridPane.setVgrow(vP2, Priority.ALWAYS);
		GridPane.setHgrow(vP4, Priority.ALWAYS);
		GridPane.setVgrow(vP4, Priority.ALWAYS);
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
	}
	
}
