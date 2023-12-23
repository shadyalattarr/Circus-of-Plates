package backend.object;

import java.awt.Color;


public interface ObjectOnStick extends FallingObject {

    public Color getColor();
    public boolean isCaught();
    public void setIsCaught(boolean isCaught);
    
}
