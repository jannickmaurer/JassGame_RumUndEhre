package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProfilPopUpPane extends GridPane {
	private JassClientView view;
	
	Button btnDeleteAccount = new Button();
	
	public ProfilPopUpPane() {
		this.add(btnDeleteAccount, 0, 0);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
