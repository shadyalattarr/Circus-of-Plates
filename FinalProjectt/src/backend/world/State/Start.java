package backend.world.State;

import java.awt.Color;

import javax.swing.JMenuBar;

import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public class Start implements GameState {

    @Override
    public void stateAction(Game game) {
        JMenuBar  menuBar = new JMenuBar();
		Circus circus= Circus.getCircus();
		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", circus, menuBar, Color.WHITE);
        
    }

}
