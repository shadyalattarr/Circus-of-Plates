package backend.world.State;

import backend.world.Circus;
import backend.world.Movement.LeftDiagonalStrategy;
import backend.world.Movement.RightDiagonalStrategy;
import backend.world.Movement.ObjectSpeedStrategy.SpeedFinalSecondsStrategy;

public class Almost implements GameState {

    @Override
    public void stateAction() {
        Circus circus= Circus.getCircus(null);
        circus.getDifficulty().setFallingObjectSpeedStrategy(new SpeedFinalSecondsStrategy());
        circus.getDifficulty().setNumFallingObjPerSecond(5);
        if(Math.random()>0.5)
            circus.getDifficulty().getMovement().setMoveD(new RightDiagonalStrategy());
        else
            circus.getDifficulty().getMovement().setMoveD(new LeftDiagonalStrategy());


    }

}
