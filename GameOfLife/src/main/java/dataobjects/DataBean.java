package dataobjects;

import java.util.ArrayList;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import control.game.ReiheViewController;
import control.game.ZellViewController;

/**
 * Datenverwaltende Klasse.
 * 
 * @author Robin
 */
public class DataBean {

	/** Anzahl der Reihen */
	@Getter
	@Setter
	private int reihenAnzahl;
	/** Anzahl der Spalten */
	@Getter
	@Setter
	private int spaltenAnzahl;

	/** List of all rows */
	@Getter
	private ArrayList<ReiheViewController> reihenListe;

	/** The primary stage */
	@Getter
	private Stage primaryStage;

	/**
	 * Standard Bean.
	 * 
	 * @param primaryStage
	 *            die Primärstage
	 */
	public DataBean(final Stage primaryStage) {
		this.primaryStage = primaryStage;
		reihenListe = new ArrayList<ReiheViewController>();
	}

	/**
	 * Get a specific cell.
	 * 
	 * @param col
	 *            the col of the cell
	 * @param row
	 *            the row of the cell
	 * @return gibt den Controller der Zelle zurück.
	 */
	public final ZellViewController getZelle(final int col, final int row) {
		ReiheViewController reiheViewController = reihenListe.get(row);
		return reiheViewController.getZellViewControllerList().get(col);
	}

	/** Säubert das Spielfeld und tötet alle Zellen. */
	public final void saeubern() {
		for (ReiheViewController reiheViewController : reihenListe) {
			reiheViewController.saeubern();
		}
	}

}
