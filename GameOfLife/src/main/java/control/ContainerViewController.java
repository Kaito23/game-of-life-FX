package control;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.stage.WindowEvent;
import control.game.GitterViewController;
import control.game.ReiheViewController;
import control.game.ZellViewController;
import dataobjects.DataBean;
import fx.ContainerView;
import fx.plain.CreditsView;

/**
 * Controller für die ContainerView. Diese Beinhaltet das Spielfeld mit seinen
 * Kontrollmöglichkeiten.
 * 
 * @author Robin
 *
 */
public class ContainerViewController {

	/** Die View */
	private ContainerView view;
	/** Zustandsboolean für autoplay */
	private static boolean autoplayRunning = false;
	/** Der Executor, der den autoplay-Befehl ausführt */
	private static ExecutorService executor;
	/** Controller für das Spielfeld */
	private GitterViewController gitterViewController;

	/** Das Model */
	private DataBean dataBean;

	/**
	 * Der standard Controller vür die Containerview.
	 * 
	 * @param dataBean
	 *            die Bean mit allen Daten
	 */
	public ContainerViewController(final DataBean dataBean) {
		this.dataBean = dataBean;
		view = new ContainerView();

		ScrollPane scrollPane = new ScrollPane();
		gitterViewController = new GitterViewController(dataBean);
		scrollPane.setContent(gitterViewController.getView());
		view.getContainer().setCenter(scrollPane);

		Button buttonNeu = view.getButtonNeu();
		buttonNeu.setOnAction(event -> {
			StartViewController startViewController = new StartViewController(dataBean);
			startViewController.show();
		});

		Button buttonAlleSaeubern = view.getButtonAlleSaeubern();
		buttonAlleSaeubern.setOnAction(event -> {
			dataBean.saeubern();
		});

		ToggleButton buttonCredits = view.getButtonCredits();
		buttonCredits.setOnAction(event -> {
			if (!buttonCredits.isSelected()) {
				view.getContainer().setCenter(scrollPane);
			} else {
				view.getContainer().setCenter(new CreditsView());
			}
		});

		ToggleButton buttonAutoPlay = view.getButtonAutoPlay();
		buttonAutoPlay.setOnAction(event -> {
			handleAutoPlay(buttonAutoPlay.isSelected());
		});

		view.getButtonNextRound().setOnAction(event -> {
			Bedingungspruefer bedingungspruefer = new Bedingungspruefer(dataBean);
			bedingungspruefer.spielzugDurchfuehren();
		});

		dataBean.getPrimaryStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(final WindowEvent we) {
				handleAutoPlay(false);
			}
		});

		view.getSliderGitterGroesse().valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ArrayList<ReiheViewController> reihenListe = dataBean.getReihenListe();
				for (ReiheViewController reiheController : reihenListe) {
					for (ZellViewController zelleController : reiheController.getZellViewControllerList()) {
						zelleController.getView().setPrefHeight(new_val.intValue());
						zelleController.getView().setPrefWidth(new_val.intValue());
					}
				}
			}
		});

	}

	/**
	 * Startet den AutoPlay-Mode oder beendet ihn.
	 * 
	 * @param autoPlayAusgewaehlt
	 *            true wenn der autoPlay-Modus ausgewählt ist
	 */
	private void handleAutoPlay(final boolean autoPlayAusgewaehlt) {
		if (autoPlayAusgewaehlt) {
			autoplayRunning = true;
			autoPlay();
		} else {
			Platform.runLater(() -> {
				autoplayRunning = false;
				executor.shutdown();
				while (!executor.isTerminated()) {
				}
			});
		}
	}

	/** Spielt automatisch Züge. */
	private void autoPlay() {
		executor = Executors.newFixedThreadPool(FUENF);
		Platform.runLater(() -> {
			Runnable worker = () -> {
				while (autoplayRunning) {
					System.out.println("doing");
					Bedingungspruefer bedingungen = new Bedingungspruefer(dataBean);
					bedingungen.spielzugDurchfuehren();
					try {
						Thread.sleep(SLEEP_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			};
			executor.execute(worker);
		});
	}

	/** Zeigt die View. */
	public void show() {
		view.show(dataBean, gitterViewController);
	}

	/** 1000 */
	private static final int SLEEP_TIME = 1000;
	/** 5 */
	private static final int FUENF = 5;
}
