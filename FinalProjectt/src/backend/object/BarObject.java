package backend.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BarObject extends Shape{
	public static final int SPRITE_WIDTH = 5;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int height;
	private final int distanceToClownX;
	private boolean verticalOnly;
	private final Clown clown;

	public BarObject(int posX, int posY, int height, boolean verticalOnly, Color color, Clown clown){
		this.x = posX;
		this.y = posY;
		this.height = height;
		this.verticalOnly = verticalOnly;
		this.visible = true;
		this.clown=clown;
		distanceToClownX = Math.abs(clown.getX() - posX);
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		spriteImages[0] = new BufferedImage(SPRITE_WIDTH, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();
		g2.setColor(color);
		g2.setBackground(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(20));
		g2.drawLine(0, 0, 0, getHeight());
		g2.dispose();
	}

	
	@Override
	public void setY(int mY) {
		if (!verticalOnly) {
            super.setY(mY);;
        }
    }
	
	@Override
	public void setX(int mX) {
		if(clown.getX() > 0 && clown.getX()<1400-clown.getWidth() /*Circus.getWidth()*/)
			super.setX(mX);
	}

	@Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return SPRITE_WIDTH;
    }

    @Override
    public int getHeight() {
       return height;
    }
}
