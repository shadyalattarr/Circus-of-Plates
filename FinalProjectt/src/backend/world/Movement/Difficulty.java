package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;
import backend.world.ObjectsFallingStrategy.ObjectsFallingStrategy;

public class Difficulty {
    private ObjectSpeedStrategy speedStrategy;
    private ObjectsFallingStrategy objectsFallingStrategy;
    private Movement movement;
    private int numFallingObjPerSecond;
    private PredefinedDifficultyStrategy predefinedDifficulty;
    private int frenzyNumFallingObj;


    //two ways to make a difficulty, use a predeifned one or make one yourself
    public Difficulty(PredefinedDifficultyStrategy pDiff)
    {
        pDiff.setDifficulty(this);
    }

    public Difficulty(ObjectSpeedStrategy speedStrat, Movement moveStrat,ObjectsFallingStrategy objectsFallingStrat,int numFallingPerSec)
    {
        this.movement = moveStrat;
        this.speedStrategy = speedStrat;
        this.objectsFallingStrategy = objectsFallingStrat;
        this.numFallingObjPerSecond = numFallingPerSec;
    }

    public ObjectSpeedStrategy getFallingObjectSpeedStrategy()
    {
        return this.speedStrategy;
    }

    public void setNumFallingObjPerSecond(int n)
    {
        this.numFallingObjPerSecond = n;
    }

    public int getNumFallingObjPerSecond()
    {
        return this.numFallingObjPerSecond;
    }

    public void setFallingObjectSpeedStrategy(ObjectSpeedStrategy speedStrategy) {
        this.speedStrategy = speedStrategy;
        this.movement.setSpeedStrat(speedStrategy);
    }

    public ObjectsFallingStrategy getObjectsFallingStrategy() {
        return this.objectsFallingStrategy;
    }

    public void setObjectsFallingStrategy(ObjectsFallingStrategy objectsFallingStrategy) {
        this.objectsFallingStrategy = objectsFallingStrategy;
    }

    public Movement getMovement() {
        return this.movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
    //overloading
    public void setMovement(MovePatternStrategy moveP,MoveDirectionStrategy moveD)
    {
        if(this.movement == null)
            this.setMovement(new Movement(moveP, moveD));
        else
        {
            this.movement.setMoveP(moveP);
            this.movement.setMoveD(moveD);
        }
    }
    public void setMovement(MovePatternStrategy moveP,MoveDirectionStrategy moveD ,ObjectSpeedStrategy gameSpeed) {
        setMovement(moveP, moveD);
        this.setFallingObjectSpeedStrategy(gameSpeed);
    }

    public void setSpeedStrategy(ObjectSpeedStrategy speedStrategy) {
        this.speedStrategy = speedStrategy;
    }

    //getmovement?
}
