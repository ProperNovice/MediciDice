package gameStates;

import controllers.Model;
import enums.EText;
import utils.Flow;

public class RollDice extends AGameState {

	@Override
	public void execute() {

		EText.ROLL_DICE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.rollDice();
		Flow.INSTANCE.proceed();
		
	}

}
