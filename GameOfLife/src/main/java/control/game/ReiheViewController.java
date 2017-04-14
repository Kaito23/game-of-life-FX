package control.game;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import lombok.Getter;

/**
 * Controller für die View einer Reihe.
 * 
 * @author Robin
 *
 */
public class ReiheViewController {

	/** Die View */
	@Getter
	private HBox view;

	/** Liste aller Zellen anhand ihrer Controller. */
	@Getter
	private ArrayList<ZellViewController> zellViewControllerList;

	/** Standard Controller für die Reihenview. */
	public ReiheViewController() {
		this.view = new HBox();
		this.zellViewControllerList = new ArrayList<ZellViewController>();
	}

	/**
	 * Fügt eine Zelle durch ihren Controller hinzu.
	 * 
	 * @param zelleController
	 *            der controller der Zelle
	 */
	public final void add(final ZellViewController zelleController) {
		zellViewControllerList.add(zelleController);
		this.view.getChildren().add(zelleController.getView());
	}

	/** Säubert das Spielfeld und tötet alle Zellen. */
	public final void saeubern() {
		for (ZellViewController zellViewController : zellViewControllerList) {
			zellViewController.toeten();
		}
	}
}
