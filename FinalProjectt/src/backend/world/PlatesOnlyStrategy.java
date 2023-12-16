package backend.world;

import java.util.ArrayList;
import eg.edu.alexu.csd.oop.game.GameObject;
import backend.object.FallingObject;
import backend.object.Plate;

public class PlatesOnlyStrategy implements ObjectsFallingStrategy{
    ArrayList<GameObject> moving = new ArrayList<GameObject>();

    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {
        for(int i=0; i < n; i++)
            moving.add(new Plate((int)(Math.random()*550/*getWidth()*/), (int)(Math.random()*600/*getHeight()*//2)));
        return moving;
    }

}
