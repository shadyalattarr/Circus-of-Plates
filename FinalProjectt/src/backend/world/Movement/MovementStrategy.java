package backend.world.Movement;
import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

public class MovementStrategy {
    private MoveDirectionStrategy moveD;
    private MovePatternStrategy moveP;
    private ObjectSpeedStrategy speedStrat;

    public MovementStrategy(MovePatternStrategy moveP,MoveDirectionStrategy moveD)
    {
        System.out.println("cool1");

        this.moveD = moveD;
        this.moveP = moveP;
        System.out.println("cool");
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
        moveP.patternMove(o,gameSpeed);
    }

}
