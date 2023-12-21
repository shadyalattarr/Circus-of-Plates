package backend.world.ObjectsFallingStrategy;

import java.util.ArrayList;
import eg.edu.alexu.csd.oop.game.GameObject;
import backend.object.ObjectFactory;
import backend.object.Plate;

public class PlatesOnlyStrategy implements ObjectsFallingStrategy{
    ArrayList<GameObject> moving = new ArrayList<GameObject>();
    ObjectFactory factory = new ObjectFactory();

    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {
        for(int i=0; i < n; i++)
            moving.add(factory.createObject("Plate",(int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/10 /*getHeight()/2*/),null));
        return moving;
    }

}
