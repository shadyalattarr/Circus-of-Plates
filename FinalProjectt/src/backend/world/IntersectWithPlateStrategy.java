package backend.world;

import java.util.Stack;

import backend.object.Plate;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithPlateStrategy implements IntersectionHandlerStrategy{

    @Override
    public void handleIntersection(GameObject plate, GameObject onStick, Circus circus) {
        //it fell on me fa put it
        Stack<GameObject> stack;
        circus.getMovableObjects().remove(plate);
        circus.getControlableObjects().add(plate);
        plate.setX(onStick.getX() + onStick.getWidth()/2 - plate.getWidth()/2);
        
        
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
            stack = circus.getClown().getLeftObjStack();
        else
            stack = circus.getClown().getRightObjStack();
        stack.push(plate);

        // if(isThreePlatesOfSameColor(stack))
        // {
        //     // circus.reuse(stack.pop());
        //     // circus.reuse(stack.pop());
        //     // circus.reuse(stack.pop());
        //     stack.pop();
        //     stack.pop();
        //     stack.pop();
        //     //increase score
        //     circus.setScore(circus.getScore()+1);
        //     //-----------------------
        //     //i think we can do better
        //     // circus.getMovableObjects().add(circus.getObjFalling().generateObjectsFalling(1).get(0));//too keep them plates coming
        //     // circus.getMovableObjects().add(circus.getObjFalling().generateObjectsFalling(1).get(0));//too keep them plates coming
        //     // circus.getMovableObjects().add(circus.getObjFalling().generateObjectsFalling(1).get(0));//too keep them plates coming
            
        // }
        // else{
        plate.setY(onStick.getY() - plate.getHeight());//can we make plate height and width static?
            // circus.getMovableObjects().add(circus.getObjFalling().generateObjectsFalling(1).get(0));//too keep them plates coming
        //}

    }

    public boolean isThreePlatesOfSameColor(Stack<GameObject> stack)
    {
        GameObject obj1,obj2,obj3;
        Plate plate1,plate2,plate3;
        boolean retVal = false;

        if(stack.size() >= 3) 
        {
            //getting the three plates on it
            obj1 = stack.pop();
            obj2 = stack.pop();
            obj3 = stack.pop();
            plate1 = (Plate) obj1;
            plate2 = (Plate) obj2;
            plate3 = (Plate) obj3;

            if(plate1.getPlateColor() == plate2.getPlateColor() &&
                plate1.getPlateColor() == plate3.getPlateColor() &&
                plate2.getPlateColor() == plate3.getPlateColor())
                {
                    retVal = true;
                }
            
            stack.push(obj3);
            stack.push(obj2);
            stack.push(obj3);

        }
        return retVal;
    }


    
    


}
