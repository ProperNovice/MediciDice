package dice;

import utils.Image;

public class ADiceSide {

	protected Image image = null;
	protected int value = -1;

	public final int getValue() {
		return this.value;
	}

	public Image getImage() {
		return this.image;
	}

}
