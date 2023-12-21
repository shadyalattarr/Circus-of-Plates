package backend.world;

public class Finish implements GameState{

    @Override
    public void stateAction(Game game) {
       System.out.println("game is over");
    }
    
}
