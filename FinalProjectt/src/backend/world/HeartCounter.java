package backend.world;

public class HeartCounter {
    public int lives;

    HeartCounter(int lives)
    {
        this.lives = lives;
    }
    public int getLives(){
        return lives;
    }
    public void addLife(){
        ++lives;
    }
    public void takeDamage(){
        --lives;
    }
}
