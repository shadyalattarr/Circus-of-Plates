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

    @Override
    public void handleIntersection(FallingObject fallingPlate, GameObject onStick, Circus circus) {
        //it fell on me fa put it
        Stack<GameObject> stack;
        ObjectOnStick objOnSticj = (ObjectOnStick) fallingPlate;
        circus.getMovableObjects().remove(objOnSticj);
        circus.getControlableObjects().add(objOnSticj);
        objOnSticj.setY(onStick.getY() - objOnSticj.getHeight());
        objOnSticj.setIsCaught(true);
        objOnSticj.setX(onStick.getX() + onStick.getWidth()/2 - objOnSticj.getWidth()/2);
        
        
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
            stack = circus.getClown().getLeftObjStack();
        else
            stack = circus.getClown().getRightObjStack();
        stack.push(objOnSticj);
        checkobjOnSticj(stack, circus);
        
    }

    public void checkobjOnSticj(Stack<GameObject> stack,Circus circus) {
        ClownIteratorConcrete iterator = new ClownIteratorConcrete(stack);

        if (iterator.getSize() >= 3) {
            int i = 0;
            iterator.setI(stack.size() - 1);
            List<ObjectOnStick> objOnSticjs = new ArrayList<ObjectOnStick>();

            while (i <= 2) {

                ObjectOnStick objOnSticj = (ObjectOnStick) iterator.next();
                objOnSticjs.add(objOnSticj);
               // System.out.println(objOnSticj.getobjOnSticjColor());
                i++;
            }
            i = 0;

            if (objOnSticjs.get(0).getColor() == objOnSticjs.get(1).getColor()
                    && objOnSticjs.get(1).getColor() == objOnSticjs.get(2).getColor()) {
                circus.setScore(circus.getScore()+300);
                while (i <= 2) {
                    ObjectOnStick objOnSticjRemoved = (ObjectOnStick) stack.pop();
                    circus.getControlableObjects().remove(objOnSticjRemoved);
                    i++;
                }

            }

            objOnSticjs.clear();
            //System.out.println("size " + objOnSticjs.size());
            // if (iterator.getSize() >= 3) {
            // objOnSticj objOnSticj1 = (objOnSticj) iterator.next();
            // objOnSticj objOnSticj2 = (objOnSticj) iterator.next();
            // }
        }

    }
    
    


}
