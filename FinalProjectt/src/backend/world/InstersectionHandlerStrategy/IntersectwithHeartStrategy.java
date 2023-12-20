package backend.world.InstersectionHandlerStrategy;

import backend.object.FallingObject;
import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectwithHeartStrategy implements IntersectionHandlerStrategy{

    @Override
    public void handleIntersection(FallingObject heart, GameObject onStick, Circus circus) {
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
        {
        }
        else
        {
        }
    }

}
