package panel;

import controllers.Credentials;
import utils.PanelNumbers;
import utils.Vector2;

public class PanelDiceCapacity {

	private int capacity = 7;
	private IconCapacity iconCapacity = new IconCapacity();
	private PanelNumbers panelNumbers = null;

	public PanelDiceCapacity() {

		Vector2 coordinates = Credentials.INSTANCE.cPanelDiceCapacity.clone();

		this.iconCapacity.getImageView().relocateTopLeft(coordinates);

		this.panelNumbers = new PanelNumbers(Credentials.INSTANCE.dIcon);
		coordinates.x += Credentials.INSTANCE.dIcon;
		coordinates.x += Credentials.INSTANCE.dGapBetweenComponents.x;
		this.panelNumbers.getListCredentials().coordinatesList = coordinates;
		this.panelNumbers.setNumber(this.capacity);

	}

}
