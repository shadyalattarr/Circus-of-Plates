package backend.world;

import java.util.Stack;

import backend.object.FallingObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithBombStrategy implements IntersectionHandlerStrategy{

    @Override
    public void handleIntersection(FallingObject bomb, GameObject onStick, Circus circus) {
        Stack<GameObject> stack;
        FallingObject plateRemoved;
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
        {
            stack = circus.getClown().getLeftObjStack();
        }
        else
        {
            stack = circus.getClown().getRightObjStack();
        }
        if(stack.size() >0)
        {
            plateRemoved = (FallingObject)stack.pop();
            circus.getControlableObjects().remove(plateRemoved);
            //reuse plate

            plateRemoved.setisVertical(true);
            
            circus.getMovableObjects().add(plateRemoved);
            circus.reuse(plateRemoved);//tala3o fo2
            //reuse it...... want it somewhere wla 7aga ////i think we shouldnt reuse later on
            circus.reuse(bomb);
            circus.setScore(circus.getScore()-1);
            //also maybe if score negative gameover?

        }
        // else
        // {
        //     //gameover or smth
        // }
    }

}
