package backend.world.Movement;

import backend.object.FallingObject;
import backend.world.Movement.MoveDirectionStrategy;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public class RightDiagonalStrategy implements MoveDirectionStrategy{

    @Override
    public void directionMove(FallingObject o, ObjectSpeedStrategy speed) {
        o.setY((o.getY()+1*speed.getFallingObjectSpeed()));
        o.setX(o.getX() +1*speed.getFallingObjectSpeed()/2);
    }

}
