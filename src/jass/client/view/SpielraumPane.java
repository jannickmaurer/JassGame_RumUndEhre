package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpielraumPane extends GridPane {
	private JassClientView view;
	
	Label lblPlayer = new Label();
	Label lblPlayer1 = new Label("P1");
	Label lblPlayer2 = new Label("P2");
	Label lblPlayer3 = new Label("P3");
	Label lblPlayer4 = new Label("P4");
	Label lblPoints = new Label();
	Label lblPoints1 = new Label("0");
	Label lblPoints2 = new Label("0");
	Label lblPoints3 = new Label("0");
	Label lblPoints4 = new Label("0");
	
	Label lblChat = new Label();
	TextArea txtMessages = new TextArea();
	TextField tfMessage = new TextField();
    Button btnSend = new Button();
    ScrollPane scrollPane = new ScrollPane();
    
    Circle crcP1 = new Circle();
    Circle crcP2 = new Circle();
    Circle crcP3 = new Circle();
    Circle crcP4 = new Circle();
  
	Button btnLeave = new Button();
	
	public SpielraumPane() {
		VBox vPoints = new VBox();
		vPoints.setId("VBoxPoints");
		
		HBox h1 = new HBox();
		h1.setId("HBox");
		h1.getChildren().addAll(lblPlayer, lblPoints);
		HBox h2 = new HBox();
		h2.setId("HBox");
		h2.getChildren().addAll(lblPlayer1, lblPoints1);
		HBox h3 = new HBox();
		h3.setId("HBox");
		h3.getChildren().addAll(lblPlayer2, lblPoints2);
		HBox h4 = new HBox();
		h4.setId("HBox");
		h4.getChildren().addAll(lblPlayer3, lblPoints3);
		HBox h5 = new HBox();
		h5.setId("HBox");
		h5.getChildren().addAll(lblPlayer4, lblPoints4);
		
		vPoints.getChildren().addAll(h1, h2, h3, h4, h5);
		this.add(vPoints, 0, 0);
		
		VBox vMessage = new VBox();
		vMessage.setId("VBoxMessage");
		
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
        scrollPane.setContent(txtMessages);
        txtMessages.setWrapText(true);	
        scrollPane.setId("ScrollPane");

		vMessage.getChildren().addAll(lblChat, scrollPane, tfMessage, btnSend);
		this.add(vMessage, 2, 0);
		
		
		VBox vP3 = new VBox();
		vP3.setId("VBoxP3");
		
		crcP3.setCenterX(100.0f);
		crcP3.setCenterY(100.0f);
		crcP3.setRadius(50.0f);
		crcP3.setFill(Color.rgb(255, 236, 111, 0.8));
		vP3.getChildren().addAll(crcP3);
		this.add(vP3, 1, 0);
		
		VBox vP1 = new VBox();
		vP1.setId("VBoxP1");
		
		crcP1.setCenterX(100.0f);
		crcP1.setCenterY(100.0f);
		crcP1.setRadius(50.0f);
		crcP1.setFill(Color.rgb(255, 236, 111, 0.8));
		vP1.getChildren().addAll(crcP1);
		this.add(vP1, 1, 4);
		
		VBox vP2 = new VBox();
		vP2.setId("VBoxP2");
		
		crcP2.setCenterX(100.0f);
		crcP2.setCenterY(100.0f);
		crcP2.setRadius(50.0f);
		crcP2.setFill(Color.rgb(255, 236, 111, 0.8));
		vP2.getChildren().addAll(crcP2);
		this.add(vP2, 2, 3);
		
		VBox vP4 = new VBox();
		vP4.setId("VBoxP4");
		
		crcP4.setCenterX(100.0f);
		crcP4.setCenterY(100.0f);
		crcP4.setRadius(50.0f);
		crcP4.setFill(Color.rgb(255, 236, 111, 0.8));
		vP4.getChildren().addAll(crcP4);
		this.add(vP4, 0, 3);
		
		VBox vControls = new VBox();
		vControls.setId("VBoxP4");
		vControls.getChildren().addAll(btnLeave);
		this.add(vControls, 1, 5);
		
		this.setHgrow(vP1, Priority.ALWAYS);
		this.setVgrow(vP1, Priority.ALWAYS);
		this.setHgrow(vP2, Priority.ALWAYS);
		this.setVgrow(vP2, Priority.ALWAYS);
		this.setHgrow(vP4, Priority.ALWAYS);
		this.setVgrow(vP4, Priority.ALWAYS);
		this.setHgrow(btnLeave, Priority.ALWAYS);
		this.setVgrow(btnLeave, Priority.ALWAYS);
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
