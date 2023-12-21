package backend.world.Movement;

import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public class OscillationStrategy implements MovePatternStrategy {

    @Override
    public void patternMove(FallingObject o,ObjectSpeedStrategy speed) {        
        if(Math.random()>0.5)        
            o.setX(o.getX() +1*speed.getFallingObjectSpeed());
        else
            o.setX(o.getX() - 1*speed.getFallingObjectSpeed());
    }
    

}
