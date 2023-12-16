
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public class App {
	
	public static void main(String[] args) {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");

		
		JMenuBar  menuBar = new JMenuBar();;
		JMenu menu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		menu.add(newMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(550, 600), menuBar, Color.white);
		
	}
	
}
