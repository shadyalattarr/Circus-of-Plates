package backend.world.InstersectionHandlerStrategy;

import backend.object.FallingObject;
import backend.world.Circus;
import backend.world.HeartCounter;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectwithHeartStrategy implements IntersectionHandlerStrategy{
    @Override
    public void handleIntersection(FallingObject heart, GameObject onStick) {
        Circus circus = Circus.getCircus(null);
        HeartCounter hearts = circus.getHeartCounter();
        hearts.addLife();
        circus.getMovableObjects().remove(heart);
    }

}
