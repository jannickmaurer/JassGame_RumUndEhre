package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LobbyPane  extends GridPane {
	private JassClientView view;
	
	Button btnProfil = new Button();
	Button btnConfig = new Button();
	ListView<String> listView = new ListView<>();
	Button btnJoin = new Button();
	Button btnCreatePlayroom = new Button();
	Button btnLogout = new Button();
	
	public LobbyPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		HBox h1 = new HBox();
		h1.setId("HBox");
		h1.getChildren().addAll(btnProfil, btnConfig, btnLogout);
		
		this.add(h1, 1, 0);
		
		this.add(listView, 1, 1);
		
		HBox h2 = new HBox();
		h2.setId("HBox");
		h2.getChildren().addAll(btnJoin, btnCreatePlayroom);
		
		this.add(h2, 1, 2);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
