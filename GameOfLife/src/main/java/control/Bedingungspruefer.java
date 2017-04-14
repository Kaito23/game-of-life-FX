package control;

import java.util.ArrayList;

import control.game.ReiheViewController;
import control.game.ZellViewController;
import dataobjects.DataBean;
import dataobjects.State;

/**
 * Der Bedingungsprüfer dient der Prüfung der Bedingungen in einem Spielzug.
 * 
 * @author Robin
 */
public class Bedingungspruefer {

	/** Das Model */
	private DataBean dataBean;

	/**
	 * Erstellt einen normalen Bedingungsprüfer.
	 * 
	 * @param dataBean
	 *            die Bean mit allen nötigen Daten
	 */
	public Bedingungspruefer(final DataBean dataBean) {
		this.dataBean = dataBean;
	}

	/** Führt einen Spielzug durch */
	public final void spielzugDurchfuehren() {
		ArrayList<ReiheViewController> reihenListe = dataBean.getReihenListe();

		// Schritt 1: Zähle die Lebenden Nachbarzellen jeder Zelle
		for (ReiheViewController reiheContoller : reihenListe) {
			for (ZellViewController zelleController : reiheContoller.getZellViewControllerList()) {
				zelleController.zahleLebendeZellen();
			}
		}

		// Schritt 2: Passe den Status jeder Zelle anhand der Spielregeln an
		for (ReiheViewController reiheContoller : reihenListe) {
			for (ZellViewController zelleController : reiheContoller.getZellViewControllerList()) {
				pruefe(zelleController);
			}
		}
	}

	/**
	 * Prüft die Bedingungen und ändert den Zustand der Zelle.
	 * 
	 * @param zellViewController
	 *            prüft den Stand dieser Zelle durch ihren Controller.
	 */
	private void pruefe(final ZellViewController zellViewController) {
		int lebendeZellenCount = zellViewController.getLebendeZellenAnzahl();
		if (zellViewController.getZelle().getZustand() == State.LEBEN) {
			// 1) Jede lebende Zelle, die weniger als 2 lebende Nachbarzellen
			// hat, stirbt
			if (lebendeZellenCount < 2) {
				zellViewController.toeten();
			}

			// 2) Jede lebende Zelle, die 2 oder 3 lebendige Nachbarzellen hat,
			// lebt weiter
			if (lebendeZellenCount == 2 || lebendeZellenCount == DREI) {
				// do nothing
			}

			// 3) Jede lebende Zelle, die mehr als 3 lebende Nachbarzellen hat,
			// stirbt
			if (lebendeZellenCount > DREI) {
				zellViewController.toeten();
			}

		} else {
			// 4) Jede tote Zelle, die genau 3 lebende Nachbarzellen hat, wird
			// lebendig
			if (lebendeZellenCount == DREI) {
				zellViewController.beleben();
			}
		}
	}

	/** 3 */
	private static final int DREI = 3;
}
