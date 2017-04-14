package fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import fx.plain.NumberTextField;

/**
 * Startup-View for entering number of rows and cols.
 * 
 * @author Robin
 */
public class StartView {

	/** Sie Scene */
	private Scene scene;

	/** Erstellt das Spielfeld */
	@Getter
	private Button buttonErstellen;

	/** Eingabefeld für die Anzahl der Reihen */
	@Getter
	private NumberTextField reihenInput;

	/** Eingabefeld für die Anzahl der Spalten */
	@Getter
	private NumberTextField spaltenInput;

	/** Standard StartView. */
	public StartView() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(ABSTAND);
		grid.setVgap(ABSTAND);
		grid.setPadding(new Insets(INSET, INSET, INSET, INSET));

		Text scenetitle = new Text("GoLFx");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, FONT_SIZE));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label labelRowInput = new Label("Reihen:");
		grid.add(labelRowInput, 0, 1);

		reihenInput = new NumberTextField(DEFAULT_VALUE);
		grid.add(reihenInput, 1, 1);

		Label labelSpaltenCount = new Label("Spalten:");
		grid.add(labelSpaltenCount, 0, 2);

		spaltenInput = new NumberTextField(DEFAULT_VALUE);
		grid.add(spaltenInput, 1, 2);

		buttonErstellen = new Button("Erstellen");

		HBox hbBtn = new HBox(ABSTAND);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(buttonErstellen);
		grid.add(hbBtn, 1, VIER);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, SECHS);

		scene = new Scene(grid, VIEW_SIZE, VIEW_SIZE);
	}

	/**
	 * Zeigt diese View.
	 * 
	 * @param stage
	 *            die Stage
	 */
	public final void show(final Stage stage) {
		stage.setScene(scene);
		stage.show();
	}

	/** 4 */
	private static final int VIER = 4;
	/** 6 */
	private static final int SECHS = 6;
	/** 25 */
	private static final int INSET = 25;
	/** 10 */
	private static final int ABSTAND = 10;
	/** 20 */
	private static final int FONT_SIZE = 20;
	/** 10 */
	private static final int DEFAULT_VALUE = 10;
	/** 300 */
	private static final int VIEW_SIZE = 300;
}
