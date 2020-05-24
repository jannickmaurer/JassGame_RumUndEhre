//Geschrieben von Samuel David

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
	
	private Label lblPort = new Label();
	private Label lblIP = new Label();
	private TextField tfPort = new TextField("1111");
	private TextField tfIP = new TextField("127.0.0.1");
	private Button btnRun = new Button();
	private Button btnStart = new Button();
	
	public ConnectPane() {
		btnStart.setDisable(true);
		this.add(lblPort, 1, 1);
		this.add(tfPort, 2, 1);
		this.add(lblIP, 3, 1);
		this.add(tfIP, 4, 1);
		this.add(btnRun, 5, 1);
		this.add(btnStart, 6, 1);
		
		this.setId("root");
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Label getLblPort() {
		return lblPort;
	}

	public void setLblPort(Label lblPort) {
		this.lblPort = lblPort;
	}

	public Label getLblIP() {
		return lblIP;
	}

	public void setLblIP(Label lblIP) {
		this.lblIP = lblIP;
	}

	public TextField getTfPort() {
		return tfPort;
	}

	public void setTfPort(TextField tfPort) {
		this.tfPort = tfPort;
	}

	public TextField getTfIP() {
		return tfIP;
	}

	public void setTfIP(TextField tfIP) {
		this.tfIP = tfIP;
	}

	public Button getBtnRun() {
		return btnRun;
	}

	public void setBtnRun(Button btnRun) {
		this.btnRun = btnRun;
	}

	public Button getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(Button btnStart) {
		this.btnStart = btnStart;
	}
	
}

