package gameStates;

import controllers.Model;
import enums.EText;
import utils.Flow;

public class RoundEnd extends AGameState {

	@Override
	public void execute() {

		if (Model.INSTANCE.phaseCurrentEnded()) {

			EText.PHASE_ENDED_SCORING.show();
			EText.CONTINUE.show();

		} else {

			Flow.INSTANCE.getFlow().addLast(StartNewRound.class);
			Flow.INSTANCE.proceed();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.setScoreTotal();

		if (Model.INSTANCE.gameEnded()) {

			Model.INSTANCE.handleGameEnded();
			Flow.INSTANCE.proceed();
			return;

		} else
			Flow.INSTANCE.getFlow().addLast(StartNewPhase.class);

		Flow.INSTANCE.getFlow().addLast(StartNewRound.class);
		Flow.INSTANCE.proceed();

	}

}
