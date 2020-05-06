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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
		vPoints.setId("VBox");
		Region spacer1 = new Region();
		Region spacer2 = new Region();
		Region spacer3 = new Region();
		Region spacer4 = new Region();
		Region spacer5 = new Region();
		
		HBox h1 = new HBox();
		h1.setId("HBox");
		h1.getChildren().addAll(lblPlayer, spacer1, lblPoints);
		HBox h2 = new HBox();
		h2.setId("HBox");
		h2.getChildren().addAll(lblPlayer1, spacer2, lblPoints1);
		HBox h3 = new HBox();
		h3.setId("HBox");
		h3.getChildren().addAll(lblPlayer2, spacer3, lblPoints2);
		HBox h4 = new HBox();
		h4.setId("HBox");
		h4.getChildren().addAll(lblPlayer3, spacer4, lblPoints3);
		HBox h5 = new HBox();
		h5.setId("HBox");
		h5.getChildren().addAll(lblPlayer4, spacer5, lblPoints4);
		
		vPoints.getChildren().addAll(h1, h2, h3, h4, h5);
		this.add(vPoints, 0, 0);
		
		VBox vMessage = new VBox();
		vMessage.setId("VBox");
		Region spacer6 = new Region();
		
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
        scrollPane.setContent(txtMessages);
        txtMessages.setWrapText(true);	
        scrollPane.setId("ScrollPane");
        
        HBox h6 = new HBox();
		h6.setId("HBox");
		h6.getChildren().addAll(tfMessage, spacer6, btnSend);

		vMessage.getChildren().addAll(lblChat, scrollPane, h6);
		this.add(vMessage, 2, 0);
		
		crcP1.setCenterX(100.0f);
		crcP1.setCenterY(100.0f);
		crcP1.setRadius(50.0f);
		crcP2.setCenterX(100.0f);
		crcP2.setCenterY(100.0f);
		crcP2.setRadius(50.0f);
		crcP3.setCenterX(100.0f);
		crcP3.setCenterY(100.0f);
		crcP3.setRadius(50.0f);
		crcP4.setCenterX(100.0f);
		crcP4.setCenterY(100.0f);
		crcP4.setRadius(50.0f);
		
		this.add(crcP1, 1, 4);
		this.add(crcP2, 0, 3);
		this.add(crcP3, 2, 3);
		this.add(crcP4, 1, 0);
		
		this.add(btnLeave, 1, 5);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}
	
}
