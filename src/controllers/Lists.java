package controllers;

import dice.Dice;
import enums.EColor;
import panel.ColorPanel;
import utils.ArrayList;
import utils.HashMap;
import utils.ListImageViewAbles;
import utils.Vector2;

public enum Lists {

	INSTANCE;

	public final ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();
	public ListImageViewAbles<Dice> dice;
	public HashMap<EColor, ColorPanel> panelStatisticsColor = new HashMap<>();

	public void instantiate() {

		// dice

		this.dice = new ListImageViewAbles<>();
		this.dice.getListCredentials().coordinatesList = Credentials.INSTANCE.cDice;

		// panel statistics

		this.panelStatisticsColor = new HashMap<>();

		Vector2 cPanelStatistics = Credentials.INSTANCE.cPanelStatistics.clone();
		
		ArrayList<EColor> eColors = new ArrayList<>(EColor.values());
		eColors.shuffle();

		for (EColor eColor : eColors) {

			this.panelStatisticsColor.put(eColor, new ColorPanel(eColor, cPanelStatistics.clone()));

			cPanelStatistics.y += Credentials.INSTANCE.dIcon;
			cPanelStatistics.y += Credentials.INSTANCE.dGapBetweenComponents.y;

		}

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
