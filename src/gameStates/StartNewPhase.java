package gameStates;

import controllers.Model;
import enums.EText;
import utils.Flow;

public class StartNewPhase extends AGameState {

	@Override
	public void execute() {

		Model.INSTANCE.setScoreTotal();
		EText.START_NEW_PHASE.show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.startNewPhase();
		Flow.INSTANCE.proceed();

	}

}
