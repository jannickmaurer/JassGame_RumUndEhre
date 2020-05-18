package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class StartGamePopupPane extends GridPane {
	private JassClientView view;
	
	Label lblPointsLimit = new Label();
	TextField tfPoints = new TextField("2500");
	Button btnStartGamePopUp = new Button();
	
	public StartGamePopupPane() {
		this.add(lblPointsLimit, 0, 0);
		this.add(tfPoints, 0, 1);
		this.add(btnStartGamePopUp, 0, 3);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
