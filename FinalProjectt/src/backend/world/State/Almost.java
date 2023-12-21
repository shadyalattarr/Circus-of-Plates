package backend.world.State;


public class Almost implements GameState {

    @Override
    public void stateAction(Game game) {
       // game.getCircus().difficulty = new DifficultyStrategy(new ObjectSpeedFinalSecondsStrategy(), game.getCircus().movement, game.getCircus().objFalling);
;       System.out.println("almost");
            
    }

}
