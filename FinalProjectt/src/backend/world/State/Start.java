package backend.world.State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import backend.world.Circus;
import backend.world.Movement.CrazyDifficultyStrategy;
import backend.world.Movement.Difficulty;
import backend.world.Movement.EasyDifficultyStrategy;
import backend.world.Movement.HardDifficultyStrategy;
import backend.world.Movement.MediumDifficultyStrategy;
import backend.world.Movement.PredefinedDifficultyStrategy;
import backend.world.Movement.ObjectSpeedStrategy.ObjectSpeedStrategy;
import backend.world.Movement.ObjectSpeedStrategy.Speedlvl1Strategy;
import backend.world.Movement.ObjectSpeedStrategy.Speedlvl2Strategy;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public class Start implements GameState {

    private PredefinedDifficultyStrategy difficulty;

    public void chooseDifficulty() {
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Dimension dim = new Dimension(200, 100);

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        easyButton.setMaximumSize(dim);
        mediumButton.setMaximumSize(dim);
        easyButton.addActionListener(e -> {
            //difficulty = new EasyDifficultyStrategy();
            difficulty = new HardDifficultyStrategy();
            frame.dispose();
            startGame();
        });
        mediumButton.addActionListener(e -> {
            //difficulty = new MediumDifficultyStrategy();
            difficulty = new CrazyDifficultyStrategy();
            frame.dispose();
            startGame();
        });
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createRigidArea(new Dimension(0, 70)));
        panel.add(easyButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(mediumButton);
        panel.add(Box.createVerticalGlue());
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setBackground(null);
        frame.setTitle("Choose Difficulty");
        frame.setVisible(true);
    }

    @Override
    public void stateAction(Game game) {
        chooseDifficulty();

    }

    private void startGame() {
        JMenuBar menuBar = new JMenuBar();
        Circus circus = Circus.getCircus(difficulty);

        final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", circus, menuBar,
                Color.WHITE);
    }

}
