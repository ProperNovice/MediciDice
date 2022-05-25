package panel;

import controllers.Credentials;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public abstract class AIcon implements IImageViewAble {

	public AIcon() {

		String filePath = "";
		filePath += getFilePath();
		filePath += ".png";

		new ImageView(filePath, this);
		getImageView().setWidth(Credentials.INSTANCE.dIcon);

	}

	protected abstract String getFilePath();

}
