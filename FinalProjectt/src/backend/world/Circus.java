package backend.world;

import backend.object.Bomb;
import backend.object.Clown;
// import backend.object.ClownIterator;
import backend.object.ClownIteratorConcrete;
import backend.object.FallingObject;
import backend.object.Plate;
import backend.world.InstersectionHandlerStrategy.IntersectWithBombStrategy;
import backend.world.InstersectionHandlerStrategy.IntersectWithPlateStrategy;
import backend.world.InstersectionHandlerStrategy.Intersection;
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
    private final List<GameObject> objectsToFall;
    private int lives = 3;
    // for now difficulty
    DifficultyStrategy difficulty;
    MovementStrategy movement;
    ObjectsFallingStrategy objFalling;
    Intersection intersection;
    // ClownIterator clownIterator;
    ClownIteratorConcrete clownLeftIteratorConcrete;
    ClownIteratorConcrete clownRightIteratorConcrete;
    // ClownIterator clownrightIterator;

    // ------------
    private final Clown clown;

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

    public Clown getClown() {
        return this.clown;
    }

    // ------------

    public Circus(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        // moving = new LinkedList<GameObject>();

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
        clownLeftIteratorConcrete = new ClownIteratorConcrete(clown.getLeftObjStack());
        clownRightIteratorConcrete = new ClownIteratorConcrete(clown.getRightObjStack());

        objectsToFall = objFalling.generateObjectsFalling(20);

        moving = objectsToFall;
        // every second
        // moving.add(//objectsToFall)
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
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

            intersection.handleIntersection((FallingObject) o, righttoIntersectWith);
            intersection.handleIntersection((FallingObject) o, lefttoIntersectWith);

            // move
            movement.move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());
            checkLPLate(clownLeftIteratorConcrete);
            checkRPLate(clownRightIteratorConcrete);

            // want to make a method for that
            if (o.getY() >= getHeight()) {
                // in bottom
                // System.out.println("Plate hit bot");
                reuse(o);
            }
        }
        GameObject stick = control.get(1);
        // System.out.println("Stick x : " + stick.getX());
        return !timeout;
    }

    public void reuse(GameObject object) {
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

    public void checkLPLate(ClownIteratorConcrete iterator) {
        Stack<GameObject> stack = getClown().getLeftObjStack();

        if (iterator.getSize() >= 3) {
            int i = 0;
            iterator.setI(stack.size() - 1);
            List<Plate> plates = new ArrayList<Plate>();

            while (i <= 2) {

                Plate plate = (Plate) iterator.next();
                plates.add(plate);
               // System.out.println(plate.getPlateColor());
                i++;
            }
            i = 0;

            if (plates.get(0).getPlateColor() == plates.get(1).getPlateColor()
                    && plates.get(1).getPlateColor() == plates.get(2).getPlateColor()) {
                score += 3;
                while (i <= 2) {
                    Plate plateRemoved = (Plate) stack.pop();
                    getControlableObjects().remove(plateRemoved);
                    i++;
                }

            }

            plates.clear();
            //System.out.println("size " + plates.size());
            // if (iterator.getSize() >= 3) {
            // Plate plate1 = (Plate) iterator.next();
            // Plate plate2 = (Plate) iterator.next();
            // }
        }

    }

    public void checkRPLate(ClownIteratorConcrete iterator) {
        Stack<GameObject> stack = getClown().getRightObjStack();

        if (iterator.getSize() >= 3) {
            int i = 0;
            iterator.setI(stack.size() - 1);
            List<Plate> plates = new ArrayList<Plate>();

            while (i <= 2) {

                Plate plate = (Plate) iterator.next();
                plates.add(plate);
              //  System.out.println(plate.getPlateColor());
                i++;
            }
            i = 0;

            if (plates.get(0).getPlateColor() == plates.get(1).getPlateColor()
                    && plates.get(1).getPlateColor() == plates.get(2).getPlateColor()) {
                score += 1;
                while (i <= 2) {
                    Plate plateRemoved = (Plate) stack.pop();
                    getControlableObjects().remove(plateRemoved);
                    i++;
                }

            }

            plates.clear();
          //  System.out.println("size " + plates.size());
            // if (iterator.getSize() >= 3) {
            // Plate plate1 = (Plate) iterator.next();
            // Plate plate2 = (Plate) iterator.next();
            // }
        }

    }
}
