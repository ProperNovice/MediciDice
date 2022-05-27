package gameStates;

import controllers.Model;
import dice.Dice;
import enums.EText;
import utils.Flow;
import utils.SelectImageViewManager;

public class ChooseDice extends AGameState {

	@Override
	public void execute() {
		showText();
	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.scoreDice();
		Flow.INSTANCE.proceed();

	}

	@Override
	public void handleDicePressed(Dice dice) {

		dice.reverseSelectImageView();
		showText();

	}

	private void showText() {

		concealText();
		EText.CHOOSE_DICE.show();

		int diceSelected = SelectImageViewManager.INSTANCE.sizeSelectImageViewAbles();

		if (diceSelected < 1)
			return;

		else if (diceSelected > Model.INSTANCE.getDiceCanBeChosen())
			return;

		EText.CONTINUE.show();

	}

}
