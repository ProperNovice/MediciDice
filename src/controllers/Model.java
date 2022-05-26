package controllers;

import enums.EColor;
import panel.PanelDiceCapacity;
import panel.PanelDiceColor;
import panel.PanelScore;
import utils.ArrayList;
import utils.HashMap;
import utils.Vector2;

public enum Model {

	INSTANCE;

	private HashMap<EColor, PanelDiceColor> panelColor = new HashMap<>();
	private PanelDiceCapacity panelDiceCapacity = new PanelDiceCapacity();
	private PanelScore panelScore = new PanelScore();

	private Model() {

		// panel color

		this.panelColor = new HashMap<>();

		Vector2 cPanelStatistics = Credentials.INSTANCE.cPanelDiceColor.clone();

		ArrayList<EColor> eColors = new ArrayList<>(EColor.values());
		eColors.shuffle();

		for (EColor eColor : eColors) {

			this.panelColor.put(eColor, new PanelDiceColor(eColor, cPanelStatistics.clone()));

			cPanelStatistics.y += Credentials.INSTANCE.dIcon;
			cPanelStatistics.y += Credentials.INSTANCE.dGapBetweenComponents.y;

		}

	}

}
