package backend.world.State;

public class Finish implements GameState{

    @Override
    public void stateAction(Game game) {
        //if circus singelto we can do smth here t oset end of game
       System.out.println("game is over");
    }
    
}
