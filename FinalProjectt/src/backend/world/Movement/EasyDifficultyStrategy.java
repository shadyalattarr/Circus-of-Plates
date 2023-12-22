package backend.world.Movement;

import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;
import backend.world.Movement.ObjectSpeedStrategy.Speedlvl1Strategy;
import backend.world.ObjectsFallingStrategy.ObjectsFallingStrategy;
import backend.world.ObjectsFallingStrategy.PlatesOnlyStrategy;

public class EasyDifficultyStrategy implements PredefinedDifficultyStrategy{

    @Override
    public void setDifficulty(Difficulty diff) {
        diff.setSpeedStrategy(new Speedlvl1Strategy());
        diff.setObjectsFallingStrategy(new PlatesOnlyStrategy());
        diff.setNumFallingObjPerSecond(2);
        Movement movement = new Movement(new NoOscillationStrategy(), new DownOnlyStrategy());
        diff.setMovement(movement);
    }

}
