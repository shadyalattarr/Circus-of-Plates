package backend.world;

import backend.object.FallingObject;

public class DownStrategy implements MoveDirectionStrategy{

    @Override
    public void directionMove(FallingObject o,ObjectSpeedStrategy speed) {
        o.setY((o.getY() + 1*speed.getFallingObjectSpeed()));
        
    }

}
