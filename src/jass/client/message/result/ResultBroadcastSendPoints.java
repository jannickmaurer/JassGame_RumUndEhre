package jass.client.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;
import javafx.application.Platform;

/*
 * Class developed by Jannick
 * Message sent to all members in a playroom
 * Send points from each member to each member
 * Sent everytim a Stich has happened
 */
public class ResultBroadcastSendPoints extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String username;
	private String points;

	public ResultBroadcastSendPoints(String[] content) {
		super(content);
		if(content.length > 1) {
			this.username = content[1];
			this.points = content[2];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.addPoints(username, Integer.parseInt(points));
		controller.getBoard().getTableCards().clearTableCards();
		controller.setPlayerOnTurn(username);
		Platform.runLater(new Runnable() {
			public void run() {
				String win = controller.getView().getLblWinner().getText() + "\n" + username;
				controller.getView().getLblWinner().setText(win);
				controller.getView().getSiegerPopUp().show(controller.getView().getStage());
			}
		});
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}
