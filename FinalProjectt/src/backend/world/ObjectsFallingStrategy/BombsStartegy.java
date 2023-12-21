package backend.world.ObjectsFallingStrategy;

import java.util.ArrayList;

import backend.object.ObjectFactory;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BombsStartegy implements ObjectsFallingStrategy {

    ArrayList<GameObject> moving = new ArrayList<GameObject>();
    ObjectFactory factory = new ObjectFactory();
    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {

        for(int i=0; i < n; i++)
        {
            if(Math.random()<=0.2)
               moving.add(factory.createObject("Bomb",(int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/*getHeight()*//2),"FinalProjectt\\bombresized.png"));
            // else if (Math.random()<=0.1) {
            //     moving.add(factory.createObject("Heart",(int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/2) , null));
            // }
            else{
                moving.add(factory.createObject("Plate",(int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/*getHeight()*//2),null));
        }
    }
        return moving;
    }
}
