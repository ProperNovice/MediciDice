package panel;

import controllers.Credentials;
import enums.ELayerZ;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public abstract class AIcon implements IImageViewAble {

	public AIcon() {

		String filePath = "";
		filePath += getFilePath();
		filePath += ".png";

		new ImageView(filePath, getELayerZ(), this);
		getImageView().setWidth(Credentials.INSTANCE.dIcon);

	}

	protected abstract String getFilePath();

	protected abstract ELayerZ getELayerZ();

}
