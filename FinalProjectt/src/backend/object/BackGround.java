package backend.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround extends Shape {
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int type;
    int width;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    int height;

    public BackGround(int width, int height, int posX, int posY, String path, int type) {
        this.x = posX;
        this.y = posY;
        this.type = type;
        this.width = width;
        this.height = height;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed
        // sequentially
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            spriteImages[0] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = spriteImages[0].createGraphics();
            
            // Draw the image scaled to the new width and height
            g2.drawImage(originalImage, 0, 0, width, height, null);
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}

// super(posX, posY, path);
// BufferedImage[] spriteImages = getSpriteImages();
// Graphics2D g2 = spriteImages[0].createGraphics();
// g2.drawImage(spriteImages[0], posX, posY, null);
// g2.dispose();