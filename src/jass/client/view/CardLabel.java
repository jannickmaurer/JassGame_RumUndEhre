package jass.client.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jass.commons.Card;

public class CardLabel extends Label {
	public String cardNameAsString;
	
	public CardLabel() {
		super();
	}

	public void setCard(Card card) {
		String fileName = cardToFileName(card);
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("/jass/image/" + fileName));
		ImageView imv = new ImageView(image);
		imv.setFitWidth(60);
		imv.maxWidth(60);
		imv.setFitHeight(86);
		imv.maxHeight(86);
		imv.setPreserveRatio(true);
		this.setGraphic(imv);
	}

	private String cardToFileName(Card card) {
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
