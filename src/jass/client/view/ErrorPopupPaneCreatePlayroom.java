package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ErrorPopupPaneCreatePlayroom extends GridPane {
	private JassClientView view;
	
	private Label lblErrorCreatePlayroom = new Label();
	private Button btnBack = new Button();
	
	public ErrorPopupPaneCreatePlayroom() {
		VBox v1 = new VBox();
		v1.setId("VBox");
		v1.getChildren().addAll(lblErrorCreatePlayroom, btnBack);
		this.add(v1, 0, 0);
		
		this.setId("popupError");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblErrorCreatePlayroom() {
		return lblErrorCreatePlayroom;
	}

	public void setLblErrorCreatePlayroom(Label lblErrorCreatePlayroom) {
		this.lblErrorCreatePlayroom = lblErrorCreatePlayroom;
	}

	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
}