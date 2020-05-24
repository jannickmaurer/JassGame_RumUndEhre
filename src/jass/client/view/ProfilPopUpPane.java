package jass.client.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProfilPopUpPane extends GridPane {
	private JassClientView view;
	
	private Button btnDeleteAccount = new Button();
	private Button btnBack = new Button();
	
	public ProfilPopUpPane() {
		this.add(btnDeleteAccount, 0, 0);
		this.add(btnBack, 0, 1);
		
		this.setId("popup");
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(20);
		this.setVgap(10);
	}

	public Button getBtnDeleteAccount() {
		return btnDeleteAccount;
	}

	public void setBtnDeleteAccount(Button btnDeleteAccount) {
		this.btnDeleteAccount = btnDeleteAccount;
	}

	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
}
