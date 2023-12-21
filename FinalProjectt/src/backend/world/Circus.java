package backend.world;

import backend.object.*;
import backend.world.InstersectionHandlerStrategy.*;
import backend.world.Movement.*;
//import backend.world.Movement.RightDiagonalStrategy;

import backend.world.Movement.ObjectSpeedStrategy.*;
import backend.world.ObjectsFallingStrategy.*;
import backend.world.State.*;
import java.util.*;

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
    private int fallingObjPerSecond;
    
    private int i;
    private Game game;
    private boolean gameOver;
    //for now difficulty
    HeartCounter hearts;
    DifficultyStrategy difficulty;
    MovementStrategy movement;
    ObjectsFallingStrategy objFalling;
    Intersection intersection;


    // ------------
    private final Clown clown;

    // ------------
    private static Circus circus;

    public static Circus getCircus(){
        if(circus==null)
            circus = new Circus(1200, 600);
        
        return circus;
    }


    private Circus(int screenWidth, int screenHeight) {
        i = 0;
        game = new Game();
        gameOver = false;
        this.width = screenWidth;
        this.height = screenHeight;
        constant = new LinkedList<GameObject>();
        control = new LinkedList<GameObject>();
        moving = new LinkedList<GameObject>();

        // maybe difficulty sent in constructor?
        hearts = new HeartCounter(3);
        intersection = new Intersection(this);
        movement = new MovementStrategy(new OscillationStrategy(), new DownOnlyStrategy());
        objFalling = new BombsStrategy();
        difficulty = new DifficultyStrategy(new ObjectSpeedlvl2Strategy(), movement, objFalling,3);
        // all above not here

        //make clown coords x 10
        clown = Clown.getInstance((int) (screenWidth / 2.5) , (int) (screenHeight / 1.7) -2,
                "FinalProjectt\\clown-removebg-preview_3_53.png");

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
                //maybe a joption pane too?
                System.out.println("ERRAOR : You ran out of falling objects");
                // close game
                System.exit(0);
            }
        }
        i += n;
    }

    @Override
    public boolean refresh() {
        boolean timeout = timePassedInms > MAX_TIME; 
        if(!gameOver)
        {
            //state management
            if (hearts.getLives() == 0 || timeout) {
                game.setState(new Finish());
                game.currentEvent();
            }
            else if (timePassedInms / 1000 == 45) {
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

                
                if (o instanceof Plate)
                    intersection.setIntersection(new IntersectWithPlateStrategy());
                else if (o instanceof Bomb)
                    intersection.setIntersection(new IntersectWithBombStrategy());
                else if(o instanceof Heart)
                    intersection.setIntersection(new IntersectwithHeartStrategy());

                intersection.handleIntersection((FallingObject) o, righttoIntersectWith);
                intersection.handleIntersection((FallingObject) o, lefttoIntersectWith);

                
                movement.move((FallingObject) o, difficulty.getFallingObjectSpeedStrategy());

                }
        }
        
        //returning false is GamePver
        return !gameOver;
            
            
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
    public void setObjectsFallingSpeed(ObjectSpeedStrategy speedStrategy)
    {
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

    public HeartCounter getHeartCounter()
    {
        return hearts;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public boolean isGameOver()
    {
        return this.gameOver;
    }

    

}
