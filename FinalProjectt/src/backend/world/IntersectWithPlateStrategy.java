package backend.world;

import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithPlateStrategy implements IntersectionHandlerStrategy{

    @Override
    public void handleIntersection(GameObject plate, GameObject onStick, Circus circus) {
        //it fell on me fa put it
        circus.getMovableObjects().remove(plate);
        circus.getControlableObjects().add(plate);
        plate.setX(onStick.getX() + onStick.getWidth()/2 - plate.getWidth()/2);
        
        
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
            {
                circus.getLeftObjStack().push(plate);
            }
        else
            circus.getRightObjStack().push(plate);
        
        plate.setY(onStick.getY() - plate.getHeight());//can we make plate height and width static?

    }
    


}
