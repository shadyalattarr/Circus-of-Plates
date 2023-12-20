package backend.object;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class Shape implements GameObject {

    // an array of sprite images that are drawn sequentially
    private int x;
    private int y;
    private boolean visible;



    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {       
        this.y = mY;
    }

    @Override
    public abstract BufferedImage[] getSpriteImages();
    @Override
	public abstract int getWidth();

	@Override
	public abstract int getHeight();

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
