package backend.world;

import backend.object.Bomb;
import backend.object.Clown;
// import backend.object.ClownIterator;
import backend.object.ClownIteratorConcrete;
import backend.object.FallingObject;
import backend.object.Heart;
import backend.object.Plate;
import backend.world.InstersectionHandlerStrategy.IntersectWithBombStrategy;
import backend.world.InstersectionHandlerStrategy.IntersectWithPlateStrategy;
import backend.world.InstersectionHandlerStrategy.Intersection;
import backend.world.InstersectionHandlerStrategy.IntersectwithHeartStrategy;
import backend.world.Movement.DifficultyStrategy;
import backend.world.Movement.DownStrategy;
import backend.world.Movement.MovementStrategy;
import backend.world.Movement.NoOscillationStrategy;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedlvl2Strategy;
import backend.world.ObjectsFallingStrategy.BombsStartegy;
import backend.world.ObjectsFallingStrategy.ObjectsFallingStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Circus extends Game implements World {
    private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
    private int score = 0;
    private long endTime, timePassedInms = 0L, startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant;
    private final List<GameObject> moving;// moved instatiation to constructor
    private final List<GameObject> control;
    private final List<GameObject> objectsToFall;
    private int i = 0;
    Game game = new Game();
    HeartCounter hearts = new HeartCounter();
    // for now difficulty
    DifficultyStrategy difficulty;
    MovementStrategy movement;
    ObjectsFallingStrategy objFalling;
    Intersection intersection;
    // ClownIterator clownIterator;
    // ClownIterator clownrightIterator;

    // ------------
    private final Clown clown;

    // ------------

    public Circus(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        moving = new LinkedList<GameObject>();

        // maybe difficulty sent in constructor?
        intersection = new Intersection(this);
        movement = new MovementStrategy(new NoOscillationStrategy(), new DownStrategy());
        objFalling = new BombsStartegy();
        difficulty = new DifficultyStrategy(new ObjectSpeedlvl2Strategy(), movement, objFalling);
        // all above not here

        clown = Clown.getInstance((int) (screenWidth / 2.6), (int) (screenHeight / 1.4),
                "FinalProjectt\\clown-removebg-preview_3_53.png");

        control.add(clown);
        control.add(clown.getLeftStick());
        control.add(clown.getRightStick());

        // Iterator leftIterator = clownIterator.creatLeftIterable();
        // Iterator rightIterator = clownIterator.creatRightIterable();
        objectsToFall = objFalling.generateObjectsFalling(1000);

        // moving = objectsToFall;
        // every second
        // moving.add(//objectsToFall)
    }

    public void spawn(int n) {
        for (int j = 0; j < n; j++) {
            try {
                // System.out.println(j);
                moving.add(objectsToFall.get(i + j));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERRAOR : You ran out of falling objects");
                // close game
                System.exit(0);
            }
        }
        i += n;
    }

    public long getTimePassedInms() {
        return timePassedInms;
    }

    @Override
    public boolean refresh() {
        boolean timeout = timePassedInms > MAX_TIME;
        // before it was ex.6 sec passed.. if i just entered and 6 sec passed,, nothing
        // but if now 7 sec passed a sec passed
        // int num = difficulty.getFallingObjectSpeedStrategy().getFallingObjectSpeed();
        if (timePassedInms / 1000 + 1 <= (System.currentTimeMillis() - startTime) / 1000.0) {
            // System.out.println(timePassedInms/1000);
            // up for debate
            spawn(3);
        }
        // update time passed
        timePassedInms = System.currentTimeMillis() - startTime;
        if (hearts.getLives() == 0 || timePassedInms / 1000 == 60) {
            game.setState(new Finish());
            game.currentEvent();
        }

        if (timePassedInms / 1000 == 50) {
            game.setState(new Almost());
            game.currentEvent();
        }
        // System.out.println(timePassedInms/1000);
        // boolean aSecondPassed = timePassedInms/1000;
        GameObject lefttoIntersectWith;
        GameObject righttoIntersectWith;
        for (GameObject o : moving.toArray(new GameObject[moving.size()])) {
            // before moving we check if it intersects with stick
            lefttoIntersectWith = clown.getLeftObjStack().size() == 0 ? clown.getLeftStick()
                    : clown.getLeftObjStack().peek();
            righttoIntersectWith = clown.getRightObjStack().size() == 0 ? clown.getRightStick()
                    : clown.getRightObjStack().peek();

            if (o instanceof Plate)
                intersection.setIntersection(new IntersectWithPlateStrategy());
            else if (o instanceof Bomb)
                intersection.setIntersection(new IntersectWithBombStrategy());
            else if(o instanceof Heart)
                intersection.setIntersection(new IntersectwithHeartStrategy());

            intersection.handleIntersection((FallingObject) o, righttoIntersectWith);
            intersection.handleIntersection((FallingObject) o, lefttoIntersectWith);

            // if(hearts.getLives()==0 || timePassedInms==0)
            // game.setState(new Finish());

            // if(timePassedInms==10)
            // game.setState(new Almost());
            // move
            movement.move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());

            // do we want reuse?
            // if (o.getY() >= getHeight()) {
            // // in bottom
            // // System.out.println("Plate hit bot");
            // reuse(o);
            // }
        }
        // GameObject stick = control.get(1);
        // System.out.println("Stick x : " + stick.getX());
        return !timeout;
    }

    // public void reuse(GameObject object) {
    // object.setY(-1 * (int) (Math.random() * getHeight()));// get it up?
    // object.setX((int) (Math.random() * getWidth()));
    // }

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
                + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000) + "   |   Lives="
                + hearts.getLives(); // update status
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

    public Clown getClown() {
        return this.clown;
    }

}
