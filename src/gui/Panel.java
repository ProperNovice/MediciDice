package gui;

import enums.ELayerZ;
import utils.Background;

public class Panel extends Parent {

	public Panel() {

		ParentInstance.INSTANCE.set(this);
		new Background(ELayerZ.BACKGROUND);

	}

}
