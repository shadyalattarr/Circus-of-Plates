package backend.object;
import eg.edu.alexu.csd.oop.game.GameObject;

public interface FallingObject extends GameObject{
    boolean isVertical();
	void setisVertical(boolean isVertical);
    
}
