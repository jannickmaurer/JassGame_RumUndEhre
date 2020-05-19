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
	
	Label lblChooseTrumpf = new Label();
	Button btnHearts = new Button();
	Button btnDiamonds = new Button();
	Button btnSpades = new Button();
	Button btnClubs = new Button();
	Button btnPush = new Button();
	
	public TrumpfPopupPane() {		
		this.add(lblChooseTrumpf, 0, 0);
		this.add(btnHearts, 0, 1);
		this.add(btnDiamonds, 0, 2);
		this.add(btnSpades, 0, 3);
		this.add(btnClubs, 0, 4);
		this.add(btnPush, 0, 5);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}