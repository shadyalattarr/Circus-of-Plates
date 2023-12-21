package backend.world.Movement;
import backend.object.FallingObject;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;

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
        moveP.patternMove(o,gameSpeed);
    }
    
    public MoveDirectionStrategy getMoveD() {
        return this.moveD;
    }

    public void setMoveD(MoveDirectionStrategy moveD) {
        this.moveD = moveD;
    }

    public MovePatternStrategy getMoveP() {
        return this.moveP;
    }

    public void setMoveP(MovePatternStrategy moveP) {
        this.moveP = moveP;
    }

    public ObjectSpeedStrategy getSpeedStrat() {
        return this.speedStrat;
    }

    public void setSpeedStrat(ObjectSpeedStrategy speedStrat) {
        this.speedStrat = speedStrat;
    }
}
