package backend.world.Movement.ObjectSpeedStrategy;

public class Speedlvl3Strategy implements ObjectSpeedStrategy {

    @Override
    public int getFallingObjectSpeed() {
        return 5;//minimum for diags
    }
}
