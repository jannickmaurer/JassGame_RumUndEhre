//Geschrieben von Samuel David und Jannick Maurer

package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class GameTypePopupPane extends GridPane {
	private JassClientView view;
	
	private Label lblChooseGameType = new Label();
	private Button btnTrumpf = new Button();
	
	public GameTypePopupPane() {		
		this.add(lblChooseGameType, 0, 0);
		this.add(btnTrumpf, 0, 1);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblChooseGameType() {
		return lblChooseGameType;
	}

	public void setLblChooseGameType(Label lblChooseGameType) {
		this.lblChooseGameType = lblChooseGameType;
	}

	public Button getBtnTrumpf() {
		return btnTrumpf;
	}

	public void setBtnTrumpf(Button btnTrumpf) {
		this.btnTrumpf = btnTrumpf;
	}
	
}