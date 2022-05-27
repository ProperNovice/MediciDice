package gameStates;

import utils.Flow;

public class StartNewRound extends AGameState {

	@Override
	public void execute() {

		Flow.INSTANCE.getFlow().addLast(RollDice.class);
		Flow.INSTANCE.getFlow().addLast(ChooseDice.class);
		Flow.INSTANCE.getFlow().addLast(RoundEnd.class);

		Flow.INSTANCE.proceed();

	}

}
