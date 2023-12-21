package backend.world.State;

import backend.world.Circus;
import backend.world.Movement.DifficultyStrategy;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedFinalSecondsStrategy;

public class Almost implements GameState {

    @Override
    public void stateAction(Game game) {
        Circus circus= Circus.getCircus();
        circus.setDifficulty(new DifficultyStrategy(new ObjectSpeedFinalSecondsStrategy(), circus.getMovement(), circus.getObjFalling()));
            
    }

}
