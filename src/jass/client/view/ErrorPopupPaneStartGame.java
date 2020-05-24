package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ErrorPopupPaneStartGame extends GridPane {
	private JassClientView view;
	
	private Label lblErrorStartGame = new Label();
	private Button btnBack = new Button();
	
	public ErrorPopupPaneStartGame() {
		VBox v1 = new VBox();
		v1.setId("VBox");
		v1.getChildren().addAll(lblErrorStartGame, btnBack);
		this.add(v1, 0, 0);
		
		this.setId("popupError");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblErrorStartGame() {
		return lblErrorStartGame;
	}

	public void setLblErrorStartGame(Label lblErrorStartGame) {
		this.lblErrorStartGame = lblErrorStartGame;
	}

	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
}