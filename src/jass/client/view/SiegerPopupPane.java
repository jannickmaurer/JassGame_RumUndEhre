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

public class SiegerPopupPane extends GridPane {
	private JassClientView view;
	
	Label lblWinner = new Label();
	Button btnBack = new Button();
	
	public SiegerPopupPane() {		
		this.add(lblWinner, 0, 0);
		this.add(btnBack, 0, 1);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}