package dice;

import controllers.Credentials;
import enums.EColor;
import utils.ArrayList;
import utils.ImageView;
import utils.Interfaces.IEventHandlerAble;
import utils.Interfaces.IImageViewAble;
import utils.Logger;

public class Dice implements IImageViewAble, IEventHandlerAble {

	private ArrayList<ADiceSide> diceSides = new ArrayList<>();
	private ADiceSide diceSideDisplaying = null;

	public Dice(ArrayList<EColor> list) {

		for (EColor eColor : list) {

			int index = list.indexOf(eColor);
			this.diceSides.addLast(new DiceSideColor(eColor, index));

		}

		this.diceSides.addLast(new DiceSideWhite());

		new ImageView(this.diceSides.getRandom().getImage(), this);
		getImageView().setWidth(Credentials.INSTANCE.dIcon);

		roll();

	}

	public void roll() {

		this.diceSideDisplaying = this.diceSides.getRandom();
		getImageView().setImage(this.diceSideDisplaying.getImage());

	}

	public ADiceSide getDiceSideDiplaying() {
		return this.diceSideDisplaying;
	}

	public void print() {

		Logger.INSTANCE.log("printing dice");
		Logger.INSTANCE.log("value -> " + this.diceSideDisplaying.getValue());

		if (this.diceSideDisplaying instanceof DiceSideColor) {

			DiceSideColor diceSideColor = (DiceSideColor) this.diceSideDisplaying;
			Logger.INSTANCE.log("color -> " + diceSideColor.getEColor().toString().toLowerCase());

		}

		Logger.INSTANCE.newLine();

	}

	@Override
	public void handleMouseButtonPressed() {

		print();

	}

}
