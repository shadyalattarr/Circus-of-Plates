package backend.world;

import java.util.ArrayList;

import backend.object.Bomb;
import backend.object.Plate;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BombsStartegy implements ObjectsFallingStrategy {

    ArrayList<GameObject> moving = new ArrayList<GameObject>();

    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {

        for(int i=0; i < n; i++)
        {
            if(Math.random()<=0.2)
                moving.add(new Bomb((int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/*getHeight()*//2),"FinalProjectt\\bombresized.png"));
            else
                moving.add(new Plate((int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/*getHeight()*//2)));
        }
        return moving;
    }
}
