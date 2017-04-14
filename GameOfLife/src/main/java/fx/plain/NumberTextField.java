package fx.plain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Ein spezielles Textfeld, dass nur Zahlen für die Eingabe zulässt.
 * 
 * @author Robin
 */
public class NumberTextField extends TextField {

	/** Referenz auf sich selbst. */
	private NumberTextField thisTextField;

	/** Initialisiert ein normales Nummerntextfeld. */
	public NumberTextField() {
		thisTextField = this;

		this.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					thisTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	/**
	 * Initialisiert das Nummerntextfeld mit einem übergebenen Wert.
	 * 
	 * @param initialwert
	 *            der Wert der initial in dem Nummernfeld stehen soll.
	 */
	public NumberTextField(final int initialwert) {
		this();
		this.setText(String.valueOf(initialwert));
	}
}
