package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

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
		
		VBox v1 = new VBox();
		v1.setId("VBox");
		
		HBox h1 = new HBox();
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		h1.setId("HBoxTop");
		h1.getChildren().addAll(btnProfil, spacer1, btnConfig, btnLogout);
		
		listView.setPrefWidth(4000);
		
		HBox h2 = new HBox();
		h2.setId("HBoxRight");
		h2.getChildren().addAll(btnJoin, btnCreatePlayroom);
		
		v1.getChildren().addAll(h1, listView, h2);
		VBox.setVgrow(listView, Priority.ALWAYS);
		this.add(v1, 1, 0);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
