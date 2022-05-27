package panel;

import controllers.Credentials;
import utils.Interfaces.ISaveLoadStateAble;
import utils.PanelNumbers;
import utils.Vector2;

public class PanelScore implements ISaveLoadStateAble {

	private int scoreCurrent = 0, scoreState = 0;
	private IconScore iconCapacity = new IconScore();
	private PanelNumbers panelNumbers = null;

	public PanelScore(Vector2 coordinates, int digits) {

		this.iconCapacity.getImageView().relocateTopLeft(coordinates);

		this.panelNumbers = new PanelNumbers(Credentials.INSTANCE.dIcon, digits);
		coordinates.x += Credentials.INSTANCE.dIcon;
		coordinates.x += Credentials.INSTANCE.dGapBetweenComponents.x;
		this.panelNumbers.getListCredentials().coordinatesList = coordinates;
		this.panelNumbers.setNumber(this.scoreCurrent);

	}

	public void setScore(int value) {
		this.scoreCurrent = value;
		displayScore();
	}

	public void addScore(int value) {
		this.scoreCurrent += value;
		displayScore();
	}

	public int getScore() {
		return this.scoreCurrent;
	}

	@Override
	public void saveState() {
		this.scoreState = this.scoreCurrent;
		displayScore();
	}

	@Override
	public void loadState() {
		this.scoreCurrent = this.scoreState;
		displayScore();
	}

	private void displayScore() {
		this.panelNumbers.setNumber(this.scoreCurrent);
	}

}
