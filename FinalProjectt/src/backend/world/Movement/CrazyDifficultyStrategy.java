package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.Speedlvl2Strategy;
import backend.world.Movement.ObjectSpeedStrategy.Speedlvl3Strategy;
import backend.world.ObjectsFallingStrategy.BombsStrategy;
import backend.world.ObjectsFallingStrategy.EverythingSoFarStrategy;

public class CrazyDifficultyStrategy implements PredefinedDifficultyStrategy { 

    @Override
    public void setDifficulty(Difficulty diff) {
        diff.setSpeedStrategy(new Speedlvl3Strategy());
        diff.setObjectsFallingStrategy(new EverythingSoFarStrategy());
        diff.setNumFallingObjPerSecond(5);
        diff.setMovement(new OscillationStrategy(), new DownOnlyStrategy());
    }
}
