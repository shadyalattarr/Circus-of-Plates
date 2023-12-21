package backend.world.State;

import backend.world.Circus;
import backend.world.Movement.LeftDiagonalStrategy;
import backend.world.Movement.RightDiagonalStrategy;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedFinalSecondsStrategy;

public class Almost implements GameState {

    @Override
    public void stateAction(Game game) {
        Circus circus= Circus.getCircus();
        circus.getDifficulty().setFallingObjectSpeedStrategy(new ObjectSpeedFinalSecondsStrategy());
        circus.getDifficulty().setNumFallingObjPerSecond(5);
        if(Math.random()>0.5)
            circus.getMovement().setMoveD(new RightDiagonalStrategy());
        else
            circus.getMovement().setMoveD(new LeftDiagonalStrategy());


    }

}
