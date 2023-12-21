package backend.world.InstersectionHandlerStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import backend.object.Clown;
import backend.object.ClownIteratorConcrete;
import backend.object.FallingObject;
import backend.object.Plate;
import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

public class IntersectWithPlateStrategy implements IntersectionHandlerStrategy{

    @Override
    public void handleIntersection(FallingObject fallingPlate, GameObject onStick, Circus circus) {
        //it fell on me fa put it
        Stack<GameObject> stack;
        Plate plate = (Plate)fallingPlate;
        circus.getMovableObjects().remove(plate);
        circus.getControlableObjects().add(plate);
        plate.setY(onStick.getY() - plate.getHeight());
        plate.setIsCaught(true);
        plate.setX(onStick.getX() + onStick.getWidth()/2 - plate.getWidth()/2);
        
        
        //right or left stack
        if((onStick.getX() - circus.getClown().getX()) <= circus.getClown().getWidth()/2)
            stack = circus.getClown().getLeftObjStack();
        else
            stack = circus.getClown().getRightObjStack();
        stack.push(plate);
        checkPLate(stack, circus);
        
    }

    // public boolean isThreePlatesOfSameColor(Stack<GameObject> stack)
    // {
    //     GameObject obj1,obj2,obj3;
    //     Plate plate1,plate2,plate3;
    //     boolean retVal = false;

    //     if(stack.size() >= 3) 
    //     {
    //         //getting the three plates on it
    //         obj1 = stack.pop();
    //         obj2 = stack.pop();
    //         obj3 = stack.pop();
    //         plate1 = (Plate) obj1;
    //         plate2 = (Plate) obj2;
    //         plate3 = (Plate) obj3;

    //         if(plate1.getPlateColor() == plate2.getPlateColor() &&
    //             plate1.getPlateColor() == plate3.getPlateColor() &&
    //             plate2.getPlateColor() == plate3.getPlateColor())
    //             {
    //                 retVal = true;
    //             }
            
    //         stack.push(obj3);
    //         stack.push(obj2);
    //         stack.push(obj3);

    //     }
    //     return retVal;
    // }


    public void checkPLate(Stack<GameObject> stack,Circus circus) {
        ClownIteratorConcrete iterator = new ClownIteratorConcrete(stack);

        if (iterator.getSize() >= 3) {
            int i = 0;
            iterator.setI(stack.size() - 1);
            List<Plate> plates = new ArrayList<Plate>();

            while (i <= 2) {

                Plate plate = (Plate) iterator.next();
                plates.add(plate);
               // System.out.println(plate.getPlateColor());
                i++;
            }
            i = 0;

            if (plates.get(0).getPlateColor() == plates.get(1).getPlateColor()
                    && plates.get(1).getPlateColor() == plates.get(2).getPlateColor()) {
                circus.setScore(circus.getScore()+300);
                while (i <= 2) {
                    Plate plateRemoved = (Plate) stack.pop();
                    circus.getControlableObjects().remove(plateRemoved);
                    i++;
                }

            }

            plates.clear();
            //System.out.println("size " + plates.size());
            // if (iterator.getSize() >= 3) {
            // Plate plate1 = (Plate) iterator.next();
            // Plate plate2 = (Plate) iterator.next();
            // }
        }

    }
    
    


}
