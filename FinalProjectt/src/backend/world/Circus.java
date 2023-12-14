package backend.world;

import backend.object.FallingObject;
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
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();

    public Circus(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
    }

    @Override
    public boolean refresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
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
