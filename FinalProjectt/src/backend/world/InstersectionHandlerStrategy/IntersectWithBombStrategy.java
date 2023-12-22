package backend.world.InstersectionHandlerStrategy;

import java.util.Stack;

import backend.object.Bomb;
import backend.object.FallingObject;
import backend.object.Plate;
import backend.object.ObjectOnStick;
import backend.world.Circus;
import backend.world.HeartCounter;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithBombStrategy implements IntersectionHandlerStrategy{

    
    @Override
    public void handleIntersection(FallingObject bomb, GameObject onStick, Circus circus) {
        Stack<GameObject> stack;
        ObjectOnStick plateRemoved;
        HeartCounter hearts = circus.getHeartCounter();
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
            plateRemoved = (ObjectOnStick)stack.pop();
            circus.getControlableObjects().remove(plateRemoved);
            circus.getMovableObjects().remove(bomb);
            hearts.takeDamage();
           // circus.reuse(plateRemoved);//tala3o fo2
            //reuse it...... want it somewhere wla 7aga ////i think we shouldnt reuse later on
            //circus.reuse(bomb);
            circus.setScore(circus.getScore() == 0 ? 0 : circus.getScore() - 100);
            //also maybe if score negative gameover?

        }
        // else
        // {
        //     //gameover or smth
        // }
    }

}
