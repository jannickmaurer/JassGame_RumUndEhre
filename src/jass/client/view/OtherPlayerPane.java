package jass.client.view;

import java.util.ArrayList;

import jass.client.HandCards;
import jass.commons.Card;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class OtherPlayerPane extends VBox {
	private JassClientView view;
	
	String username;
	Label lblName = new Label("Name");
    Label lblPoints = new Label("Punkte");
    Circle crcPlayer = new Circle();
  
    public GridPane pointsGrid = new GridPane();
    public Label lblPointsPlayer = new Label("0");
    
    // Link to player object
//---> anpassen:        private Player player;
    
    public OtherPlayerPane(String username) {
    	super(); // Always call super-constructor first !!
        
    	this.username = username;
    	
        pointsGrid.add(lblPoints, 0,0);
        pointsGrid.add(lblPointsPlayer, 2,0);
        pointsGrid.setAlignment(Pos.CENTER);
        pointsGrid.setHgap(20);
        // Add child nodes
      
        crcPlayer.setRadius(20.0f);
        Image img = new Image("/jass/image/Profile_Symbol.png");
        crcPlayer.setFill(new ImagePattern(img));
        lblName.setVisible(true);
        crcPlayer.setVisible(true);
        pointsGrid.setVisible(true);
        this.getChildren().addAll(lblName, crcPlayer, pointsGrid);
        
        this.setId("playerArea");    
        this.getLblName().setText(this.username);
    }
    
//---> anpassen:           public void setPlayer(Player player) {
//---> anpassen:          	this.player = player;
//---> anpassen:           	updatePlayerDisplay(); // Immediately display the player information
//---> anpassen:           }
    
    public void updatePlayerDisplay() {
    	
		}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(Label lblName) {
		this.lblName = lblName;
	}
	
	public String getUsername() {
		return this.username;
	}

	public Label getLblPoints() {
		return lblPoints;
	}

	public void setLblPoints(Label lblPoints) {
		this.lblPoints = lblPoints;
	}

	public Label getLblPointsPlayer() {
		return lblPointsPlayer;
	}

	public void setLblPointsPlayer(Label lblPointsPlayer) {
		this.lblPointsPlayer = lblPointsPlayer;
	}
	
	
	
	
	
//---> anpassen:            public void updatePointsLabel() {
//---> anpassen:           	lblPointsPlayer.setText(Integer.toString(Integer.parseInt(lblPointsPlayer.getText()) + 1)); 	
//---> anpassen:          }
}
