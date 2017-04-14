package fx.plain;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Zeigt Daten über den Autor dieses Programms.
 * 
 * @author Robin
 */
public class CreditsView extends GridPane {

	/** Erstellt eine View mit Informationen über den Autor. */
	public CreditsView() {
		this.setAlignment(Pos.CENTER);
		this.setHgap(ABSTAND);
		this.setVgap(ABSTAND);
		this.setPadding(new Insets(INSETS, INSETS, INSETS, INSETS));

		Text scenetitle = new Text("GoLFx");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, FONT_SIZE));
		this.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Robin Kötter:");
		this.add(userName, 0, 1);

		Label labelMail = new Label("Mail: robinkotter@yahoo.de:");
		this.add(labelMail, 0, 2);
	}

	/** 10 */
	private static final int ABSTAND = 10;

	/** 25 */
	private static final int INSETS = 25;

	/** 20 */
	private static final int FONT_SIZE = 20;
}
