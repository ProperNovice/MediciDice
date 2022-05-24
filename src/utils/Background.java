package utils;

import enums.ELayerZ;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;

public class Background implements IImageViewAble, IEventHandlerAble {

	public Background(ELayerZ eLayerZ) {
		new ImageView("misc/background.png", eLayerZ, this);
	}

	@Override
	public void handleMouseButtonPressed() {
		ShutDown.INSTANCE.execute();
	}

}
