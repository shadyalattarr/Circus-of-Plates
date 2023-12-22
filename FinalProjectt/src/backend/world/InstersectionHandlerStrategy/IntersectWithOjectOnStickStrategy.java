package backend.world.InstersectionHandlerStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import backend.object.Clown;
import backend.object.ClownIteratorConcrete;
import backend.object.FallingObject;
import backend.object.ObjectOnStick;
import backend.object.Plate;
import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithOjectOnStickStrategy implements IntersectionHandlerStrategy{

    //////////////////////////////////weeeeeeeeeeeeeeeeeeee want to remove circus
    ////daaroooooooooooooooooooorirrrrrrrrrrrrrrrrrrrrr // dont forgrtyyytttttttttttttttttt
    @Override
    public void handleIntersection(FallingObject fallingPlate, GameObject onStick, Circus circus) {
        //it fell on me fa put it
        Stack<GameObject> stack;
        ObjectOnStick objOnStick = (ObjectOnStick) fallingPlate;
        circus.getMovableObjects().remove(objOnStick);
        circus.getControlableObjects().add(objOnStick);
        objOnStick.setY(onStick.getY() - objOnStick.getHeight());
        objOnStick.setIsCaught(true);
        objOnStick.normalSetX(onStick.getX() + onStick.getWidth()/2 - objOnStick.getWidth()/2);
        
        
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
            stack = circus.getClown().getLeftObjStack();
        else
            stack = circus.getClown().getRightObjStack();
        stack.push(objOnStick);
        checkObjOnStick(stack, circus);
        
    }

    public void checkObjOnStick(Stack<GameObject> stack,Circus circus) {
        ClownIteratorConcrete iterator = new ClownIteratorConcrete(stack);

        if (iterator.getSize() >= 3) {
            int i = 0;
            iterator.setI(stack.size() - 1);
            List<ObjectOnStick> objectsOnStick = new ArrayList<ObjectOnStick>();

            while (i <= 2) {

                ObjectOnStick objOnStick = (ObjectOnStick) iterator.next();
                objectsOnStick.add(objOnStick);
               // System.out.println(objOnStick.getobjOnStickColor());
                i++;
            }
            i = 0;

            if (objectsOnStick.get(0).getColor() == objectsOnStick.get(1).getColor()
                    && objectsOnStick.get(1).getColor() == objectsOnStick.get(2).getColor()) {
                circus.setScore(circus.getScore()+300);
                while (i <= 2) {
                    ObjectOnStick objOnStickRemoved = (ObjectOnStick) stack.pop();
                    circus.getControlableObjects().remove(objOnStickRemoved);
                    i++;
                }

            }

            objectsOnStick.clear();
            //System.out.println("size " + objOnSticks.size());
            // if (iterator.getSize() >= 3) {
            // objOnStick objOnStick1 = (objOnStick) iterator.next();
            // objOnStick objOnStick2 = (objOnStick) iterator.next();
            // }
        }

    }
    
    


}
