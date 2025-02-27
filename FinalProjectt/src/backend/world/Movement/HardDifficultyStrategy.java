package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.Speedlvl2Strategy;

import backend.world.ObjectsFallingStrategy.BombsAndHeartsStrategy;

public class HardDifficultyStrategy implements PredefinedDifficultyStrategy { 

    @Override
    public void setDifficulty(Difficulty diff) {
        diff.setSpeedStrategy(new Speedlvl2Strategy());
        diff.setObjectsFallingStrategy(new BombsAndHeartsStrategy());
        diff.setNumFallingObjPerSecond(4);
        diff.setMovement(new OscillationStrategy(), new DownOnlyStrategy());
    }
}
