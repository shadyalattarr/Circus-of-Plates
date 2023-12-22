package backend.world.State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import backend.world.Circus;
import backend.world.Memento;
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
    
    private final Stack<Memento> mementoStack = new Stack<>();
    JFrame frame2 = new JFrame();
    int picked = -1;
    private Circus circus;

    public void chooseDifficulty() {
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Dimension dim = new Dimension(200, 100);

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Difficult");
        JButton crazyButton = new JButton("CRAZY");
        easyButton.setMaximumSize(dim);
        mediumButton.setMaximumSize(dim);
        easyButton.setMaximumSize(dim);
        crazyButton.setMaximumSize(dim);
        easyButton.addActionListener(e -> {
            difficulty = new EasyDifficultyStrategy();
            frame.dispose();
            startGame();
        });
        mediumButton.addActionListener(e -> {
            difficulty = new MediumDifficultyStrategy();
            frame.dispose();
            startGame();
        });
        hardButton.addActionListener(e -> {
            difficulty = new HardDifficultyStrategy();
            frame.dispose();
            startGame();
        });
        crazyButton.addActionListener(e -> {
            difficulty = new CrazyDifficultyStrategy();
            frame.dispose();
            startGame();
        });
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(easyButton);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(mediumButton);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(hardButton);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(crazyButton);

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setBackground(null);
        frame.setTitle("Choose Difficulty");
        frame.setVisible(true);
    }

    public void startFrame(){
        //JFrame frame = new JFrame();
      
        frame2.setLayout(new FlowLayout());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Dimension dim = new Dimension(200, 100);

        JButton startButton = new JButton("Start new game");
        JButton loadButton = new JButton("Load game");

        startButton.setMaximumSize(dim);
        loadButton.setMaximumSize(dim);

        startButton.addActionListener(e -> {
            chooseDifficulty();
            frame2.dispose();
        });
        loadButton.addActionListener(e -> {

            String[] saves = {"Save 1", "Save 2", "Save 3"};
                    picked = JOptionPane.showOptionDialog(null,
                            "Choose a Promotion:",
                            "Promotions",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            saves,
                            saves[0]);
            if(mementoStack.size()>picked) {               
            Memento loadedmemento = mementoStack.get(picked);
            circus.getMemento(loadedmemento);
            startGame();           
            
            frame2.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Empty Save Slot");
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(startButton);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(loadButton);


        frame2.add(panel);
        frame2.setSize(300, 300);
        frame2.setLocationRelativeTo(null);
        frame2.setBackground(null);
        frame2.setTitle("Start Game");
        frame2.setVisible(true);

    }

    @Override
    public void stateAction(Game game) {
        startFrame();

    }

    private void startGame() {
        JMenuBar menuBar = new JMenuBar();
        
         circus = Circus.getCircus(difficulty);

        final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", circus, menuBar,
                Color.WHITE);
               
		JMenu menu = new JMenu("File");
		
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
        menu.add(saveMenuItem);
		menuBar.add(menu);
		
		pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
        saveMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Memento save = circus.createMemento();
                if(mementoStack.size()<3){
                    System.out.println("ff");
                gameController.pause();
                mementoStack.push(save);
                circus.reset();
                
                JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(menuBar);
                thisFrame.dispose();
                frame2.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Only 3 saves allowed");
                }
			}
		});

    }

}
