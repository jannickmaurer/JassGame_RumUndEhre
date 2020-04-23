package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LobbyPane  extends GridPane {
	private JassClientView view;
	
	Button btnJoin = new Button();
	Button btnCreatePlayroom = new Button();
	Button btnLogout = new Button();
	
	public LobbyPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		this.add(btnJoin, 1, 1);
		this.add(btnCreatePlayroom, 2, 1);
		this.add(btnLogout, 3, 1);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
