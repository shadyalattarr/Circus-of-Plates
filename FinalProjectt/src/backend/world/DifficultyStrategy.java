package backend.world;

public class DifficultyStrategy {
    // -NumOfLivesStrategy : NumberOfLivesStrategy
    private ObjectSpeedStrategy speedStrategy;
    // -NumOfClownsStrategy : NumberOfCLownsStrategy
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
