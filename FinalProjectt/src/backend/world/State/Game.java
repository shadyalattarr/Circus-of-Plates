package backend.world.State;


public class Game{
    GameState currState;

    public void setState(GameState state)
    {
        currState=state;
    }

    public void currentEvent()
    {
        currState.stateAction();
    }

}
