package backend.object;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageObject extends Shape{
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int type;
	
	public ImageObject(int posX, int posY, String path){
		this(posX, posY, path, 0);
	}
	
	public ImageObject(int posX, int posY, String path, int type){
		this.x = posX;
		this.y = posY;
		this.type = type;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			spriteImages[0] = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
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
