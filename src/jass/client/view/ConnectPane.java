package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConnectPane extends GridPane {
	private JassClientView view;
	
	Label lblPort = new Label();
	Label lblIP = new Label();
	TextField tfPort = new TextField("1111");
	TextField tfIP = new TextField("127.0.0.1");
	Button btnRun = new Button();
	Button btnPing = new Button();
	Button btnStart = new Button();
	
	public ConnectPane() {
		this.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		
		this.add(lblPort, 1, 1);
		this.add(tfPort, 2, 1);
		this.add(lblIP, 3, 1);
		this.add(tfIP, 4, 1);
		this.add(btnRun, 5, 1);
		this.add(btnPing, 6, 1);
		this.add(btnStart, 7, 1);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
