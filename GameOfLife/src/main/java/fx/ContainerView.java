package fx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import control.game.GitterViewController;
import dataobjects.DataBean;

/**
 * Die Containerview die das Spielfeld und seine Kontrollflächen enthält.
 * 
 * @author Robin
 */
public class ContainerView {

	/** Diese Scene */
	private Scene scene;
	/** Der Kontainer */
	@Getter
	private BorderPane container;

	/** Toggle um die Credits anzuzeigen. */
	@Getter
	private ToggleButton buttonCredits;
	/** Toggle um automatisch züge auszuführen */
	@Getter
	private ToggleButton buttonAutoPlay;
	/** Erstellt ein neues Spielfeld */
	@Getter
	private Button buttonNeu;
	/** Fürhrt die nächste Runde durch */
	@Getter
	private Button buttonNextRound;
	/** Tötet alle Zellen auf dem Spielfeld */
	@Getter
	private Button buttonAlleSaeubern;
	/** Scrollpane, wenn das Spielfeld größer als das Fenster ist */
	private ScrollPane scrollPane = new ScrollPane();
	/** Slider für die Gittergröße */
	@Getter
	private Slider sliderGitterGroesse;

	/** Standard Containerview. */
	public ContainerView() {
		container = new BorderPane();

		buttonNeu = new Button("Neu");
		buttonAlleSaeubern = new Button("Säubern");
		buttonNextRound = new Button("Nächster Zug");
		buttonAutoPlay = new ToggleButton("Auto");

		Label labelGitterGroesse = new Label("Gittergröße");
		sliderGitterGroesse = new Slider();
		buttonCredits = new ToggleButton("Credits");

		sliderGitterGroesse.setMin(GITTER_SLIDER_TICK);
		sliderGitterGroesse.setMax(GITTER_SLIDER_MAX);
		sliderGitterGroesse.setValue(GITTER_SLIDER_MAX);
		sliderGitterGroesse.setMajorTickUnit(GITTER_SLIDER_TICK);
		sliderGitterGroesse.setMinorTickCount(GITTER_SLIDER_TICK);
		sliderGitterGroesse.setBlockIncrement(GITTER_SLIDER_TICK);

		ToolBar toolBar = new ToolBar();
		toolBar.getItems().addAll(buttonNeu, buttonAlleSaeubern, new Separator());
		toolBar.getItems().addAll(buttonNextRound, buttonAutoPlay, new Separator());

		toolBar.getItems().addAll(labelGitterGroesse, sliderGitterGroesse, buttonCredits);
		container.setTop(toolBar);
		container.setCenter(scrollPane);

		scene = new Scene(container, FENSTERBREITE, FENSTERHOEHE);
	}

	/** Zeige die Containerview an */
	public void show(final DataBean dataBean, final GitterViewController gitterViewController) {
		scrollPane.setContent(gitterViewController.getView());
		dataBean.getPrimaryStage().setScene(scene);
		dataBean.getPrimaryStage().show();
	}

	/** Fensterhöhe */
	private static final int FENSTERHOEHE = 600;
	/** Fensterbreite */
	private static final int FENSTERBREITE = 800;
	/** 30 */
	private static final int GITTER_SLIDER_MAX = 30;
	/** 5 */
	private static final int GITTER_SLIDER_TICK = 5;
}
