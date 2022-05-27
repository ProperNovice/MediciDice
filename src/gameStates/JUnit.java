package gameStates;

import utils.Flow;

public class JUnit extends AGameState {

	@Override
	public void execute() {
		
		Flow.INSTANCE.executeGameState(StartGame.class);

	}

}
