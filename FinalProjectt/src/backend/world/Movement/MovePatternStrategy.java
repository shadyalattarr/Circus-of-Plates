package backend.world.Movement;

import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public interface MovePatternStrategy {
    public void patternMove(FallingObject o,ObjectSpeedStrategy gameSpeed);
}
