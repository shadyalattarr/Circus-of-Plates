package backend.world;

public class HeartCounter {
    public static int lives=3;

    public int getLives(){
        return lives;
    }
    public void addLife(){
        ++lives;
    }
    public void loseLife(){
        --lives;
    }
}
