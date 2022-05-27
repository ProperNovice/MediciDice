package panel;

import controllers.Credentials;
import utils.PanelNumbers;
import utils.Vector2;

public class PanelScore {

	private int score = 0;
	private IconScore iconCapacity = new IconScore();
	private PanelNumbers panelNumbers = null;

	public PanelScore(Vector2 coordinates, int digits) {

		this.iconCapacity.getImageView().relocateTopLeft(coordinates);

		this.panelNumbers = new PanelNumbers(Credentials.INSTANCE.dIcon, digits);
		coordinates.x += Credentials.INSTANCE.dIcon;
		coordinates.x += Credentials.INSTANCE.dGapBetweenComponents.x;
		this.panelNumbers.getListCredentials().coordinatesList = coordinates;
		this.panelNumbers.setNumber(this.score);

	}

	public void setScore(int value) {
		this.score = value;
		this.panelNumbers.setNumber(this.score);
	}

	public void addScore(int value) {
		this.score += value;
		this.panelNumbers.setNumber(this.score);
	}

	public int getScore() {
		return this.score;
	}

}
