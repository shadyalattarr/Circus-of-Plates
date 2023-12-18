package backend.world;

import backend.object.BarObject;
import backend.object.Clown;
import backend.object.FallingObject;
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


    //------------
    private final BarObject leftStick;
    private final BarObject rightStick;
    private final Clown clown;
    private Stack<GameObject> leftObjStack;
    private Stack<GameObject> rightObjStack;

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
        // maybe difficulty sent in constructor?
        movement = new MovementStrategy(new NoOscillationStrategy(), new DownStrategy());
        objFalling = new PlatesOnlyStrategy();
        difficulty = new DifficultyStrategy(new ObjectSpeedlvl2Strategy(), movement, objFalling);
        // all above not here

        moving = objFalling.generateObjectsFalling(6);
    }
    
    private boolean intersect(GameObject fallingObject, GameObject stick){
        double fallingObjectXCenter = (fallingObject.getX() + fallingObject.getWidth()/2.0);
        double stickXCenter = (stick.getX() + stick.getWidth()/2.0);
        int fallingObjectBottomY = fallingObject.getY() + fallingObject.getHeight();
        int stickTop = stick.getY();
		return ((Math.abs(fallingObjectXCenter - stickXCenter) <= fallingObject.getWidth()/3) && (Math.abs(stickTop- fallingObjectBottomY)<=5));
	}
    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        for (GameObject o : moving.toArray(new GameObject[moving.size()])) {
            //before moving we check if it intersects with stick
            GameObject lefttoIntersectWith;
            GameObject righttoIntersectWith;

            lefttoIntersectWith = leftObjStack.size() == 0 ? leftStick: leftObjStack.peek(); 
            righttoIntersectWith = rightObjStack.size() == 0 ? rightStick : rightObjStack.peek();

            if(intersect(o, lefttoIntersectWith))
            {
                System.out.println("HitLerftStick");//germany gg
                moving.remove(o);
                control.add(o);
                o.setX(lefttoIntersectWith.getX() + lefttoIntersectWith.getWidth()/2 - o.getWidth()/2);
                leftObjStack.push(o);
                o.setY(lefttoIntersectWith.getY() - o.getHeight());//can we make plate height and width static?

            } 
            else if(intersect(o, righttoIntersectWith))
            {
                System.out.println("HitRightStick");
                moving.remove(o);
                control.add(o);
                o.setX(righttoIntersectWith.getX() + righttoIntersectWith.getWidth()/2 - o.getWidth()/2);
                rightObjStack.push(o);
                o.setY(righttoIntersectWith.getY() - o.getHeight());//can we make plate height and width static?

            }

            //move
            movement.move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());
            
            //want to make a method for that
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
