package backend.world.ObjectsFallingStrategy;

import java.util.ArrayList;

import backend.object.ObjectFactory;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BombsStrategy implements ObjectsFallingStrategy {

    ArrayList<GameObject> moving = new ArrayList<GameObject>();
    ObjectFactory factory = new ObjectFactory();
    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {

        for(int i=0; i < n; i++)
        {
            if(Math.random()<=0.2)
               moving.add(factory.createObject("Bomb",(int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/10 /*getHeight()/2*/)));
            else{
                moving.add(factory.createObject("Plate",(int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/10 /*getHeight()/2*/)));
            }
        }
        return moving;
    }
}
