package backend.world.Movement.ObjectSpeedStrategy;

public class Speedlvl1Strategy implements ObjectSpeedStrategy {

    @Override
    public int getFallingObjectSpeed() {
        return 2;//minimum for diags
    }
}
