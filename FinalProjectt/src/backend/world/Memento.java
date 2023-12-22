package backend.world;

import java.util.LinkedList;
import java.util.List;

import backend.world.State.Game;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Memento {
    private final int score;
    private final HeartCounter heartCounter;
    private final LinkedList<GameObject> constant;
    private final LinkedList<GameObject> moving;
    private final LinkedList<GameObject> control;

    public Memento(int score, HeartCounter heartCounter, LinkedList<GameObject> constant, LinkedList <GameObject> moving, LinkedList<GameObject> control){
        this.score=score;
        this.heartCounter=new HeartCounter(heartCounter.getLives());
        this.constant= createList(constant);
        this.moving= createList(moving);
        this.control= createList(control);
    }

    private LinkedList<GameObject> createList(LinkedList<GameObject> list)
    {
        LinkedList<GameObject> newList = new LinkedList<GameObject>();
        newList.addAll(list);
        return newList;
    }
    public List<GameObject> getConstant() {
        return constant;
    }

    public List<GameObject> getControl() {
        return control;
    }

    public HeartCounter getHeartCounter() {
        return heartCounter;
    }

    public List<GameObject> getMoving() {
        return moving;
    }

    public int getScore() {
        return score;
    }
}
