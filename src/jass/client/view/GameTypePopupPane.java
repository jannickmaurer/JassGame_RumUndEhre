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
	
	Label lblChooseGameType = new Label();
	Button btnTrumpf = new Button();
	Button btnUndeUfe = new Button();
	Button btnObeAbe = new Button();
	Button btnSlalomUndeUfe = new Button();
	Button btnSlalomObeAbe = new Button();
	
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
	
}