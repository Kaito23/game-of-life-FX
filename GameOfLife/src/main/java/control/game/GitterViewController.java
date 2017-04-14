package control.game;

import javafx.scene.layout.VBox;
import lombok.Getter;
import dataobjects.DataBean;
import dataobjects.Zelle;

/**
 * Controller der GitterView. Die Gitterview stellt das Spielfeld dar.
 * 
 * @author Robin
 */
public class GitterViewController {

	/** Die View */
	@Getter
	private VBox view;

	/**
	 * Standard Controller für die Gitteransicht des Spiels.
	 * 
	 * @param dataBean
	 *            die Bean mit allen nötigen Daten.
	 */
	public GitterViewController(final DataBean dataBean) {
		view = new VBox();

		for (int i = 0; i < dataBean.getReihenAnzahl(); i++) {
			ReiheViewController reiheViewController = new ReiheViewController();
			for (int j = 0; j < dataBean.getSpaltenAnzahl(); j++) {
				Zelle zelle = new Zelle(j, i);
				ZellViewController zelleController = new ZellViewController(zelle, dataBean);
				reiheViewController.add(zelleController);
			}
			dataBean.getReihenListe().add(reiheViewController);
			this.view.getChildren().add(reiheViewController.getView());
		}

	}
}
