package backend.object;

public class Bomb extends ImageObject implements FallingObject{

    public Bomb(int posX, int posY, String path) {
        super(posX, posY, path);
    }

    @Override
    public void normalSetX(int mX) {
        setX(mX);
    }

    
}
