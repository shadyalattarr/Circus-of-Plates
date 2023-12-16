package backend.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Plate implements FallingObject {

    public static final int SPRITE_HEIGHT = 15;
    public static final int SPRITE_WIDTH = 70;
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private String filename;

    private boolean bottom;

    private boolean visible;
    private Color[] colors = {Color.RED, Color.LIGHT_GRAY, Color.GREEN, Color.CYAN, Color.PINK};

    public Plate(int posX, int posY, Color color) {
        this.x = posX;
        this.y = posY;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed
        // sequentially
        Color randomColor = getRandomColor();
        spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImages[0].createGraphics();
        g2.setColor(randomColor);
        g2.fillOval(getWidth()/2 - SPRITE_WIDTH/2 , getHeight()/2 - SPRITE_HEIGHT/2, SPRITE_WIDTH, SPRITE_HEIGHT);
        
        g2.setColor(Color.BLACK);
        g2.drawArc(getWidth() / 2 - SPRITE_WIDTH / 2, getHeight() / 2 - SPRITE_HEIGHT / 4, SPRITE_WIDTH, SPRITE_HEIGHT / 2, 0, -180);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.dispose();

    }
    
    private Color getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(colors.length); 
        return colors[index];
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



    
   
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
