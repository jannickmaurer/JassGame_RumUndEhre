//Geschrieben von Samuel David

package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SpielraumPopupPane extends GridPane {
	private JassClientView view;
	
	private TextField tfSpielraumName = new TextField();
	private Button btnCreate = new Button();
	private Button btnBack = new Button();
	
	public SpielraumPopupPane() {		
		this.add(tfSpielraumName, 0, 0);
		
		HBox h1 = new HBox();
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		h1.setId("HBox");
		h1.getChildren().addAll(btnCreate, btnBack);
		
		this.add(h1, 0, 1);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public TextField getTfSpielraumName() {
		return tfSpielraumName;
	}

	public void setTfSpielraumName(TextField tfSpielraumName) {
		this.tfSpielraumName = tfSpielraumName;
	}

	public Button getBtnCreate() {
		return btnCreate;
	}

	public void setBtnCreate(Button btnCreate) {
		this.btnCreate = btnCreate;
	}

	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
}
