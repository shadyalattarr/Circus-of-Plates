package backend.world.Movement.ObjectSpeedStrategy;

public class ObjectSpeedlvl1Strategy implements ObjectSpeedStrategy {

    @Override
    public int getFallingObjectSpeed() {
        return 2;//minimum for diags
    }
}
