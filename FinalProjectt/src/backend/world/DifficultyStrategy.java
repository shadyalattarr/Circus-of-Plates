package backend.world;

public class DifficultyStrategy {
    // -NumOfLivesStrategy : NumberOfLivesStrategy
    private ObjectSpeedStrategy speedStrategy;
    // -NumOfClownsStrategy : NumberOfCLownsStrategy
    // -ObjectsFallingStrategy : ObjectsFallingStrategy
    private MovementStrategy movement;

    public DifficultyStrategy(ObjectSpeedStrategy speedStrat, MovementStrategy moveStrat)
    {
        this.movement = moveStrat;
        this.speedStrategy = speedStrat;
    }

    public ObjectSpeedStrategy getFallingObjectSpeedStrategy()
    {
        return this.speedStrategy;
    }

    //getmovement?
}
