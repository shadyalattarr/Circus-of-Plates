package backend.world.ObjectsFallingStrategy;

import java.util.ArrayList;

import backend.object.ObjectFactory;
import eg.edu.alexu.csd.oop.game.GameObject;

public class EverythingSoFarStrategy implements ObjectsFallingStrategy {
    ArrayList<GameObject> moving = new ArrayList<GameObject>();
    ObjectFactory factory = new ObjectFactory();
    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {
        double random;
        for(int i=0; i < n; i++)
        {
            random = Math.random();
            if (random<=0.05) 
                moving.add(factory.createObject("Heart",(int)(Math.random()*1400/*getWidth()*/), 0));
            else if(random<=0.2)
                moving.add(factory.createObject("Bomb",(int)(Math.random()*1400/*getWidth()*/), 0/*getHeight()/2*/)); 
            else{
                if(Math.random()<0.25)
                    moving.add(factory.createObject("Cup",(int)(Math.random()*1400/*getWidth()*/), 0 /*getHeight()/2*/));            
                else
                    moving.add(factory.createObject("Plate",(int)(Math.random()*1400/*getWidth()*/), 0 /*getHeight()/2*/));
            }
        }
        return moving;
    }
}
