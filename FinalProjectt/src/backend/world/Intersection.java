package backend.world;

import backend.object.FallingObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Intersection {
    IntersectionHandlerStrategy intersection;
    Circus circus;

    public IntersectionHandlerStrategy getIntersection() {
        return this.intersection;
    }

    public void setIntersection(IntersectionHandlerStrategy intersection) {
        this.intersection = intersection;
    }

    Intersection(Circus circus)
    {
        this.circus = circus;
    }
    private boolean isIntersect(GameObject fallingObject, GameObject onStick)
    {
        double fallingObjectXCenter = (fallingObject.getX() + fallingObject.getWidth()/2.0);
        double stickXCenter = (onStick.getX() + onStick.getWidth()/2.0);
        int fallingObjectBottomY = fallingObject.getY() + fallingObject.getHeight();
        int stickTop = onStick.getY();
		return ((Math.abs(fallingObjectXCenter - stickXCenter) <= fallingObject.getWidth()/3) && (Math.abs(stickTop- fallingObjectBottomY)<=5));
	}

    public void handleIntersection(FallingObject fallingObject, GameObject onStick)
    {
        if(isIntersect(fallingObject, onStick))
            intersection.handleIntersection(fallingObject, onStick,circus);
    }

}
