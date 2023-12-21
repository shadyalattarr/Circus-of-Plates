package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;
import backend.world.ObjectsFallingStrategy.ObjectsFallingStrategy;

public class DifficultyStrategy {
    private ObjectSpeedStrategy speedStrategy;
    private ObjectsFallingStrategy objectsFallingStrategy;
    private MovementStrategy movement;
    private int numFallingObjPerSecond;


    public DifficultyStrategy(ObjectSpeedStrategy speedStrat, MovementStrategy moveStrat,ObjectsFallingStrategy objectsFallingStrat,int numFallingPerSec)
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

    public MovementStrategy getMovement() {
        return this.movement;
    }

    public void setMovement(MovementStrategy movement) {
        this.movement = movement;
    }
    //overloading
    public void setMovement(MovePatternStrategy moveP,MoveDirectionStrategy moveD)
    {
        this.movement.setMoveP(moveP);
        this.movement.setMoveD(moveD);
    }
    public void setMovement(MovePatternStrategy moveP,MoveDirectionStrategy moveD ,ObjectSpeedStrategy gameSpeed) {
        setMovement(moveP, moveD);
        this.setFallingObjectSpeedStrategy(gameSpeed);
    }

    //getmovement?
}
