package jass.client;

import com.sun.media.jfxmedia.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.client.view.JassClientView;
import jass.commons.ServiceLocator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Jass extends Application {
	private JassClientModel model;
	private JassClientController controller;
	private JassClientView view;
	
	private static Jass jassProgram;
	private ServiceLocator serviceLocator;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
    public void init() {
        if (jassProgram == null) {
            jassProgram = this;
        } else {
            Platform.exit();
        }
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new JassClientModel();
		view = new JassClientView(primaryStage, model);
		controller = new JassClientController(model, view);
		
		model.initialize();
		serviceLocator = ServiceLocator.getServiceLocator();
		view.start();
	}
	
	public void stop() {
		
        
    }
	
	protected static Jass getJassProgram() {
        return jassProgram;
    }
	
}
