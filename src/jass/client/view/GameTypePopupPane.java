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
	private Button btnUndeUfe = new Button();
	private Button btnObeAbe = new Button();
	private Button btnSlalomUndeUfe = new Button();
	private Button btnSlalomObeAbe = new Button();
	
	public GameTypePopupPane() {		
		this.add(lblChooseGameType, 0, 0);
		this.add(btnTrumpf, 0, 1);
		this.add(btnUndeUfe, 0, 2);
		this.add(btnObeAbe, 0, 3);
		this.add(btnSlalomUndeUfe, 0, 4);
		this.add(btnSlalomObeAbe, 0, 5);
		
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

	public Button getBtnUndeUfe() {
		return btnUndeUfe;
	}

	public void setBtnUndeUfe(Button btnUndeUfe) {
		this.btnUndeUfe = btnUndeUfe;
	}

	public Button getBtnObeAbe() {
		return btnObeAbe;
	}

	public void setBtnObeAbe(Button btnObeAbe) {
		this.btnObeAbe = btnObeAbe;
	}

	public Button getBtnSlalomUndeUfe() {
		return btnSlalomUndeUfe;
	}

	public void setBtnSlalomUndeUfe(Button btnSlalomUndeUfe) {
		this.btnSlalomUndeUfe = btnSlalomUndeUfe;
	}

	public Button getBtnSlalomObeAbe() {
		return btnSlalomObeAbe;
	}

	public void setBtnSlalomObeAbe(Button btnSlalomObeAbe) {
		this.btnSlalomObeAbe = btnSlalomObeAbe;
	}
	
}