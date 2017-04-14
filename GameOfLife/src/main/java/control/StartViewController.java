package control;

import dataobjects.DataBean;
import fx.StartView;

/**
 * Controller der StartView.
 * 
 * @author Robin
 */
public class StartViewController {

	/** Das Model */
	private DataBean dataBean;

	/** Die view */
	private StartView view;

	/**
	 * Standard Controller für die StartView.
	 * 
	 * @param dataBean
	 */
	public StartViewController(final DataBean dataBean) {
		this.dataBean = dataBean;
		view = new StartView();
		view.getButtonErstellen().setOnAction(event -> {
			String spaltenanzahlString = view.getSpaltenInput().getText();
			String reihenanzahlString = view.getReihenInput().getText();
			int spaltenanzahl = Integer.parseInt(spaltenanzahlString);
			int reihenanzahl = Integer.parseInt(reihenanzahlString);

			dataBean.setReihenAnzahl(reihenanzahl);
			dataBean.setSpaltenAnzahl(spaltenanzahl);

			ContainerViewController containerViewController = new ContainerViewController(dataBean);
			containerViewController.show();
		});

	}

	/** Zeigt die primäre Stage */
	public void show() {
		view.show(dataBean.getPrimaryStage());
	}

}
