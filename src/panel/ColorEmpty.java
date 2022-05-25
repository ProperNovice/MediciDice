package panel;

import controllers.Credentials;
import enums.EColor;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public class ColorEmpty implements IImageViewAble {

	public ColorEmpty(EColor eColor) {

		String filePath = "";
		filePath += "numbers/";
		filePath += eColor.toString().toLowerCase();
		filePath += "/empty.png";

		new ImageView(filePath, this);
		getImageView().setWidth(Credentials.INSTANCE.dIcon);

	}

}
