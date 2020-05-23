package jass.client.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.ServiceLocator;

public class CardLabel extends Label {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	public String cardNameAsString;
	
	public CardLabel() {
		super();
	}

	public void setCard(Card card) {
		String fileName = cardToFileName(card);
		logger.info("Looking for card: " + fileName);
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("jass/image/" + fileName));
		ImageView imv = new ImageView(image);
		imv.setFitWidth(60);
		imv.maxWidth(60);
		imv.setFitHeight(86);
		imv.maxHeight(86);
		imv.setPreserveRatio(true);
		this.setGraphic(imv);
	}

	private String cardToFileName(Card card) {
		logger.info("Looking for Card: " + card.getRank().toString() + card.getSuit().toString());
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + "_FR" + ".jpg";
	}

	public String getCardNameAsString() {
		return cardNameAsString;
	}

	public void setCardNameAsString(String cardNameAsString) {
		this.cardNameAsString = cardNameAsString;
	}
	
}
