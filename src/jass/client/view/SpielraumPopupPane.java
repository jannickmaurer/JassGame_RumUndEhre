package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SpielraumPopupPane extends GridPane {
	private JassClientView view;
	
	TextField tfSpielraumName = new TextField();
	CheckBox cbTrumpf = new CheckBox();
	CheckBox cbUndeUfe = new CheckBox();
	CheckBox cbObeAbe = new CheckBox();
	CheckBox cbSlalom = new CheckBox();
	CheckBox cbWyss = new CheckBox();
	Button btnCreate = new Button();
	Button btnBack = new Button();
	
	public SpielraumPopupPane() {		
		this.add(tfSpielraumName, 0, 0);
		this.add(cbTrumpf, 0, 1);
		this.add(cbUndeUfe, 0, 2);
		this.add(cbObeAbe, 0, 3);
		this.add(cbSlalom, 0, 4);
		
		HBox h1 = new HBox();
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		h1.setId("HBox");
		h1.getChildren().addAll(cbWyss, spacer1, btnCreate, btnBack);
		
		this.add(h1, 0, 5);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
