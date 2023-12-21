package backend.world.Movement;

import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public class DownOnlyStrategy implements MoveDirectionStrategy{

    @Override
    public void directionMove(FallingObject o,ObjectSpeedStrategy speed) {
        o.setY((o.getY()+1*speed.getFallingObjectSpeed()));        
    }

}
