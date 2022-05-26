package panel;

import controllers.Credentials;
import utils.PanelNumbers;
import utils.Vector2;

public class PanelScore {

	private int score = 0;
	private IconScore iconCapacity = new IconScore();
	private PanelNumbers panelNumbers = null;

	public PanelScore() {

		Vector2 coordinates = Credentials.INSTANCE.cPanelScore.clone();

		this.iconCapacity.getImageView().relocateTopLeft(coordinates);

		this.panelNumbers = new PanelNumbers(Credentials.INSTANCE.dIcon);
		coordinates.x += Credentials.INSTANCE.dIcon;
		coordinates.x += Credentials.INSTANCE.dGapBetweenComponents.x;
		this.panelNumbers.getListCredentials().coordinatesList = coordinates;
		this.panelNumbers.setNumber(this.score);

	}

}
