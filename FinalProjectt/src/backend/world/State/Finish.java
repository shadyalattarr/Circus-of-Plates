package backend.world.State;

import javax.swing.JOptionPane;

import backend.world.Circus;

public class Finish implements GameState{

    @Override
    public void stateAction(Game game) {
        Circus circus= Circus.getCircus();
        if(circus.getHeartCounter().getLives()==0){
                JOptionPane.showMessageDialog(null, "Unlucky :( \nBetter luck next time!");

        }
        else{
        JOptionPane.showMessageDialog(null, "Thanks for playing!");
        }

    }
    
}
