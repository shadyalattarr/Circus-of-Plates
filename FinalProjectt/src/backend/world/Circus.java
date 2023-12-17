package backend.world;

import backend.object.BarObject;
import backend.object.Clown;
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
    private int RIGHT_STICK = 1;
    private int LEFT_STICK = 2;
    private final int width;
    private final int height;
    private final List<GameObject> constant;
    private final List<GameObject> moving;// moved instatiation to constructor
    private final List<GameObject> control;
    // for now difficulty
    DifficultyStrategy difficulty;
    MovementStrategy movement;
    ObjectsFallingStrategy objFalling;

    public Circus(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        // moving = new LinkedList<GameObject>();
        Clown clown = new Clown((int) (screenWidth / 2.6), (int) (screenHeight / 1.4),
                "clown-removebg-preview_3_53.png");
        // maybe difficulty sent in constructor?
        control.add(clown);
        control.add(new BarObject(clown.getX() + 30, clown.getY() - 30, 150, true, Color.RED));
        control.add(new BarObject(clown.getX() + 210, clown.getY() - 30, 130, true, Color.GREEN));
        movement = new MovementStrategy(new NoOscillationStrategy(), new DownStrategy());
        objFalling = new PlatesOnlyStrategy();
        difficulty = new DifficultyStrategy(new ObjectSpeedlvl2Strategy(), movement, objFalling);
        // all above not here

        moving = objFalling.generateObjectsFalling(6);
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        for (GameObject o : moving.toArray(new GameObject[moving.size()])) {
            movement.move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());
            if (o.getY() >= getHeight()) {
                // in bottom
                System.out.println("Plate hit bot");
                o.setY(-1 * (int) (Math.random() * getHeight()));// get it up?
                o.setX((int) (Math.random() * getWidth()));
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
