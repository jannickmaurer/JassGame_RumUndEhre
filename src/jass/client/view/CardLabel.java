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

	private String username;
	public String cardNameAsString;
	
	public CardLabel() {
		super();
	}
	
	public CardLabel(String username) {
		super();
		this.username = username;
	}

	public void setCard(Card card) {
		String fileName = cardToFileName(card);
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("jass/image/" + fileName));
		ImageView imv = new ImageView(image);
		imv.setFitWidth(80);
		imv.maxWidth(80);
		imv.setFitHeight(115);
		imv.maxHeight(115);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
