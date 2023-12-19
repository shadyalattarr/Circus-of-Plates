package backend.world;

import backend.object.BarObject;
import backend.object.Bomb;
import backend.object.Clown;
import backend.object.FallingObject;
import backend.object.IntersectWithBombStrategy;
import backend.object.Plate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
    Intersection intersection;


    //------------
    private final BarObject leftStick;
    private final BarObject rightStick;
    private final Clown clown;
    private Stack<GameObject> leftObjStack;
    private Stack<GameObject> rightObjStack;

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getRIGHT_STICK() {
        return this.RIGHT_STICK;
    }

    public void setRIGHT_STICK(int RIGHT_STICK) {
        this.RIGHT_STICK = RIGHT_STICK;
    }

    public int getLEFT_STICK() {
        return this.LEFT_STICK;
    }

    public void setLEFT_STICK(int LEFT_STICK) {
        this.LEFT_STICK = LEFT_STICK;
    }

    public List<GameObject> getConstant() {
        return this.constant;
    }


    public List<GameObject> getMoving() {
        return this.moving;
    }


    public List<GameObject> getControl() {
        return this.control;
    }


    public DifficultyStrategy getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(DifficultyStrategy difficulty) {
        this.difficulty = difficulty;
    }

    public MovementStrategy getMovement() {
        return this.movement;
    }

    public void setMovement(MovementStrategy movement) {
        this.movement = movement;
    }

    public ObjectsFallingStrategy getObjFalling() {
        return this.objFalling;
    }

    public void setObjFalling(ObjectsFallingStrategy objFalling) {
        this.objFalling = objFalling;
    }

    

    public BarObject getLeftStick() {
        return this.leftStick;
    }


    public BarObject getRightStick() {
        return this.rightStick;
    }


    public Clown getClown() {
        return this.clown;
    }


    public Stack<GameObject> getLeftObjStack() {
        return this.leftObjStack;
    }

    public void setLeftObjStack(Stack<GameObject> leftObjStack) {
        this.leftObjStack = leftObjStack;
    }

    public Stack<GameObject> getRightObjStack() {
        return this.rightObjStack;
    }

    public void setRightObjStack(Stack<GameObject> rightObjStack) {
        this.rightObjStack = rightObjStack;
    }

    //------------
    
    public Circus(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        // moving = new LinkedList<GameObject>();
        clown = new Clown((int) (screenWidth / 2.6), (int) (screenHeight / 1.4),
                "FinalProjectt\\clown-removebg-preview_3_53.png");
        control.add(clown);
        leftStick = new BarObject(clown.getX() + 30, clown.getY() - 30, 150, true, Color.RED);
        rightStick = new BarObject(clown.getX() + 210, clown.getY() - 30, 130, true, Color.GREEN);
        leftObjStack = new Stack<GameObject>();
        rightObjStack = new Stack<GameObject>();
        control.add(leftStick);
        control.add(rightStick);
        intersection = new Intersection(this);
        // maybe difficulty sent in constructor?
        movement = new MovementStrategy(new NoOscillationStrategy(), new DownStrategy());
        objFalling = new BombsStartegy();
        difficulty = new DifficultyStrategy(new ObjectSpeedlvl2Strategy(), movement, objFalling);
        // all above not here

        moving = objFalling.generateObjectsFalling(10);
    }
    
    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        GameObject lefttoIntersectWith;
        GameObject righttoIntersectWith;
        for (GameObject o : moving.toArray(new GameObject[moving.size()])) {
            //before moving we check if it intersects with stick    
            lefttoIntersectWith = leftObjStack.size() == 0 ? leftStick: leftObjStack.peek(); 
            righttoIntersectWith = rightObjStack.size() == 0 ? rightStick : rightObjStack.peek();
            
            if(o instanceof Plate)
                intersection.setIntersection(new IntersectWithPlateStrategy());
            else if(o instanceof Bomb)
                intersection.setIntersection(new IntersectWithBombStrategy());

            intersection.handleIntersection(o,righttoIntersectWith);
            intersection.handleIntersection(o,lefttoIntersectWith);
            
            //move
            movement.move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());
            
            //want to make a method for that
            if (o.getY() >= getHeight()) {
                // in bottom
                System.out.println("Plate hit bot");
                reuse(o);
            }
        }
        return !timeout;
    }

    public void reuse(GameObject object)
    {
        object.setY(-1 * (int) (Math.random() * getHeight()));// get it up?
        object.setX((int) (Math.random() * getWidth()));
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
