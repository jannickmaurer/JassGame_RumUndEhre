package jass.client.view;

import java.util.ArrayList;

import jass.client.HandCards;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jass.commons.Card;

public class PlayerPane extends VBox {
	private Label lblName = new Label("Name");
    private Label lblPoints = new Label("Punkte");
    private Circle crcPlayer = new Circle();
    private HBox hboxCards = new HBox();
  
    private GridPane pointsGrid = new GridPane();
    private Label lblPointsPlayer = new Label("0"); 
   
    Rectangle card1 = new Rectangle();
    Rectangle card2 = new Rectangle();
    Rectangle card3 = new Rectangle();
    Rectangle card4 = new Rectangle();
    Rectangle card5 = new Rectangle();
    Rectangle card6 = new Rectangle();
    Rectangle card7 = new Rectangle();
    Rectangle card8 = new Rectangle();
    Rectangle card9 = new Rectangle();
    
    // Link to player object
//---> anpassen:    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        
        pointsGrid.add(lblPoints, 0,0);
        pointsGrid.add(lblPointsPlayer, 2,0);
        pointsGrid.setAlignment(Pos.CENTER);
        pointsGrid.setHgap(20);
        // Add child nodes		
        
        // Add CardLabels for the cards
        for (int i = 0; i < 9; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
            hboxCards.setSpacing(10);
        }
       
        hboxCards.setId("HBox");
        this.getChildren().addAll(lblName, hboxCards, pointsGrid);
        this.setId("playerArea");

    }
    
  //---> anpassen:        public void setPlayer(Player player) {
  //---> anpassen:        	this.player = player;
  //---> anpassen:        	updatePlayerDisplay(); // Immediately display the player information
  //---> anpassen:        }
    
       public void updatePlayerDisplay(HandCards handCards) {
  //---> anpassen:        	lblName.setText(player.getPlayerName());
    	   ArrayList<Card> handCardsList = new ArrayList<>();
    	   handCardsList = handCards.getHandCards();
    	   
      	  for (Card c : handCardsList) {   
       		CardLabel cl = new CardLabel();
       		cl.setCard(c);
       		cl.setCardNameAsString(c.toString());
       		hboxCards.getChildren().add(cl);
       	}
       
       }
    
  //---> anpassen:        public void updatePointsLabel() {
  //---> anpassen:        	lblPointsPlayer.setText(Integer.toString(Integer.parseInt(lblPointsPlayer.getText()) + 1)); 	
  //---> anpassen:        }
}
