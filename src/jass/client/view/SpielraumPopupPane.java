package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class SpielraumPopupPane extends GridPane {
	private JassClientView view;
	
	TextField tfSpielraumName = new TextField();
	ToggleGroup group = new ToggleGroup();
	RadioButton rbTrumpf = new RadioButton();
	RadioButton rbUndeUfe = new RadioButton();
	RadioButton rbObeAbe = new RadioButton();
	RadioButton rbSlalom = new RadioButton();
	CheckBox cbWyss = new CheckBox();
	Button btnCreate = new Button();
	
	public SpielraumPopupPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		this.add(tfSpielraumName, 0, 0);
		rbTrumpf.setToggleGroup(group);
		rbUndeUfe.setToggleGroup(group);
		rbObeAbe.setToggleGroup(group);
		rbSlalom.setToggleGroup(group);
		this.add(rbTrumpf, 0, 1);
		this.add(rbUndeUfe, 0, 2);
		this.add(rbObeAbe, 0, 3);
		this.add(rbSlalom, 0, 4);
		this.add(cbWyss, 0, 5);
		this.add(btnCreate, 2, 5);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
