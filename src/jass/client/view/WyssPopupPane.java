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

public class WyssPopupPane extends GridPane {
	private JassClientView view;
	
	private Label lblWyss = new Label();
	private Button btnWyss = new Button();
	private Button btnNoWyss = new Button();
	
	public WyssPopupPane() {		
		this.add(lblWyss, 0, 0);
		this.add(btnWyss, 0, 1);
		this.add(btnNoWyss, 0, 2);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblWyss() {
		return lblWyss;
	}

	public void setLblWyss(Label lblWyss) {
		this.lblWyss = lblWyss;
	}

	public Button getBtnWyss() {
		return btnWyss;
	}

	public void setBtnWyss(Button btnWyss) {
		this.btnWyss = btnWyss;
	}

	public Button getBtnNoWyss() {
		return btnNoWyss;
	}

	public void setBtnNoWyss(Button btnNoWyss) {
		this.btnNoWyss = btnNoWyss;
	}
	
}