package backend.world;

import backend.object.FallingObject;
import backend.object.Plate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Circus implements World {
    private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
    private int score = 0;
    private long endTime, startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant;
    private final List<GameObject> moving;//moved instatiation to constructor
    private final List<GameObject> control;
    //for now difficulty
    DifficultyStrategy difficulty;
    MovementStrategy movement;
    public Circus(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        moving = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        //maybe difficulty sent in constructor?
        movement = new MovementStrategy(new NoOscillationStrategy(), new DownStrategy());
        difficulty = new DifficultyStrategy(new ObjectSpeedlvl2Strategy(), movement);
        for(int i=0; i < 6; i++)
            moving.add(new Plate((int)(Math.random()*getWidth()), (int)(Math.random()*getHeight()/2),Color.RED));

    }

    

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        for(GameObject o : moving.toArray(new GameObject[moving.size()])){
            movement.move((FallingObject)o, difficulty.getFallingObjectSpeedStrategy());
            if(o.getY()>=getHeight()){
                //in bottom
                System.out.println("Plate hit bot");
                o.setY(-1 * (int)(Math.random() * getHeight()));//get it up?
                o.setX((int)(Math.random() * getWidth()));	
            }
        }
        return !timeout;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 10;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time="
                + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000); // update status
    }

}
