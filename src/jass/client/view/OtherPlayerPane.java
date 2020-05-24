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
	
	private String username;
	private Label lblName = new Label("Name");
    private Label lblPoints = new Label("Punkte");
    private Circle crcPlayer = new Circle();
    private GridPane pointsGrid = new GridPane();
    private Label lblPointsPlayer = new Label("0");
    
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

	public Circle getCrcPlayer() {
		return crcPlayer;
	}

	public void setCrcPlayer(Circle crcPlayer) {
		this.crcPlayer = crcPlayer;
	}

	public GridPane getPointsGrid() {
		return pointsGrid;
	}

	public void setPointsGrid(GridPane pointsGrid) {
		this.pointsGrid = pointsGrid;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
