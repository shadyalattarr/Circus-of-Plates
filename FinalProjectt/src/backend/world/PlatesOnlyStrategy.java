package backend.world;

import java.util.ArrayList;
import eg.edu.alexu.csd.oop.game.GameObject;
import backend.object.Plate;

public class PlatesOnlyStrategy implements ObjectsFallingStrategy{
    ArrayList<GameObject> moving = new ArrayList<GameObject>();

    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {
        for(int i=0; i < n; i++)
            moving.add(new Plate((int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/*getHeight()*//2)));
        return moving;
    }

}
