package controllers;

import dice.Dice;
import enums.EColor;
import utils.ArrayList;

public enum InstantiateComponents {

	INSTANCE;

	public void instantiate() {

		dice();

	}

	private void dice() {

		ArrayList<EColor> eColors = new ArrayList<>(EColor.values());

		for (int counter = 1; counter <= 5; counter++) {

			Lists.INSTANCE.dice.getArrayList().addLast(new Dice(eColors));
			eColors.addLast(eColors.removeFirst());

		}

		Lists.INSTANCE.dice.getArrayList().shuffle();
		Lists.INSTANCE.dice.relocateImageViews();

	}

}
