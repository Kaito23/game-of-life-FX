package dataobjects;

import lombok.Getter;
import lombok.Setter;

/**
 * Eine Zelle.
 * 
 * @author Robin
 */
public class Zelle {

	/** Spaltennummer */
	@Getter
	private int spalte;
	/** Reihennummer */
	@Getter
	private int reihe;

	/** Zustand */
	@Setter
	@Getter
	private State zustand;

	/**
	 * Erstellt eine Zelle, die ihre Position im Gitter kennt.
	 * 
	 * @param spalte
	 *            reihe der Zelle
	 * @param reihe
	 *            spalte der Zelle
	 */
	public Zelle(final int spalte, final int reihe) {
		super();
		this.spalte = spalte;
		this.reihe = reihe;
		this.zustand = State.TOT;
	}

	/** Tötet die Zelle. */
	public final void kill() {
		this.zustand = State.TOT;
	}

	/** Belebt die Zelle wieder. */
	public final void live() {
		this.zustand = State.LEBEN;
	}
}
