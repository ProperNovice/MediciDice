package controllers;

import dice.ADiceSide;
import dice.Dice;
import dice.DiceSideColor;
import enums.EColor;
import panel.PanelDiceCapacity;
import panel.PanelDiceColor;
import panel.PanelScore;
import phases.APhase;
import phases.PhaseI;
import phases.PhaseII;
import phases.PhaseIII;
import utils.ArrayList;
import utils.HashMap;
import utils.SelectImageViewManager;
import utils.Vector2;

public enum Model {

	INSTANCE;

	private HashMap<EColor, PanelDiceColor> panelColor = new HashMap<>();
	private PanelDiceCapacity panelDiceCapacity = new PanelDiceCapacity();
	private PanelScore panelScoreTotal = new PanelScore(Credentials.INSTANCE.cPanelScoreTotal, 3);
	private PanelScore panelScoreCurrentRound = new PanelScore(
			Credentials.INSTANCE.cPanelScoreCurrentRound, 2);
	private ArrayList<APhase> phases = new ArrayList<>();

	public void setScoreTotal() {

		boolean botHasNineValue = false;

		for (EColor eColor : this.panelColor)
			if (this.panelColor.getValue(eColor).getScoreBot() >= 9)
				botHasNineValue = true;

		if (botHasNineValue)
			return;

		int score = 0;

		for (EColor eColor : this.panelColor) {

			int humanValue = this.panelColor.getValue(eColor).getScoreHuman();
			int botValue = this.panelColor.getValue(eColor).getScoreBot();

			if (humanValue >= botValue)
				score += 10;

		}

		int currentScore = this.panelScoreCurrentRound.getScore();

		if (currentScore >= 20)
			score += currentScore;

		this.panelScoreTotal.addScore(score);

	}

	public void startNewPhase() {

		this.phases.removeFirst();

		int diceCapacity = this.phases.getFirst().getDiceCapacity();
		this.panelDiceCapacity.setCapacity(diceCapacity);
		this.panelScoreCurrentRound.loadState();

	}

	public boolean phaseCurrentEnded() {
		return this.panelDiceCapacity.getCapacity() == 0;
	}

	public void scoreDice() {

		ArrayList<Dice> diceHuman = new ArrayList<>();
		ArrayList<Dice> diceBot = new ArrayList<>();

		ArrayList<Dice> list = null;

		for (Dice dice : Lists.INSTANCE.dice) {

			if (dice.isSelected())
				list = diceHuman;
			else
				list = diceBot;

			list.addLast(dice);

		}

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		// dice human

		for (Dice dice : diceHuman) {

			ADiceSide diceSide = dice.getDiceSideDiplaying();

			int value = diceSide.getValue();
			this.panelScoreCurrentRound.addScore(value);
			this.panelDiceCapacity.reduceCapacityByOne();

			if (!(dice.getDiceSideDiplaying() instanceof DiceSideColor))
				continue;

			DiceSideColor diceSideColor = (DiceSideColor) diceSide;
			EColor eColor = diceSideColor.getEColor();
			this.panelColor.getValue(eColor).addScoreOneToPlayer();

			if (value != 0)
				continue;

			this.panelColor.getValue(eColor).addScoreOneToPlayer();

		}

		// dice bot

		for (Dice dice : diceBot) {

			ADiceSide diceSide = dice.getDiceSideDiplaying();

			if (!(dice.getDiceSideDiplaying() instanceof DiceSideColor))
				continue;

			int value = diceSide.getValue();

			DiceSideColor diceSideColor = (DiceSideColor) diceSide;
			EColor eColor = diceSideColor.getEColor();
			this.panelColor.getValue(eColor).addScoreOneToBot();

			if (value != 0)
				continue;

			this.panelColor.getValue(eColor).addScoreOneToBot();

		}

	}

	public int getDiceCanBeChosen() {

		int diceCapacity = this.panelDiceCapacity.getCapacity();
		return Math.min(diceCapacity, 3);

	}

	public void rollDice() {

		Lists.INSTANCE.dice.getArrayList().shuffle();

		for (Dice dice : Lists.INSTANCE.dice)
			dice.roll();

		Lists.INSTANCE.dice.relocateImageViews();

	}

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

		// phases

		this.phases.addLast(new PhaseI());
		this.phases.addLast(new PhaseII());
		this.phases.addLast(new PhaseIII());

	}

}
