package control.game;

import java.util.ArrayList;

import lombok.Getter;
import dataobjects.DataBean;
import dataobjects.State;
import dataobjects.Zelle;
import fx.ZellView;

/**
 * Der Controller der ZellView.
 * 
 * @author Robin
 */
public class ZellViewController {
	/** Die View */
	@Getter
	private ZellView view;
	/** Die Zelle (Model) */
	@Getter
	private Zelle zelle;
	/** Anzahl lebender Zellen um Umfeld. */
	@Getter
	private int lebendeZellenAnzahl;
	/** Das Model */
	@Getter
	private DataBean dataBean;

	/**
	 * Initialisiert einen neuen Controller mit einer Zelle.
	 * 
	 * @param dataBean
	 *            die bean mit allen Datan
	 * @param zelle
	 *            die Zelle zu diesem Controller
	 */
	public ZellViewController(final Zelle zelle, final DataBean dataBean) {
		this.zelle = zelle;
		this.dataBean = dataBean;
		view = new ZellView();

		view.setOnMouseClicked(event -> {
			zahleLebendeZellen();
			switch (zelle.getZustand()) {
			case LEBEN:
				zelle.kill();
				view.toeten();
				break;
			case TOT:
				zelle.live();
				view.beleben();
				break;
			default:
				throw new NullPointerException(); // TODO
			}
		});
	}

	/** Töte diese Zelle. */
	public final void toeten() {
		this.view.toeten();
		this.zelle.kill();
	}

	/** Belebe diese Zelle. */
	public final void beleben() {
		this.view.beleben();
		this.zelle.live();
	}

	/**
	 * Zählt die lebenden Zellen im Umfeld
	 */
	// TODO void -> int?
	public final void zahleLebendeZellen() {
		lebendeZellenAnzahl = 0;
		int spaltencount = dataBean.getSpaltenAnzahl();
		int reihenanzahl = dataBean.getSpaltenAnzahl();

		int reiheVorherInt = getVorherigeIndex(zelle.getReihe(), reihenanzahl);
		int reiheNachherInt = getNaechsteIndex(zelle.getReihe(), reihenanzahl);

		int spalte = zelle.getSpalte();
		int spalteVorherInt = getVorherigeIndex(spalte, spaltencount);
		int spalteNachherInt = getNaechsteIndex(spalte, spaltencount);

		ArrayList<ZellViewController> reiheBevor = dataBean.getReihenListe().get(reiheVorherInt).getZellViewControllerList();
		ArrayList<ZellViewController> gleicheReihe = dataBean.getReihenListe().get(zelle.getReihe()).getZellViewControllerList();
		ArrayList<ZellViewController> reiheNach = dataBean.getReihenListe().get(reiheNachherInt).getZellViewControllerList();

		Zelle zelleEins = reiheBevor.get(spalteVorherInt).getZelle();
		Zelle zelleZwei = reiheBevor.get(spalte).getZelle();
		Zelle zelleDrei = reiheBevor.get(spalteNachherInt).getZelle();

		Zelle zelleSechs = reiheNach.get(spalteVorherInt).getZelle();
		Zelle zelleSieben = reiheNach.get(spalte).getZelle();
		Zelle zelleAcht = reiheNach.get(spalteNachherInt).getZelle();

		Zelle vor = gleicheReihe.get(spalteVorherInt).getZelle();
		Zelle nach = gleicheReihe.get(spalteNachherInt).getZelle();

		Zelle[] zellenListe = { zelleEins, zelleZwei, zelleDrei, vor, nach, zelleSechs, zelleSieben, zelleAcht };

		for (Zelle nachbarZelle : zellenListe) {
			if (nachbarZelle.getZustand() == State.LEBEN) {
				lebendeZellenAnzahl++;
			}
		}
	}

	/**
	 * Indexnummer der nächsten Reihe oder Spalte.
	 * 
	 * @param index
	 *            der Index der Zelle.
	 * @param max
	 *            die maximale Anzahl Reihen / Spalten
	 * @return die nächste Indexnummer
	 */
	private int getNaechsteIndex(final int index, final int max) {
		int naechste = 0;
		if (index == max - 1) {
			naechste = 0;
		} else {
			naechste = index + 1;
		}
		return naechste;
	}

	/**
	 * Indexnummer der vorherigen Reihe oder Spalte.
	 * 
	 * @param index
	 *            der Index der Zelle.
	 * @param max
	 *            die maximale Anzahl Reihen / Spalten
	 * @return die vorherige Indexnummer
	 */
	private int getVorherigeIndex(final int index, final int max) {
		int vorherige = 0;
		if (index == 0) {
			vorherige = max - 1;
		} else {
			vorherige = index - 1;
		}
		return vorherige;
	}

}
