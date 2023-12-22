package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.Speedlvl2Strategy;
import backend.world.ObjectsFallingStrategy.BombsAndHeartsStrategy;
import backend.world.ObjectsFallingStrategy.PlatesOnlyStrategy;

public class MediumDifficultyStrategy implements PredefinedDifficultyStrategy { 

    @Override
    public void setDifficulty(Difficulty diff) {
        diff.setSpeedStrategy(new Speedlvl2Strategy());
        diff.setObjectsFallingStrategy(new BombsAndHeartsStrategy());
        diff.setNumFallingObjPerSecond(3);
        diff.setMovement(new NoOscillationStrategy(), new DownOnlyStrategy());
    }
}
