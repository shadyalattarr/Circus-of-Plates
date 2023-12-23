package backend.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


public class BarObject extends Shape{
	public static final int SPRITE_WIDTH = 5;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int height;
	private final Clown clown;
	private final boolean isLeftStick;
	private int p;

	public BarObject(int posX, int posY, int height, Color color, Clown clown,boolean isLeftStick){
		this.x = posX;
		this.y = posY;
		this.height = height;
		this.visible = true;
		//it left stick if true and right stick if false
		this.isLeftStick = isLeftStick;
		this.clown=clown;
		p= clown.getX();
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
		//does nothing
    }
	
	@Override
	public void setX(int mX) {
		//System.out.println(clown.getWidth());
		if (clown.getX() != p) {          
            super.setX(mX);
            p = clown.getX(); 
        }
    
		// System.out.println("clown on left? "+clown.isClownOnFarLeft());
		// System.out.println("Clown: "+ Clown.getInstance(0, 0, null).getX());
		// System.out.println("bar"+mX);
		// System.out.println("cond mx<=210"+ (mX<210));
		// if(!isLeftStick)
		// 	{
		// 		if(clown.isClownOnFarLeft())
		// 		{
		// 			if(mX>=210)
		// 				super.setX(mX);
		// 		}
		// 		else if(clown.isClownOnFarRight())
		// 		{
		// 			if(mX<=Circus.getCircus().getWidth()-clown.getWidth() +214 )
		// 			 	super.setX(mX);	
		// 		}
		// 		else if(!(clown.isClownOnFarLeft() && clown.isClownOnFarRight()))
		// 		{
		// 			super.setX(mX);
		// 		}
		// 	}
		// else 
		// 	{
		// 		if(clown.isClownOnFarLeft())
		// 		{
		// 			if(mX<30)
		// 				super.setX(mX);
		// 		}
		// 		else if(clown.isClownOnFarRight())
		// 		{//970 = 1200-264 --- 936 +
		// 			if(mX>Circus.getCircus().getWidth()-clown.getWidth() +34 /*+34 */ )
		// 			 	super.setX(mX);	
		// 		}
		// 		else if(!(clown.isClownOnFarLeft() && clown.isClownOnFarRight()))
		// 		{
		// 			super.setX(mX);
		// 		}
		// 	}
		// if(!(clown.isClownOnFarRight() && mX>=Circus.getCircus().getWidth() - clown.getWidth() +30))
		// 	{
		// 		super.setX(mX);
		// 	}
		// 	System.out.println("clown on right? " +clown.isClownOnFarRight());


		// if(isLeftStick)//rules for left stick
		// {
		// 	if(mX<1400-clown.getWidth()+40 && mX>=20)
		// 		super.setX(mX);
		// }
		// else{
		// 	if(mX > clown.getWidth()-60 && mX<1400 -50  /*Circus.getWidth()*/)
		// 		super.setX(mX);
		// }
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
	public boolean isLeftStick()
	{
		return isLeftStick;
	}
}
