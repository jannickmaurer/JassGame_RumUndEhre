package jass.client.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardLabel extends Label {
	public CardLabel() {
		super();
	}

	public void setCard(Card card) {			
			Image image = new Image("/jass/image/Rückseite.jpg");
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
		return rank + "_of_" + suit + ".jpg";
	}

}
