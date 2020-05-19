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
	
	Label lblWyss = new Label();
	Button btnWyss = new Button();
	Button btnNoWyss = new Button();
	
	public WyssPopupPane() {		
		this.add(lblWyss, 0, 0);
		this.add(btnWyss, 0, 1);
		this.add(btnNoWyss, 0, 2);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}