package backend.world.Movement;

import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public class NoOscillationStrategy implements MovePatternStrategy {

    @Override
    public void patternMove(FallingObject o,ObjectSpeedStrategy gameSpeed) {
        return;//no os        
    }

}
