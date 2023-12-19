package backend.object;

import java.util.Stack;

import backend.world.Circus;
import backend.world.IntersectionHandlerStrategy;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithBombStrategy implements IntersectionHandlerStrategy{

    @Override
    public void handleIntersection(GameObject bomb, GameObject onStick, Circus circus) {
        Stack<GameObject> stack;
        GameObject plateRemoved;
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
        {
            stack = circus.getLeftObjStack();
        }
        else
        {
            stack = circus.getRightObjStack();
        }
        if(stack.size() >0)
        {
            plateRemoved = stack.pop();
            circus.getControlableObjects().remove(plateRemoved);
            //reuse plate
            circus.getMovableObjects().add(plateRemoved);
            circus.reuse(plateRemoved);//tala3o fo2
            //reuse it...... want it somewhere wla 7aga
            circus.reuse(bomb);
        }
        // else
        // {
        //     //gameover or smth
        // }
    }

}
