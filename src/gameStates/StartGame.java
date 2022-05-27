package gameStates;

import enums.EText;
import utils.Flow;

public class StartGame extends AGameState {

	@Override
	public void execute() {

		EText.START_GAME.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Flow.INSTANCE.executeGameState(StartNewRound.class);

	}

}
