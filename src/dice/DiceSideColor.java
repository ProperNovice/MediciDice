package dice;

import enums.EColor;
import utils.Image;

public class DiceSideColor extends ADiceSide {

	private EColor eColor = null;

	public DiceSideColor(EColor eColor, int value) {

		this.eColor = eColor;
		super.value = value;

		String filePath = "numbers/";
		filePath += this.eColor.toString().toLowerCase();
		filePath += "/";
		filePath += super.value;
		filePath += ".png";
		super.image = new Image(filePath);

	}

	public EColor getEColor() {
		return this.eColor;
	}

}
