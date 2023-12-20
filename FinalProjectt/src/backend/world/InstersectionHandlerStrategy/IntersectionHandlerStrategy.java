package backend.world.InstersectionHandlerStrategy;

import backend.object.FallingObject;
import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

public interface IntersectionHandlerStrategy {

    public void handleIntersection(FallingObject fallingObject, GameObject onStick,Circus circus);

    

}
