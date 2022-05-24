package controllers;

import dice.Dice;
import utils.ArrayList;
import utils.ListImageViewAbles;

public enum Lists {

	INSTANCE;

	public final ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();
	public ListImageViewAbles<Dice> dice;

	public void instantiate() {

		// dice

		this.dice = new ListImageViewAbles<>();

	}

	public void saveListsOriginal() {

		for (ArrayList<? extends Object> list : this.lists)
			list.saveOriginal();

	}

	public void loadListsOriginal() {

		for (ArrayList<? extends Object> list : this.lists)
			list.loadOriginal();

	}

}
