package backend.world;
import java.util.ArrayList;
import backend.object.*;
import eg.edu.alexu.csd.oop.game.GameObject;

public interface ObjectsFallingStrategy {
    
    public ArrayList<GameObject> generateObjectsFalling(int n);   

    
}
