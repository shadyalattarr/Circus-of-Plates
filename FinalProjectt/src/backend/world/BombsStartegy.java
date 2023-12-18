package backend.world;

import java.util.ArrayList;

import backend.object.Bomb;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BombsStartegy implements ObjectsFallingStrategy {

    ArrayList<GameObject> moving = new ArrayList<GameObject>();

    @Override
    public ArrayList<GameObject> generateObjectsFalling(int n) {
        for(int i=0; i < n; i++)
            moving.add(new Bomb((int)(Math.random()*1400/*getWidth()*/), (int)(Math.random()*750/*getHeight()*//2),"FinalProjectt\\bomb-removebg-preview.png"));
        return moving;
    }
}
