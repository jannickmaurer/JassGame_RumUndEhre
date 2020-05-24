//Geschrieben von Samuel David und Jannick Maurer

package jass.client.view;

import java.util.ArrayList;
import java.util.logging.Logger;

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
import jass.commons.ServiceLocator;

public class PlayerPane extends VBox {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	private Label lblName = new Label("Name");
	private Label lblPoints = new Label("Punkte");
	private Circle crcPlayer = new Circle();
	private HBox hboxCards = new HBox();

	private GridPane pointsGrid = new GridPane();
	private Label lblPointsPlayer = new Label("0");
	
	private ArrayList<CardLabel> cardLabels;
	
	Label lb = new Label();

	public PlayerPane() {
		super(); // Always call super-constructor first !!
		logger.info("Set Up Player Pane");
		pointsGrid.add(lblPoints, 0, 0);
		pointsGrid.add(lblPointsPlayer, 2, 0);
		pointsGrid.setAlignment(Pos.CENTER);
		pointsGrid.setHgap(20);
		// Add child nodes
		cardLabels = new ArrayList<>();

		// Add CardLabels for the cards
		for (int i = 0; i < 9; i++) {
			CardLabel cl = new CardLabel();
			cl.setDisable(false);
			hboxCards.getChildren().add(cl);
			hboxCards.setSpacing(10);
			cardLabels.add(cl);
		}

		hboxCards.setId("HBox");
		this.getChildren().addAll(lblName, hboxCards, pointsGrid, lb);
		this.setId("playerArea");

	}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(Label lblName) {
		this.lblName = lblName;
	}

	public void updatePlayerDisplay(HandCards handCards, String playedCard) {
		ArrayList<Card> handCardsList = new ArrayList<>();
		handCardsList = handCards.getHandCards();
		logger.info(handCards.getHandCards().toString());
		logger.info("Update PlayerPane: " + handCardsList );
				
		CardLabel clToRemove = new CardLabel();
		for(CardLabel cl : cardLabels) {
			if(cl.getCardNameAsString().equals(playedCard)) {
				clToRemove = cl;
			}
		}
		cardLabels.remove(clToRemove);
		hboxCards.getChildren().remove(clToRemove);

		for (int i = 0; i < handCardsList.size(); i++) {
			Card card = null;
			CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
			cl.setCard(handCardsList.get(i));
			cl.setCardNameAsString(handCardsList.get(i).toString());
		}
	}
	
	public void updatePlayerDisplay(HandCards handCards) {
		ArrayList<Card> handCardsList = new ArrayList<>();
		ArrayList<Card> playableCardsList = new ArrayList<>();
		handCardsList = handCards.getHandCards();
		logger.info(handCards.getHandCards().toString());
		logger.info("Update PlayerPane: " + handCardsList );

		for (int i = 0; i < handCardsList.size(); i++) {
			Card card = null;
			CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
			cl.setCard(handCardsList.get(i));
			cl.setCardNameAsString(handCardsList.get(i).toString());
		}
	}

	public Label getLblPointsPlayer() {
		return lblPointsPlayer;
	}

	public void setLblPointsPlayer(Label lblPointsPlayer) {
		this.lblPointsPlayer = lblPointsPlayer;
	}

	public ArrayList<CardLabel> getCardLabels() {
		return cardLabels;
	}

	public void setCardLabels(ArrayList<CardLabel> cardLabels) {
		this.cardLabels = cardLabels;
	}

	public Label getLblPoints() {
		return lblPoints;
	}

	public void setLblPoints(Label lblPoints) {
		this.lblPoints = lblPoints;
	}

	public Circle getCrcPlayer() {
		return crcPlayer;
	}

	public void setCrcPlayer(Circle crcPlayer) {
		this.crcPlayer = crcPlayer;
	}

	public HBox getHboxCards() {
		return hboxCards;
	}

	public void setHboxCards(HBox hboxCards) {
		this.hboxCards = hboxCards;
	}

	public GridPane getPointsGrid() {
		return pointsGrid;
	}

	public void setPointsGrid(GridPane pointsGrid) {
		this.pointsGrid = pointsGrid;
	}

	public Label getLb() {
		return lb;
	}

	public void setLb(Label lb) {
		this.lb = lb;
	}
	
}
