package backend.world;
import backend.object.FallingObject;
import backend.world.*;

public class MovementStrategy {
    private MoveDirectionStrategy moveD;
    private MovePatternStrategy moveP;
    private ObjectSpeedStrategy speedStrat;

    public MovementStrategy(MovePatternStrategy moveP,MoveDirectionStrategy moveD)
    {
        this.moveD = moveD;
        this.moveP = moveP;
    }
    public MovementStrategy(MovePatternStrategy moveP,MoveDirectionStrategy moveD ,ObjectSpeedStrategy gameSpeed)
    {
        this(moveP,moveD);
        this.speedStrat = gameSpeed;
    }
     
    //second constructor to make a movement without speed aka difault speed?

    public void move(FallingObject o,ObjectSpeedStrategy gameSpeed)
    {
        moveD.directionMove(o, gameSpeed);
        moveP.patternMove();
    }

}
