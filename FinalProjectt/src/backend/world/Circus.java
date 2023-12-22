package backend.world;

import backend.object.*;
import backend.world.InstersectionHandlerStrategy.*;
import backend.world.Movement.*;
//import backend.world.Movement.RightDiagonalStrategy;

import backend.world.Movement.ObjectSpeedStrategy.*;
import backend.world.ObjectsFallingStrategy.*;
import backend.world.State.*;

import java.awt.Color;
import java.util.*;

import javax.swing.JButton;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Circus extends Game implements World {
    private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
    private int score;
    private long endTime, timePassedInms , startTime;
    private final int width;
    private final int height;
    private final List<GameObject> constant;
    private final List<GameObject> moving;// moved instatiation to constructor
    private final List<GameObject> control;
    private final List<GameObject> objectsToFall;
    private int fallingObjPerSecond;

    private int i;
    private Game game;
    private boolean gameOver;
    // for now difficulty
    HeartCounter hearts;
    Difficulty difficulty;
    //Movement movement;
    ObjectsFallingStrategy objFalling;
    Intersection intersection;

    // ------------
    private final Clown clown;

    // ------------
    private static Circus circus;

    public static Circus getCircus(PredefinedDifficultyStrategy difficulty) {
        if (circus == null)
            circus = new Circus(1900, 750,difficulty);
        return circus;
    }
    public Circus(Memento memento)
    {
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        moving = new LinkedList<GameObject>();
        objectsToFall = new ArrayList<GameObject>();
        clown = Clown.getInstance();
        this.loadGame(memento);
    }
    private Circus(int screenWidth, int screenHeight,PredefinedDifficultyStrategy difficulty) {
        startTime = System.currentTimeMillis();
        i = 0;
        score = 0;
        timePassedInms = 0L;
        game = new Game();
        gameOver = false;
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        moving = new LinkedList<GameObject>();
      //  constant.add(new BarObject((int)(Math.random() * screenWidth/2), (int)(Math.random() * screenHeight/4 + screenHeight/2), 15, Color.RED,clown,true));
    constant.add(new BackGround(screenWidth,screenHeight,0,0,"FinalProjectt\\cp.png",0));
        // maybe difficulty sent in constructor?
        this.difficulty = new Difficulty(difficulty);
        hearts = new HeartCounter(3);
        intersection = new Intersection(this);
        //movement = this.difficulty.getMovement();
        objFalling = this.difficulty.getObjectsFallingStrategy();
        
        // all above not here


        // make clown coords x 10
        clown = Clown.getInstance();

        control.add(clown);
        control.add(clown.getLeftStick());
        control.add(clown.getRightStick());

        objectsToFall = objFalling.generateObjectsFalling(1000);

    }

    public void spawn(int n) {
        for (int j = 0; j < n; j++) {
            try {
                // System.out.println(j);
                moving.add(objectsToFall.get(i + j));
            } catch (IndexOutOfBoundsException e) {
                // maybe a joption pane too?
                System.out.println("ERRAOR : You ran out of falling objects");
                // close game
                System.exit(0);
            }
        }
        i += n;
    }

    @Override
    public boolean refresh() {
        GameObject c = constant.get(0);
        c.isVisible();
        boolean timeout = timePassedInms > MAX_TIME;
        if (!gameOver) {
            // state management
            if (hearts.getLives() == 0 || timeout) {
                game.setState(new Finish());
                game.currentEvent();
            } else if (timePassedInms / 1000 == 45) {
                game.setState(new Almost());
                game.currentEvent();
            }

            if (timePassedInms / 1000 + 1 <= (System.currentTimeMillis() - startTime) / 1000.0) {
                spawn(difficulty.getNumFallingObjPerSecond());
            }

            // update time passed
            timePassedInms = System.currentTimeMillis() - startTime;

            GameObject lefttoIntersectWith;
            GameObject righttoIntersectWith;
            for (GameObject o : moving.toArray(new GameObject[moving.size()])) {
                // before moving we check if it intersects with stick
                lefttoIntersectWith = clown.getLeftObjStack().size() == 0 ? clown.getLeftStick()
                        : clown.getLeftObjStack().peek();
                righttoIntersectWith = clown.getRightObjStack().size() == 0 ? clown.getRightStick()
                        : clown.getRightObjStack().peek();

                if (o instanceof ObjectOnStick)
                    intersection.setIntersection(new IntersectWithOjectOnStickStrategy());
                else if (o instanceof Bomb)
                    intersection.setIntersection(new IntersectWithBombStrategy());
                else if (o instanceof Heart)
                    intersection.setIntersection(new IntersectwithHeartStrategy());

                intersection.handleIntersection((FallingObject) o, righttoIntersectWith);
                intersection.handleIntersection((FallingObject) o, lefttoIntersectWith);

                difficulty.getMovement().move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());

            }
        }
        //System.out.println(getStatus());
        //System.out.println(circus);
        // returning false is GamePver
        return !gameOver;

    }

    public Memento createMemento(){
        System.out.println("i saved:" + i);
        return new Memento(i,score, getHeartCounter(), objectsToFall,getConstantObjects(), getMovableObjects(), getControlableObjects());
    }

    public void loadGame(Memento memento){
        this.hearts=memento.getHeartCounter();
        

        System.out.println(this.score);

        setScore(memento.getScore());

        System.out.println(memento.getScore());
        System.out.println(this.score);

        getConstantObjects().clear();
        getMovableObjects().clear();
        getMovableObjects().clear();
        this.objectsToFall.clear();

        System.out.println(getConstantObjects().size());
        System.out.println(getMovableObjects().size());
        System.out.println(getControlableObjects().size());

        this.constant.addAll(memento.getConstant());
        this.moving.addAll(memento.getMoving());
        this.control.addAll(memento.getControl());
        this.objectsToFall.addAll(memento.getObjectsToFall());
        
        System.out.println(getConstantObjects().size());
        System.out.println(getMovableObjects().size());
        System.out.println(getControlableObjects().size());
        System.out.println("i before "+ i);

        this.i = memento.getI();
        System.out.println("i afetr:"+i);
        this.timePassedInms = memento.getTimePassedInms();


        System.out.println(getStatus());


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
        return "Score=" + getScore() + "   |   Time="
                + Math.max(0, (MAX_TIME - (timePassedInms)) / 1000) + "   |   Lives="
                + hearts.getLives();  // update status
    }

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


    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setObjectsFallingSpeed(ObjectSpeedStrategy speedStrategy) {
        difficulty.setFallingObjectSpeedStrategy(speedStrategy);
    }

    public ObjectsFallingStrategy getObjFalling() {
        return this.objFalling;
    }

    public void setObjFalling(ObjectsFallingStrategy objFalling) {
        this.objFalling = objFalling;
    }

    public Clown getClown() {
        return this.clown;
    }

    public HeartCounter getHeartCounter() {
        return hearts;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void reset(){
        circus=null;
    }

}
