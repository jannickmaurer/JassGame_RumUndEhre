package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class StartGamePopupPane extends GridPane {
	private JassClientView view;
	
	private Label lblPointsLimit = new Label();
	private TextField tfPoints = new TextField("2500");
	private Button btnStartGamePopUp = new Button();
	private Button btnBack = new Button();
	
	public StartGamePopupPane() {
		this.add(lblPointsLimit, 0, 0);
		this.add(tfPoints, 0, 1);
		this.add(btnStartGamePopUp, 0, 3);
		this.add(btnBack, 0, 4);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblPointsLimit() {
		return lblPointsLimit;
	}

	public void setLblPointsLimit(Label lblPointsLimit) {
		this.lblPointsLimit = lblPointsLimit;
	}

	public TextField getTfPoints() {
		return tfPoints;
	}

	public void setTfPoints(TextField tfPoints) {
		this.tfPoints = tfPoints;
	}

	public Button getBtnStartGamePopUp() {
		return btnStartGamePopUp;
	}

	public void setBtnStartGamePopUp(Button btnStartGamePopUp) {
		this.btnStartGamePopUp = btnStartGamePopUp;
	}

	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
}
