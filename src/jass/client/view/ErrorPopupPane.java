package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ErrorPopupPane extends GridPane {
	private JassClientView view;
	
	Label lblError = new Label();
	Button btnBack = new Button();
	
	public ErrorPopupPane() {		
		
		this.add(lblError, 0, 0);
		this.add(btnBack, 0, 1);
		
		this.setId("popupError");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}