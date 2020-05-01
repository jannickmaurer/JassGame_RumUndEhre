package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SpielraumPane extends GridPane {
	private JassClientView view;
	
	Button btnLeave = new Button();
	
	public SpielraumPane() {
		this.add(btnLeave, 1, 1);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
