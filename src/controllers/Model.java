package controllers;

import dice.ADiceSide;
import dice.Dice;
import dice.DiceSideColor;
import enums.EColor;
import gameStates.AGameState;
import gameStates.EndGameLost;
import gameStates.EndGameWon;
import panel.PanelDiceCapacity;
import panel.PanelDiceColor;
import panel.PanelScore;
import phases.APhase;
import phases.PhaseI;
import phases.PhaseII;
import phases.PhaseIII;
import utils.ArrayList;
import utils.Flow;
import utils.HashMap;
import utils.SelectImageViewManager;
import utils.Vector2;

public enum Model {

	INSTANCE;

	private HashMap<EColor, PanelDiceColor> panelDiceColor = new HashMap<>();
	private ArrayList<APhase> phases = new ArrayList<>();
	private PanelDiceCapacity panelDiceCapacity = new PanelDiceCapacity();
	private PanelScore panelScoreTotal = new PanelScore(Credentials.INSTANCE.cPanelScoreTotal, 0);
	private PanelScore panelScoreCurrentRound = new PanelScore(
			Credentials.INSTANCE.cPanelScoreCurrentRound, 2);

	public void startNewGame() {

		// panel dice colors

		for (EColor eColor : EColor.values())
			this.panelDiceColor.getValue(eColor).clearPanels();

		// phases

		this.phases.loadOriginal();

		// panel dice capacity

		this.panelDiceCapacity.setCapacity(this.phases.getFirst().getDiceCapacity());

		// panel score total

		this.panelScoreTotal.setScore(0);

		// panel score current round

		this.panelScoreCurrentRound.setScore(0);

	}

	public void handleGameEnded() {

		Class<? extends AGameState> gameState = null;

		if (this.panelScoreTotal.getScore() >= 200)
			gameState = EndGameWon.class;
		else
			gameState = EndGameLost.class;

		Flow.INSTANCE.executeGameState(gameState);

	}

	public boolean gameEnded() {
		return this.phases.size() == 1;
	}

	public void setScoreTotal() {

		int score = 0;

		// current score

		int currentScore = this.panelScoreCurrentRound.getScore();

		if (currentScore >= 20)
			score += currentScore;

		if (currentScore >= 30)
			score += 10;

		this.panelScoreTotal.addScore(score);

		// monopolies

		for (EColor eColor : this.panelDiceColor)
			if (this.panelDiceColor.getValue(eColor).getScoreBot() >= 9)
				return;

		score = 0;

		for (EColor eColor : this.panelDiceColor) {

			int humanValue = this.panelDiceColor.getValue(eColor).getScoreHuman();
			int botValue = this.panelDiceColor.getValue(eColor).getScoreBot();

			if (humanValue == 0)
				continue;

			if (humanValue < botValue)
				continue;

			score += 10;

			if (humanValue >= 9)
				score += 10;

			if (humanValue == 12)
				score += 10;

		}

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
			this.panelDiceColor.getValue(eColor).addScoreOneToPlayer();

			if (value != 0)
				continue;

			this.panelDiceColor.getValue(eColor).addScoreOneToPlayer();

		}

		// dice bot

		for (Dice dice : diceBot) {

			ADiceSide diceSide = dice.getDiceSideDiplaying();

			if (!(dice.getDiceSideDiplaying() instanceof DiceSideColor))
				continue;

			int value = diceSide.getValue();

			DiceSideColor diceSideColor = (DiceSideColor) diceSide;
			EColor eColor = diceSideColor.getEColor();
			this.panelDiceColor.getValue(eColor).addScoreOneToBot();

			if (value != 0)
				continue;

			this.panelDiceColor.getValue(eColor).addScoreOneToBot();

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

		this.panelDiceColor = new HashMap<>();

		Vector2 cPanelStatistics = Credentials.INSTANCE.cPanelDiceColor.clone();

		ArrayList<EColor> eColors = new ArrayList<>(EColor.values());
		eColors.shuffle();

		for (EColor eColor : eColors) {

			this.panelDiceColor.put(eColor, new PanelDiceColor(eColor, cPanelStatistics.clone()));

			cPanelStatistics.y += Credentials.INSTANCE.dIcon;
			cPanelStatistics.y += Credentials.INSTANCE.dGapBetweenComponents.y;

		}

		// phases

		this.phases.addLast(new PhaseI());
		this.phases.addLast(new PhaseII());
		this.phases.addLast(new PhaseIII());

		this.phases.saveOriginal();

	}

}
