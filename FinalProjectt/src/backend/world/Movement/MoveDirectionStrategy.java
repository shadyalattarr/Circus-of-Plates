package backend.world.Movement;

import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public interface MoveDirectionStrategy {
    public void directionMove(FallingObject o, ObjectSpeedStrategy speed);
    //either fallingobject sent.. ith its sent
}
