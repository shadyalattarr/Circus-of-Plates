package backend.world;

import java.util.List;

import backend.world.State.Game;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Memento {
    private final int score;
    private final HeartCounter heartCounter;
    private final List<GameObject> constant;
    private final List<GameObject> moving;
    private final List<GameObject> control;

    public Memento(int score, HeartCounter heartCounter, List<GameObject> constant, List <GameObject> moving, List<GameObject> control){
        this.score=score;
        this.heartCounter=heartCounter;
        this.constant=constant;
        this.moving=moving;
        this.control=control;
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
