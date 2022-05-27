package gameStates;

import controllers.Model;
import utils.Flow;

public class RoundStart extends AGameState {

	@Override
	public void execute() {

		Flow.INSTANCE.getFlow().addLast(RollDice.class);
		Flow.INSTANCE.getFlow().addLast(ChooseDice.class);
		Flow.INSTANCE.getFlow().addLast(RoundEnd.class);

		if (Model.INSTANCE.phaseCurrentEnded())
			Flow.INSTANCE.getFlow().addFirst(StartNewPhase.class);

		Flow.INSTANCE.proceed();

	}

}
