package backend.object;

import java.awt.Color;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface ObjectOnStick extends FallingObject {

    public Color getColor();
    public boolean isCaught();
    public void setIsCaught(boolean isCaught);
    
}
