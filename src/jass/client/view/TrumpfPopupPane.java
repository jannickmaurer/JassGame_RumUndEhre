//Geschrieben von Samuel David

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

public class TrumpfPopupPane extends GridPane {
	private JassClientView view;
	
	private Label lblChooseTrumpf = new Label();
	private Button btnHearts = new Button();
	private Button btnDiamonds = new Button();
	private Button btnSpades = new Button();
	private Button btnClubs = new Button();
	//private Button btnPush = new Button();
	
	public TrumpfPopupPane() {		
		this.add(lblChooseTrumpf, 0, 0);
		this.add(btnHearts, 0, 1);
		this.add(btnDiamonds, 0, 2);
		this.add(btnSpades, 0, 3);
		this.add(btnClubs, 0, 4);
		//this.add(btnPush, 0, 5);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblChooseTrumpf() {
		return lblChooseTrumpf;
	}

	public void setLblChooseTrumpf(Label lblChooseTrumpf) {
		this.lblChooseTrumpf = lblChooseTrumpf;
	}

	public Button getBtnHearts() {
		return btnHearts;
	}

	public void setBtnHearts(Button btnHearts) {
		this.btnHearts = btnHearts;
	}

	public Button getBtnDiamonds() {
		return btnDiamonds;
	}

	public void setBtnDiamonds(Button btnDiamonds) {
		this.btnDiamonds = btnDiamonds;
	}

	public Button getBtnSpades() {
		return btnSpades;
	}

	public void setBtnSpades(Button btnSpades) {
		this.btnSpades = btnSpades;
	}

	public Button getBtnClubs() {
		return btnClubs;
	}

	public void setBtnClubs(Button btnClubs) {
		this.btnClubs = btnClubs;
	}

	//public Button getBtnPush() {
	//	return btnPush;
	//}

	//public void setBtnPush(Button btnPush) {
	//	this.btnPush = btnPush;
	//}
	
}