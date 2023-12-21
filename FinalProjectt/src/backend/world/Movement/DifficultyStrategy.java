package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;
import backend.world.ObjectsFallingStrategy.ObjectsFallingStrategy;

public class DifficultyStrategy {
    private ObjectSpeedStrategy speedStrategy;
    private ObjectsFallingStrategy objectsFallingStrategy;
    private MovementStrategy movement;

    public DifficultyStrategy(ObjectSpeedStrategy speedStrat, MovementStrategy moveStrat,ObjectsFallingStrategy objectsFallingStrat)
    {
        this.movement = moveStrat;
        this.speedStrategy = speedStrat;
        this.objectsFallingStrategy = objectsFallingStrat;
    }

    public ObjectSpeedStrategy getFallingObjectSpeedStrategy()
    {
        return this.speedStrategy;
    }

    

    //getmovement?
}
