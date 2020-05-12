package jass.client.view;

import jass.client.model.JassClientModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LobbyPane  extends BorderPane {
	private JassClientView view;
	private JassClientModel model;
	
	Button btnProfil = new Button();
	Button btnConfig = new Button();
	Button btnLogout = new Button();
	
	public LobbyPane() {
		HBox h1 = new HBox();
		h1.setId("HBoxTop");
		h1.getChildren().addAll(btnProfil, btnConfig, btnLogout);
		
		this.setTop(h1);
	}
	
}
