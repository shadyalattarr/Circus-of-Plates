package backend.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Plate implements FallingObject {

    public static final int SPRITE_HEIGHT = 50;
    public static final int SPRITE_WIDTH = 90;
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private String filename;

    private boolean bottom;

    private boolean visible;
    private boolean isOnShelf;

    public Plate(int posX, int posY, boolean bottom, Color color, String filename) {
        this.x = posX;
        this.filename = filename;
        this.y = posY;
        this.bottom = bottom;
        this.isOnShelf = true;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed
        // sequentially
        spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImages[0].createGraphics();
        g2.setColor(color);
        g2.setStroke(new BasicStroke(20));

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.dispose();

    }

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
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return SPRITE_WIDTH;
    }

    @Override
    public int getHeight() {
        return SPRITE_HEIGHT;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public boolean isOnShelf() {
        return isOnShelf;
    }

    public void setOnShelf(boolean isOnShelf) {
        this.isOnShelf = isOnShelf;
    }
    public String getFilename() {
        return filename;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
