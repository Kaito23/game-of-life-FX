package fx;

import javafx.scene.control.Label;

/**
 * Die View einer Zelle im Spiel.
 * 
 * @author Robin
 */
public class ZellView extends Label {
	/** Erstellt die standardansicht einer toten Zelle. */
	public ZellView() {
		super();
		this.setPrefHeight(SIZE);
		this.setPrefWidth(SIZE);
		this.setPrefSize(SIZE, SIZE);
		this.setStyle("-fx-background-color: white; -fx-border-color: red;");
	}

	/** Setzt den Style zu einer toten Zelle. */
	public final void toeten() {
		this.setStyle("-fx-background-color: white; -fx-border-color: red;");
	}

	/** Setzt den Style zu eiener lebenden Zelle. */
	public final void beleben() {
		this.setStyle("-fx-background-color: black; -fx-border-color: red;");
	}

	/** 30 */
	private static final int SIZE = 30;
}
