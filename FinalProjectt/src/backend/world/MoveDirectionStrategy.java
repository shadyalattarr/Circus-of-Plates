package backend.world;

import backend.object.FallingObject;

public interface MoveDirectionStrategy {
    public void directionMove(FallingObject o, ObjectSpeedStrategy speed);
    //either fallingobject sent.. ith its sent
}
