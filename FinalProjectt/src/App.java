 import backend.world.State.Game;
import backend.world.State.Start;


public class App {
	
	public static void main(String[] args) {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");
		Game game = new Game();
		game.setState(new Start());
		game.currentEvent();

		
		// JMenuBar  menuBar = new JMenuBar();;
		
		// final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(1400, 750), menuBar, Color.WHITE);
		
	}
	
}
