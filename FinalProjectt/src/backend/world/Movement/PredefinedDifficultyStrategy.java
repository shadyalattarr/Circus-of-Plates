package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;
import backend.world.ObjectsFallingStrategy.ObjectsFallingStrategy;

public interface PredefinedDifficultyStrategy {

    public void setDifficulty(Difficulty diff);

}
