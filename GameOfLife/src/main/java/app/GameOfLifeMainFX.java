package app;

import javafx.application.Application;
import javafx.stage.Stage;
import control.StartViewController;
import dataobjects.DataBean;

/**
 * Startklasse für das Game of Life.
 * 
 * @author Robin
 */
public class GameOfLifeMainFX extends Application {

	/**
	 * Main.
	 * 
	 * @param args
	 *            an das Programm übegebene Parameter
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	/** Startet die JavaFX Applikation. */
	@Override
	public final void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("GoLFx");

		// Model
		DataBean dataBean = new DataBean(primaryStage);
		// View
		StartViewController startUpViewController = new StartViewController(dataBean);
		// Control
		startUpViewController.show();
	}
}
